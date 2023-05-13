package com.example.api_beverage_shop.controller.client;

import com.example.api_beverage_shop.dto.NotificationDTO;
import com.example.api_beverage_shop.service.notification.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class NotificationClientController {
    @Autowired
    private final INotificationService notificationService;

    @GetMapping("/notifications")
    public ResponseEntity<List<NotificationDTO>> getNotificationByStatus(@Param("status") Integer status) {
        return ResponseEntity.ok(notificationService.getNotificationByStatus(status));
    }
}
