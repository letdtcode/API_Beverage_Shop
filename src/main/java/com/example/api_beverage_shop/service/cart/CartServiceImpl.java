package com.example.api_beverage_shop.service.cart;

import com.example.api_beverage_shop.dto.CartDTO;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.*;
import com.example.api_beverage_shop.repository.*;
import com.example.api_beverage_shop.util.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    private ModelMapper mapper;

    @Override
    public CartDTO creatNewProductInCart(Long userId, String productName, Integer quantity, List<String> toppingNameList, String sizeName) {
        Long cartId = userId;
        Product product = productRepository.findByProductName(productName).orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND + productName));
        Cart cartUser = cartRepository.findCartById(cartId);
        List<Topping> toppingList = new ArrayList<>();
        for (String nameTopping : toppingNameList) {
            Topping topping = toppingRepository.findByToppingName(nameTopping);
            toppingList.add(topping);
        }
        Size size = sizeRepository.findBySizeName(sizeName);


        BigDecimal quantityCovert = new BigDecimal(quantity);
        BigDecimal totalPriceProduct = (product.getPriceDefault().add(BigDecimal.valueOf(size.getPricePlus()))).multiply(quantityCovert);
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

        Cart cart = cartRepository.findCartById(cartId);
        return mapper.map(cart, CartDTO.class);
    }
}
