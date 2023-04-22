package com.example.api_beverage_shop.service.wish;

import com.example.api_beverage_shop.dto.request.wish.AddWishRequest;
import com.example.api_beverage_shop.dto.response.wish.WishItemResponse;

import java.util.List;

public interface IWishService {
    WishItemResponse createWishItem(AddWishRequest wishRequest);

    List<WishItemResponse> getAllWishItemOfUser(Long userId);
}
