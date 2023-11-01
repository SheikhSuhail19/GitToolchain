package com.soprabanking.repositories;

import com.soprabanking.model.dto.AuditDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditRepository extends MongoRepository<AuditDto, String> {
}
