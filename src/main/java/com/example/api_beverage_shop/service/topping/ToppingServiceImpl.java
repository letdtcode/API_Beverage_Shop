package com.example.api_beverage_shop.service.topping;

import com.example.api_beverage_shop.dto.ToppingDTO;
import com.example.api_beverage_shop.model.Topping;
import com.example.api_beverage_shop.repository.IToppingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToppingServiceImpl implements IToppingService {

    @Autowired
    private IToppingRepository toppingRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public ToppingDTO createTopping(ToppingDTO toppingDTO) {
        Topping topping = Topping.builder().build();
        BeanUtils.copyProperties(toppingDTO, topping);
        topping = toppingRepository.save(topping);
        return mapper.map(topping, ToppingDTO.class);
    }
}
