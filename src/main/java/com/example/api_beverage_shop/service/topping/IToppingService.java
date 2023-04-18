package com.example.api_beverage_shop.service.topping;

import com.example.api_beverage_shop.dto.ToppingDTO;

import java.util.List;

public interface IToppingService {
    ToppingDTO createTopping(ToppingDTO toppingDTO);

    List<ToppingDTO> getAllToppingInfo();
}
