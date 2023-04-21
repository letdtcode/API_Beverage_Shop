package com.example.api_beverage_shop.service.wish;

import com.example.api_beverage_shop.dto.request.wish.AddWishRequest;
import com.example.api_beverage_shop.dto.response.wish.WishItemResponse;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.mapper.WishItemMapper;
import com.example.api_beverage_shop.model.Product;
import com.example.api_beverage_shop.model.WishItem;
import com.example.api_beverage_shop.model.WishList;
import com.example.api_beverage_shop.repository.IProductRepository;
import com.example.api_beverage_shop.repository.IWishItemRepository;
import com.example.api_beverage_shop.repository.IWishListRepository;
import com.example.api_beverage_shop.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishServiceImpl implements IWishService {

    @Autowired
    private IWishListRepository wishListRepository;

    @Autowired
    private IWishItemRepository wishItemRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private WishItemMapper wishItemMapper;

    @Override
    public WishItemResponse createWishItem(AddWishRequest wishRequest) {
        Long userId = wishRequest.getUserId();
        Long wishListId = userId;
        String productName = wishRequest.getProductName();

        Product product = productRepository.findByProductName(productName).orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND_WITH_NAME + productName));
        WishList wishListUser = wishListRepository.findWishListById(wishListId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.WISH_LIST_NOT_FOUND + wishListId));

        WishItem wishItemSave;
        Optional<WishItem> wishItem = wishItemRepository.findByProductAndWishList(product, wishListUser);
        if (wishItem.isPresent()) {
            wishItemSave = changeWishItem(wishItem.get(), true);
        } else {
            wishItemSave = WishItem.builder().product(product).status(1).wishList(wishListUser).build();
            wishItemSave = wishItemRepository.save(wishItemSave);
        }
        return wishItemMapper.toDTO(wishItemSave);
    }

    public WishItem changeWishItem(WishItem wishItem, boolean change) {
        if (wishItem.getStatus() == 0) {
            wishItem.setStatus(1);
        } else {
            wishItem.setStatus(0);
        }
        wishItem = wishItemRepository.save(wishItem);
        return wishItem;
    }
}
