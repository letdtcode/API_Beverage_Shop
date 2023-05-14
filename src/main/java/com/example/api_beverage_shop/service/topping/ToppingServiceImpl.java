package com.example.api_beverage_shop.service.topping;

import com.example.api_beverage_shop.dto.ToppingDTO;
import com.example.api_beverage_shop.exception.ResourceExistException;
import com.example.api_beverage_shop.model.Topping;
import com.example.api_beverage_shop.repository.IToppingRepository;
import com.example.api_beverage_shop.util.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToppingServiceImpl implements IToppingService {

    @Autowired
    private IToppingRepository toppingRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public ToppingDTO createTopping(ToppingDTO toppingDTO) {
        String toppingName = toppingDTO.getToppingName();
        Optional<Topping> toppingTemp = toppingRepository.findByToppingName(toppingName);
        if (toppingTemp.isPresent()) {
            throw new ResourceExistException(AppConstant.TOPPING_EXIST);
        }

        Topping topping = Topping.builder().build();
        BeanUtils.copyProperties(toppingDTO, topping);
        topping = toppingRepository.save(topping);
        return mapper.map(topping, ToppingDTO.class);
    }

    @Override
    public List<ToppingDTO> getAllToppingInfo() {
        List<Topping> toppingList = toppingRepository.findAll();
        List<ToppingDTO> toppingDTOList = toppingList.stream().map(size
                -> mapper.map(size, ToppingDTO.class)).collect(Collectors.toList());
        return toppingDTOList;
    }


}
