package com.example.api_beverage_shop.service.order;

import com.example.api_beverage_shop.dto.response.order.OrderDTO;
import com.example.api_beverage_shop.dto.request.cart.CheckOutCartRequest;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.mapper.OrderMapper;
import com.example.api_beverage_shop.model.*;
import com.example.api_beverage_shop.repository.*;
import com.example.api_beverage_shop.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IOrderItemRepository orderItemRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICartItemRepository cartItemRepository;


    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDTO checkOut(CheckOutCartRequest request) {
        Long userId = request.getUserId();
        String address = request.getAddress();
        String nameCus = request.getNameCus();
        int payMent = request.getPayMent();
        String phoneNumber = request.getPhoneNumber();
        Integer shipping = request.getShipping();

        BigDecimal totalItemPrice = BigDecimal.valueOf(0);
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        List<Long> cardItemIdList = request.getCardItemId();


        User userOrder = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND + userId));


        List<CartItem> cartItemList = new ArrayList<>();
        for (Long idItem : cardItemIdList) {
            CartItem item = cartItemRepository.findCartItemById(idItem).orElseThrow(() -> new ResourceNotFoundException(AppConstant.CART_ITEM_NOT_FOUND + idItem));
            totalItemPrice = totalItemPrice.add(item.getTotalPriceItem());
            cartItemList.add(item);
        }
        totalPrice = totalItemPrice.add(BigDecimal.valueOf(shipping));

        Order orderBill = Order.builder()
                .nameCustomer(nameCus)
                .phoneNumber(phoneNumber)
                .address(address)
                .shipping(shipping)
                .payment(payMent)
                .totalItemPrice(totalItemPrice)
                .totalPrice(totalPrice)
                .userOrder(userOrder)
                .status(1).build();
        orderRepository.save(orderBill);

        for (CartItem item : cartItemList) {
            Product productInCart = item.getProduct();
            List<Topping> toppingList = item.getToppings();
            Size sizeOfProduct = item.getSizeProduct();
            Integer quantity = item.getQuantity();
            BigDecimal totalPriceProduct = item.getTotalPriceProduct();
            BigDecimal totalPriceItem = item.getTotalPriceItem();

            OrderItem orderItem = OrderItem.builder()
                    .product(productInCart)
                    .order(orderBill)
                    .toppings(toppingList)
                    .sizeProduct(sizeOfProduct)
                    .quantity(quantity)
                    .totalPriceProduct(totalPriceProduct)
                    .totalPriceItem(totalPriceItem).build();

            orderItemRepository.save(orderItem);
        }
        resetCartUser(cartItemList, userId);
        return orderMapper.toDTO(orderBill);
    }

    @Override
    public OrderDTO approveOrder(Long orderId, Integer status) {
        Order orderCurrent = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.ORDER_NOT_FOUND + orderId));
        orderCurrent.setStatus(status);
        orderCurrent = orderRepository.save(orderCurrent);
        return orderMapper.toDTO(orderCurrent);
    }

    private void resetCartUser(List<CartItem> cartItemList, Long cardId) {
//        Delete Cart Item
        BigDecimal totalCart = BigDecimal.valueOf(0);
        for (CartItem item : cartItemList) {
            totalCart = totalCart.add(item.getTotalPriceItem());
            cartItemRepository.delete(item);
        }

//        Reset total price of cart user
        Cart cartUser = cartRepository.findCartById(cardId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.CART_NOT_FOUND + cardId));
        cartUser.setTotalPrice(totalCart);
        cartRepository.save(cartUser);
    }
}
