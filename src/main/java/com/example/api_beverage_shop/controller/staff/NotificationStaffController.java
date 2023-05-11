package com.example.api_beverage_shop.controller.staff;

import com.example.api_beverage_shop.dto.NotificationDTO;
import com.example.api_beverage_shop.service.notification.INotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
public class NotificationStaffController {

    @Autowired
    private final INotificationService notificationService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/notifications")
    public ResponseEntity<NotificationDTO> createNotification(
            @RequestParam("file") MultipartFile file,
            @RequestParam("model") String JsonObject) throws JsonProcessingException {
        NotificationDTO notification = objectMapper.readValue(JsonObject, NotificationDTO.class);
        return ResponseEntity.ok(notificationService.createNotification(notification, file));
    }
}
