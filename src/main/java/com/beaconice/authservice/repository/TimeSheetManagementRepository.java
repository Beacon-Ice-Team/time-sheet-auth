package com.beaconice.authservice.repository;

import com.beaconice.authservice.entity.TimeSheetManagement;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TimeSheetManagementRepository extends MongoRepository<TimeSheetManagement, String> {

    Optional<TimeSheetManagement> findByUsername(String username);

}
