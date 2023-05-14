package com.example.api_beverage_shop.service.notification;

import com.example.api_beverage_shop.dto.NotificationDTO;
import com.example.api_beverage_shop.exception.ResourceNotFoundException;
import com.example.api_beverage_shop.model.Notification;
import com.example.api_beverage_shop.repository.INotificationRepository;
import com.example.api_beverage_shop.service.storage.ICloudinaryService;
import com.example.api_beverage_shop.util.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements INotificationService {
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

    @Override
    public List<NotificationDTO> getNotificationByStatus(Integer status) {
        List<Notification> notificationList = notificationRepository.findByStatus(status);
        return notificationList.stream().map(noti ->
                mapper.map(noti, NotificationDTO.class)).collect(Collectors.toList());
    }
    @Override
    public List<NotificationDTO> updateStatus(List<NotificationDTO> notificationList) {
        List<Notification> updatedNotification = new ArrayList<>();
        for (NotificationDTO notificationDTO : notificationList) {
            Long idNotification = notificationDTO.getId();
            Notification itemNotification = notificationRepository.findById(notificationDTO.getId()).orElseThrow(()
                    -> new ResourceNotFoundException(AppConstant.NOTIFICATION_NOT_FOUND + idNotification));
            itemNotification.setStatus(notificationDTO.getStatus());
            updatedNotification.add(notificationRepository.save(itemNotification));
        }
        return updatedNotification.stream().map(noti -> mapper.map(noti, NotificationDTO.class)).collect(Collectors.toList());
    }


}
