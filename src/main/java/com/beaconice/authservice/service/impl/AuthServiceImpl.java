package com.beaconice.authservice.service.impl;

import com.beaconice.authservice.entity.TimeSheetManagement;
import com.beaconice.authservice.repository.TimeSheetManagementRepository;
import com.beaconice.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private TimeSheetManagementRepository timeSheetManagementRepository;

    @Autowired
    public void setTimeSheetManagementRepository(TimeSheetManagementRepository timeSheetManagementRepository) {
        this.timeSheetManagementRepository = timeSheetManagementRepository;
    }

    @Override
    public TimeSheetManagement getAuth(String username, String password) {
        TimeSheetManagement timeSheetManagement = timeSheetManagementRepository.findByUsername(username).orElse(null);
        if (timeSheetManagement != null && !timeSheetManagement.getPassword().equals(password)){
            return null;
        }else {
            return timeSheetManagement;
        }
    }

}
