package com.example.api_beverage_shop.service.notification;

import com.example.api_beverage_shop.dto.NotificationDTO;
import com.example.api_beverage_shop.model.Notification;
import com.example.api_beverage_shop.repository.INotificationRepository;
import com.example.api_beverage_shop.service.storage.ICloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class INotificationServiceImpl implements INotificationService {
    @Autowired
    private INotificationRepository notificationRepository;
    @Autowired
    private ICloudinaryService cloudinaryService;
    @Autowired
    private ModelMapper mapper;

    @Override
    public NotificationDTO createNotification(NotificationDTO notificationDTO, MultipartFile file) {
        Notification notification = mapper.map(notificationDTO, Notification.class);
        String pathImgDes = cloudinaryService.store(file);
        notification.setPathImgDescription(pathImgDes);
        notification = notificationRepository.save(notification);
        return mapper.map(notification, NotificationDTO.class);
    }

    @Override
    public List<NotificationDTO> getAllNotification() {
        List<Notification> notificationList = notificationRepository.findAll();
        return notificationList.stream().map(noti -> mapper.map(noti, NotificationDTO.class)).collect(Collectors.toList());
    }
}
