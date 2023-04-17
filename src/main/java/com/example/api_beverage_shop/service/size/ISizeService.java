package com.example.api_beverage_shop.service.size;

import com.example.api_beverage_shop.dto.SizeDTO;

import java.util.List;

public interface ISizeService {
    public SizeDTO createSize(SizeDTO sizeDTO);

    List<SizeDTO> getAllSizeInfo();
}
