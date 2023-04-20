package com.example.api_beverage_shop.mapper;

import com.example.api_beverage_shop.dto.response.CartItemResponse;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.*;
import com.example.api_beverage_shop.repository.ICartRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartItemMapper {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IToppingRepository toppingRepository;

    @Autowired
    private ISizeRepository sizeRepository;

    @Autowired
    private ICartRepository cartRepository;

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
    //     Convert Cart
    private final Converter<Cart, Long> cartToCartIdConverter = context -> {
        Cart cart = context.getSource();
        return cart != null ? cart.getId() : null;
    };
    private final Converter<Long, Cart> cartIdToCartConverter = mappingContext -> {
        Long id = mappingContext.getSource();
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppConstant.CART_NOT_FOUND + id));
        return cart;
    };

    @PostConstruct
    public void init() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.createTypeMap(CartItem.class, CartItemResponse.class)
                .addMappings(mapper -> mapper.using(productToProductNameConverter)
                        .map(CartItem::getProduct, CartItemResponse::setProductName))

                .addMappings(mapper -> mapper.using(toppingToToppingNameConverter)
                        .map(CartItem::getToppings, CartItemResponse::setToppingName))

                .addMappings(mapper -> mapper.using(sizeToSizeNameConverter)
                        .map(CartItem::getSizeProduct, CartItemResponse::setSizeName))

                .addMappings(mapper -> mapper.using(cartToCartIdConverter)
                        .map(CartItem::getCart, CartItemResponse::setCartId));


        mapper.createTypeMap(CartItemResponse.class, CartItem.class)
                .setPropertyCondition(Conditions.isNotNull())
                .addMappings(mapper -> mapper.using(productNameToProductConverter)
                        .map(CartItemResponse::getProductName, CartItem::setProduct))

                .addMappings(mapper -> mapper.using(toppingNameToToppingConverter)
                        .map(CartItemResponse::getToppingName, CartItem::setToppings))

                .addMappings(mapper -> mapper.using(sizeNameToSizeConverter)
                        .map(CartItemResponse::getProductName, CartItem::setProduct));
    }

    public CartItemResponse toDTO(CartItem cartItem) {
        CartItemResponse cartItemResponse;
        cartItemResponse = mapper.map(cartItem, CartItemResponse.class);

//        productDTO.setUrlImgProduct(buildImgProductUrl(product.getId()));
        return cartItemResponse;
    }

    public CartItem toEntity(CartItemResponse dto) {
        return mapper.map(dto, CartItem.class);
    }
}
