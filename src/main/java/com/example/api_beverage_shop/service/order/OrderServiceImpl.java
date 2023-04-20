package com.example.api_beverage_shop.service.order;

import com.example.api_beverage_shop.dto.OrderDTO;
import com.example.api_beverage_shop.dto.request.cart.CheckOutCartRequest;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.mapper.OrderMapper;
import com.example.api_beverage_shop.model.*;
import com.example.api_beverage_shop.repository.*;
import com.example.api_beverage_shop.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        BigDecimal totalItemPrice = request.getTotalItemPrice();
        BigDecimal totalPrice = request.getTotalPrice();

        User userOrder = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND + userId));

        Order orderBill = Order.builder()
                .nameCustomer(nameCus)
                .phoneNumber(phoneNumber)
                .address(address)
                .shipping(shipping)
                .payment(payMent)
                .totalItemPrice(totalItemPrice)
                .totalPrice(totalPrice)
                .userOrder(userOrder).build();
        orderRepository.save(orderBill);

        List<CartItem> cartItemList = cartItemRepository.findByCartId(userId);

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
        resetCartUser(userId);
        return orderMapper.toDTO(orderBill);
    }

    private void resetCartUser(Long cardId) {
//        Delete All Cart Item
        List<CartItem> cartItemList = cartItemRepository.findByCartId(cardId);
        for (CartItem item : cartItemList) {
            cartItemRepository.delete(item);
        }

//        Reset total price of cart user
        Cart cartUser = cartRepository.findCartById(cardId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.CART_NOT_FOUND + cardId));
        cartUser.setTotalPrice(BigDecimal.valueOf(0));
        cartRepository.save(cartUser);
    }
}
