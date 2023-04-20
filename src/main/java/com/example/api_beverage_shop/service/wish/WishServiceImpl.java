package com.example.api_beverage_shop.service.wish;

import com.example.api_beverage_shop.repository.IWishItemRepository;
import com.example.api_beverage_shop.repository.IWishListRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class WishServiceImpl implements IWishService {

    @Autowired
    private IWishListRepository wishListRepository;

    @Autowired
    private IWishItemRepository wishItemRepository;

}
