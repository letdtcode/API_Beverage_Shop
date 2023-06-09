package com.example.api_beverage_shop.service.order;

import com.example.api_beverage_shop.dto.request.cart.CheckOutCartRequest;
import com.example.api_beverage_shop.dto.response.order.OrderItemResponse;
import com.example.api_beverage_shop.dto.response.order.OrderResponse;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.mapper.OrderItemMapper;
import com.example.api_beverage_shop.mapper.OrderMapper;
import com.example.api_beverage_shop.model.*;
import com.example.api_beverage_shop.repository.*;
import com.example.api_beverage_shop.util.AppConstant;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private IProductRepository productRepository;

    @Override
    public OrderResponse checkOut(CheckOutCartRequest request) {
        Long userId = request.getUserId();
        String address = request.getAddress();
        String nameCus = request.getNameCus();
        int payMent = request.getPayMent();
        String phoneNumber = request.getPhoneNumber();
        Integer shipping = request.getShipping();

        BigDecimal totalItemPrice = BigDecimal.valueOf(0);
        BigDecimal totalPrice;
        List<Long> cardItemIdList = request.getCardItemId();

        User userOrder = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND + userId));

        List<CartItem> cartItemList = new ArrayList<>();
        for (Long idItem : cardItemIdList) {
            CartItem item = cartItemRepository.findCartItemById(idItem).orElseThrow(()
                    -> new ResourceNotFoundException(AppConstant.CART_ITEM_NOT_FOUND + idItem));
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

//            Set value for sold count product
            String productName = productInCart.getProductName();
            Product product = productRepository.findByProductName(productName).orElseThrow(()
                    -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND_WITH_NAME + productName));
            Integer soldCountCurrent = productInCart.getSoldCount();
            product.setSoldCount(soldCountCurrent + quantity);
            productRepository.save(product);

            orderItemRepository.save(orderItem);
        }
        resetCartUser(cartItemList, userId);
        return orderMapper.toDTO(orderBill);
    }

    @Override
    public OrderResponse approveOrder(Long orderId, Integer status) {
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
        Cart cartUser = cartRepository.findCartById(cardId).orElseThrow(()
                -> new ResourceNotFoundException(AppConstant.CART_NOT_FOUND + cardId));
        cartUser.setTotalPrice(totalCart);
        cartRepository.save(cartUser);
    }

    @Override
    @Transactional
    public List<OrderItemResponse> getAllListOrderItems(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrder_Id(orderId);
        orderItems.forEach(orderItem -> Hibernate.initialize(orderItem.getToppings()));
        return orderItems.stream().map(orderItem -> orderItemMapper.toDTO(orderItem)).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getAllListOrderOfUser(Long userId) {
        List<Order> orders = orderRepository.findByUserOrder_Id(userId);
        return orders.stream().map(order -> orderMapper.toDTO(order)).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getListOrderWaitingConfirmOfUser(Long userId) {
        List<Order> orders = orderRepository.findByUserOrder_Id(userId);
        if (orders != null) {
            Iterator<Order> iterator = orders.iterator();
            while (iterator.hasNext()) {
                Order item = iterator.next();
                if (item.getStatus() != 1) {
                    iterator.remove();
                }
            }
        }
        return orders.stream().map(order -> orderMapper.toDTO(order)).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getListOrderWaitingDeliveryOfUser(Long userId) {
        List<Order> orders = orderRepository.findByUserOrder_Id(userId);
        if (orders != null) {
            Iterator<Order> iterator = orders.iterator();
            while (iterator.hasNext()) {
                Order item = iterator.next();
                if (item.getStatus() != 2) {
                    iterator.remove();
                }
            }
        }
        return orders.stream().map(order -> orderMapper.toDTO(order)).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getListOrderSuccessOfUser(Long userId) {
        List<Order> orders = orderRepository.findByUserOrder_Id(userId);
        if (orders != null) {
            Iterator<Order> iterator = orders.iterator();
            while (iterator.hasNext()) {
                Order item = iterator.next();
                if (item.getStatus() != 3) {
                    iterator.remove();
                }
            }
        }
        return orders.stream().map(order -> orderMapper.toDTO(order)).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getListOrderCancelOfUser(Long userId) {
        List<Order> orders = orderRepository.findByUserOrder_Id(userId);
        if (orders != null) {
            Iterator<Order> iterator = orders.iterator();
            while (iterator.hasNext()) {
                Order item = iterator.next();
                if (item.getStatus() != 0) {
                    iterator.remove();
                }
            }
        }
        return orders.stream().map(order -> orderMapper.toDTO(order)).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getListOrderByStatus(Integer statusOrder) {
        List<Order> orders = orderRepository.findByStatus(statusOrder);
        return orders.stream().map(order -> orderMapper.toDTO(order)).collect(Collectors.toList());
    }
}
