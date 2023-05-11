package com.example.api_beverage_shop.service.notification;

import com.example.api_beverage_shop.dto.NotificationDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface INotificationService {
    NotificationDTO createNotification(NotificationDTO notificationDTO, MultipartFile file);

    List<NotificationDTO> getAllNotification();
}
