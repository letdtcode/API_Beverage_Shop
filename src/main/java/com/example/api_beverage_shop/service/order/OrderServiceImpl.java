package com.example.api_beverage_shop.service.order;

import com.example.api_beverage_shop.dto.OrderDTO;
import com.example.api_beverage_shop.dto.request.CheckOutCartRequest;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.*;
import com.example.api_beverage_shop.repository.ICartItemRepository;
import com.example.api_beverage_shop.repository.IOrderItemRepository;
import com.example.api_beverage_shop.repository.IOrderRepository;
import com.example.api_beverage_shop.repository.IUserRepository;
import com.example.api_beverage_shop.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IOrderItemRepository orderItemRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICartItemRepository cartItemRepository;


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
    }

}
