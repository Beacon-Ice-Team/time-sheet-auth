package com.beaconice.authservice.service;

import com.beaconice.authservice.entity.TimeSheetManagement;

public interface AuthService {

    TimeSheetManagement getAuth(String username, String password);

}
