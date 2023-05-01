package com.example.api_beverage_shop.mapper;

import com.example.api_beverage_shop.dto.response.order.OrderItemResponse;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.*;
import com.example.api_beverage_shop.repository.IOrderRepository;
import com.example.api_beverage_shop.repository.IProductRepository;
import com.example.api_beverage_shop.repository.ISizeRepository;
import com.example.api_beverage_shop.repository.IToppingRepository;
import com.example.api_beverage_shop.util.AppConstant;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemMapper {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IToppingRepository toppingRepository;

    @Autowired
    private ISizeRepository sizeRepository;

    //    Covert product
    private final Converter<Product, String> productToProductNameConverter = context -> {
        Product product = context.getSource();
        return product != null ? product.getProductName() : null;
    };
    private final Converter<String, Product> productNameToProductConverter = mappingContext -> {
        String productName = mappingContext.getSource();
        Product product = productRepository.findByProductName(productName).orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND_WITH_NAME + productName));
        return product;
    };

    //    Covert order
    private final Converter<Order, Long> orderToOrderIdConverter = context -> {
        Order order = context.getSource();
        return order != null ? order.getId() : null;
    };
    private final Converter<Long, Order> orderIdToOrderConverter = mappingContext -> {
        Long orderId = mappingContext.getSource();
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.ORDER_NOT_FOUND + orderId));
        return order;
    };

    //    Convert Topping
    private final Converter<List<Topping>, List<String>> toppingToToppingNameConverter = context -> {
        List<Topping> toppingList = context.getSource();
        return toppingList != null ? toppingList.stream().map(Topping::getToppingName).collect(Collectors.toList()) : null;
    };

    private final Converter<List<String>, List<Topping>> toppingNameToToppingConverter = mappingContext -> {
        List<String> toppingName = mappingContext.getSource();
        List<Topping> toppingList = new ArrayList<>();
        for (String name : toppingName) {
            Topping topping = toppingRepository.findByToppingName(name).orElseThrow(() -> new ResourceNotFoundException(AppConstant.TOPPING_NOT_FOUND + name));
            toppingList.add(topping);
        }
        return toppingList;
    };

    //    Convert Size
    private final Converter<Size, String> sizeToSizeNameConverter = context -> {
        Size size = context.getSource();
        return size != null ? size.getSizeName() : null;
    };
    private final Converter<String, Size> sizeNameToSizeConverter = mappingContext -> {
        String sizeName = mappingContext.getSource();
        Size size = sizeRepository.findBySizeName(sizeName).orElseThrow(() -> new ResourceNotFoundException(AppConstant.Size_NOT_FOUND + sizeName));
        return size;
    };

    @PostConstruct
    public void init() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.createTypeMap(OrderItem.class, OrderItemResponse.class)
                .addMappings(mapper -> mapper.using(productToProductNameConverter)
                        .map(OrderItem::getProduct, OrderItemResponse::setProductName))

                .addMappings(mapper -> mapper.using(orderToOrderIdConverter)
                        .map(OrderItem::getOrder, OrderItemResponse::setOrderId))

                .addMappings(mapper -> mapper.using(toppingToToppingNameConverter)
                        .map(OrderItem::getToppings, OrderItemResponse::setToppingsName))

                .addMappings(mapper -> mapper.using(sizeToSizeNameConverter)
                        .map(OrderItem::getSizeProduct, OrderItemResponse::setSizeName))

                .addMappings(mapper -> mapper.map(src -> src.getOrder().getStatus(), OrderItemResponse::setStatus))
                .addMappings(mapper -> mapper.map(src -> src.getProduct().getPathImage(), OrderItemResponse::setImgProduct));

        mapper.createTypeMap(OrderItemResponse.class, OrderItem.class)
                .setPropertyCondition(Conditions.isNotNull())
                .addMappings(mapper -> mapper.using(productNameToProductConverter)
                        .map(OrderItemResponse::getProductName, OrderItem::setProduct))

                .addMappings(mapper -> mapper.using(orderIdToOrderConverter)
                        .map(OrderItemResponse::getOrderId, OrderItem::setOrder))

                .addMappings(mapper -> mapper.using(toppingNameToToppingConverter)
                        .map(OrderItemResponse::getToppingsName, OrderItem::setToppings))

                .addMappings(mapper -> mapper.using(sizeNameToSizeConverter)
                        .map(OrderItemResponse::getSizeName, OrderItem::setSizeProduct));
    }

    public OrderItemResponse toDTO(OrderItem orderItem) {
        OrderItemResponse orderItemResponse;
        orderItemResponse = mapper.map(orderItem, OrderItemResponse.class);
        return orderItemResponse;
    }

    public OrderItem toEntity(OrderItemResponse orderItemResponse) {
        return mapper.map(orderItemResponse, OrderItem.class);
    }
}
