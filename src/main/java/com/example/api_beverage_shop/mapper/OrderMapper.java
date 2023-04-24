package com.example.api_beverage_shop.mapper;

import com.example.api_beverage_shop.dto.response.order.OrderDTO;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.*;
import com.example.api_beverage_shop.repository.IDiscountRepository;
import com.example.api_beverage_shop.repository.IUserRepository;
import com.example.api_beverage_shop.util.AppConstant;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IDiscountRepository discountRepository;

    @Autowired
    private IUserRepository userRepository;

    //    Covert discount
    private final Converter<Discount, String> discountToDiscountCodeConverter = context -> {
        Discount discount = context.getSource();
        return discount != null ? discount.getDiscountCode() : null;
    };
    private final Converter<String, Discount> discountCodeToDiscountConverter = mappingContext -> {
        String discountCode = mappingContext.getSource();
        Discount discount = discountRepository.findByDiscountCode(discountCode).orElseThrow(() -> new ResourceNotFoundException(AppConstant.DISCOUNT_NOT_FOUND_WITH_CODE + discountCode));
        return discount;
    };

    //    Covert user
    private final Converter<User, Long> userToUserIdConverter = context -> {
        User user = context.getSource();
        return user != null ? user.getId() : null;
    };
    private final Converter<Long, User> userIdToUserConverter = mappingContext -> {
        Long userId = mappingContext.getSource();
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND + userId));
        return user;
    };


    @PostConstruct
    public void init() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.createTypeMap(Order.class, OrderDTO.class)
                .addMappings(mapper -> mapper.using(discountToDiscountCodeConverter)
                        .map(Order::getDiscount, OrderDTO::setDiscountCode))

                .addMappings(mapper -> mapper.using(userToUserIdConverter)
                        .map(Order::getUserOrder, OrderDTO::setUserId));


        mapper.createTypeMap(OrderDTO.class, Order.class)
                .setPropertyCondition(Conditions.isNotNull())
                .addMappings(mapper -> mapper.using(discountCodeToDiscountConverter)
                        .map(OrderDTO::getDiscountCode, Order::setDiscount))

                .addMappings(mapper -> mapper.using(userIdToUserConverter)
                        .map(OrderDTO::getUserId, Order::setUserOrder));
    }

    public OrderDTO toDTO(Order order) {
        OrderDTO orderDTO;
        orderDTO = mapper.map(order, OrderDTO.class);
        return orderDTO;
    }

    public Order toEntity(OrderDTO orderDTO) {
        return mapper.map(orderDTO, Order.class);
    }
}
