package com.example.api_beverage_shop.service.cart;

import com.example.api_beverage_shop.dto.response.cart.CartItemResponse;
import com.example.api_beverage_shop.dto.request.cart.AddCartRequest;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.mapper.CartItemMapper;
import com.example.api_beverage_shop.model.*;
import com.example.api_beverage_shop.repository.*;
import com.example.api_beverage_shop.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private ICartItemRepository cartItemRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IToppingRepository toppingRepository;

    @Autowired
    private ISizeRepository sizeRepository;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public CartItemResponse creatNewProductInCart(AddCartRequest cartRequest) {
        Long userId = cartRequest.getUserId();
        String productName = cartRequest.getProductName();
        Integer quantity = cartRequest.getQuantity();
        List<String> toppingNameList = cartRequest.getToppingNameList();
        String sizeName = cartRequest.getSizeName();

        Long cartId = userId;
        Product product = productRepository.findByProductName(productName).orElseThrow(() ->
                new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND + productName));
        Cart cartUser = cartRepository.findCartById(cartId).orElseThrow(() ->
                new ResourceNotFoundException(AppConstant.CART_NOT_FOUND + cartId));
        List<Topping> toppingList = new ArrayList<>();
        for (String nameTopping : toppingNameList) {
            Topping topping = toppingRepository.findByToppingName(nameTopping).orElseThrow(() ->
                    new ResourceNotFoundException(AppConstant.TOPPING_NOT_FOUND + nameTopping));
            toppingList.add(topping);
        }
        Size size = sizeRepository.findBySizeName(sizeName).orElseThrow(() ->
                new ResourceNotFoundException(AppConstant.Size_NOT_FOUND + sizeName));

        BigDecimal quantityCovert = new BigDecimal(quantity);
        BigDecimal totalPriceProduct = (product.getPriceDefault()
                .add(BigDecimal.valueOf(size.getPricePlus()))).multiply(quantityCovert);
        Integer toppingPlus = 0;
        for (Topping toppingItem : toppingList) {
            toppingPlus = toppingPlus + toppingItem.getToppingPrice() * quantity;
        }
        BigDecimal totalPriceItem = totalPriceProduct.add(new BigDecimal(toppingPlus));

        CartItem cartItem = CartItem.builder().build();
        cartItem.setProduct(product);
        cartItem.setCart(cartUser);
        cartItem.setToppings(toppingList);
        cartItem.setSizeProduct(size);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPriceItem(totalPriceItem);
        cartItem.setTotalPriceProduct(totalPriceProduct);
        cartItem = cartItemRepository.save(cartItem);

//        Xử lý cập nhật lại tổng tiền trong cart
        BigDecimal totalPriceInCart = cartUser.getTotalPrice();
        cartUser.setTotalPrice(totalPriceInCart.add(totalPriceItem));
        cartRepository.save(cartUser);

//        logger.ser("errormap",cartItemMapper.toDTO(cartItem));
        return cartItemMapper.toDTO(cartItem);
    }

    @Override
    public List<CartItemResponse> getAllCartItemInfo(Long userId) {
        Cart cart = cartRepository.findCartById(userId).orElseThrow(() ->
                new ResourceNotFoundException(AppConstant.CART_NOT_FOUND + userId));
        List<CartItem> cartItemList = cartItemRepository.findCartItemByCart(cart);
        return cartItemList.stream().map(cartItem -> cartItemMapper.toDTO(cartItem)).collect(Collectors.toList());
    }


}
