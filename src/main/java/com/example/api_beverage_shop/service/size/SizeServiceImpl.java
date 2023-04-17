package com.example.api_beverage_shop.service.size;

import com.example.api_beverage_shop.dto.SizeDTO;
import com.example.api_beverage_shop.exception.ResourceExistException;
import com.example.api_beverage_shop.model.Size;
import com.example.api_beverage_shop.repository.ISizeRepository;
import com.example.api_beverage_shop.util.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SizeServiceImpl implements ISizeService {
    @Autowired
    private ISizeRepository sizeRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public SizeDTO createSize(SizeDTO sizeDTO) {
        String sizeName = sizeDTO.getSizeName();
        Optional<Size> sizeTemp = sizeRepository.findBySizeName(sizeName);
        if (sizeTemp.isPresent()) {
            throw new ResourceExistException(AppConstant.SIZE_EXIST);
        }
        Size size = Size.builder().build();
        BeanUtils.copyProperties(sizeDTO, size);
        size = sizeRepository.save(size);
        return mapper.map(size, SizeDTO.class);
    }

    @Override
    public List<SizeDTO> getAllSizeInfo() {
        List<Size> sizeList = sizeRepository.findAll();
        List<SizeDTO> sizeDTOList = sizeList.stream().map(size -> mapper.map(size, SizeDTO.class)).collect(Collectors.toList());
        return sizeDTOList;
    }
}
