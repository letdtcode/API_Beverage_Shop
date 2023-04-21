package com.example.api_beverage_shop.mapper;

import com.example.api_beverage_shop.dto.response.wish.WishItemResponse;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.Product;
import com.example.api_beverage_shop.model.WishItem;
import com.example.api_beverage_shop.model.WishList;
import com.example.api_beverage_shop.repository.IProductRepository;
import com.example.api_beverage_shop.repository.IWishListRepository;
import com.example.api_beverage_shop.util.AppConstant;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WishItemMapper {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IWishListRepository wishListRepository;

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

    //     Convert Wish List
    private final Converter<WishList, Long> wishListToWishListIdConverter = context -> {
        WishList wishList = context.getSource();
        return wishList != null ? wishList.getId() : null;
    };
    private final Converter<Long, WishList> wishListIdToWishListConverter = mappingContext -> {
        Long id = mappingContext.getSource();
        WishList wishList = wishListRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppConstant.WISH_LIST_NOT_FOUND + id));
        return wishList;
    };

    @PostConstruct
    public void init() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.createTypeMap(WishItem.class, WishItemResponse.class)
                .addMappings(mapper -> mapper.using(productToProductNameConverter)
                        .map(WishItem::getProduct, WishItemResponse::setProductName))

                .addMappings(mapper -> mapper.using(wishListToWishListIdConverter)
                        .map(WishItem::getWishList, WishItemResponse::setWishListId));


        mapper.createTypeMap(WishItemResponse.class, WishItem.class)
                .setPropertyCondition(Conditions.isNotNull())
                .addMappings(mapper -> mapper.using(productNameToProductConverter)
                        .map(WishItemResponse::getProductName, WishItem::setProduct))

                .addMappings(mapper -> mapper.using(wishListIdToWishListConverter)
                        .map(WishItemResponse::getWishListId, WishItem::setWishList));
    }

    public WishItemResponse toDTO(WishItem wishItem) {
        WishItemResponse wishItemResponse;
        wishItemResponse = mapper.map(wishItem, WishItemResponse.class);
        return wishItemResponse;
    }

    public WishItem toEntity(WishItemResponse dto) {
        return mapper.map(dto, WishItem.class);
    }
}
