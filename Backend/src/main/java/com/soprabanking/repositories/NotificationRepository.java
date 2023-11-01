package com.soprabanking.repositories;

import com.soprabanking.model.dto.MRNotificationDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<MRNotificationDto, String> {
    List<MRNotificationDto> findByState(String state);
}
