package com.example.api_beverage_shop.service.wish;

import com.example.api_beverage_shop.dto.request.wish.AddWishRequest;
import com.example.api_beverage_shop.dto.response.wish.WishItemResponse;

public interface IWishService {
    WishItemResponse createWishItem(AddWishRequest wishRequest);
}
