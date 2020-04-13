package com.beaconice.authservice.controller;

import com.beaconice.authservice.domain.AuthPostRequest;
import com.beaconice.authservice.domain.AuthPostResponse;
import com.beaconice.authservice.entity.TimeSheetManagement;
import com.beaconice.authservice.security.util.CookieUtil;
import com.beaconice.authservice.security.util.JwtUtil;
import com.beaconice.authservice.service.AuthService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static com.beaconice.authservice.constant.Constant.JWT_TOKEN_COOKIE_NAME;
import static com.beaconice.authservice.constant.Constant.SIGNING_KEY;


@RestController
@RequestMapping("/auth")
@Api("Auth Controller")
public class AuthController {

    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping(value = "/post")
    public ResponseEntity<Object> postAuth(HttpServletResponse httpServletResponse, @RequestBody AuthPostRequest authPostRequest) {
        ResponseEntity<Object> responseEntity;
        AuthPostResponse authPostResponse = new AuthPostResponse();

        String username = authPostRequest.getUsername();
        String password = authPostRequest.getPassword();

        TimeSheetManagement timeSheetManagement = authService.getAuth(username, password);

        if (timeSheetManagement == null) {
            responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("unauthorized");
        } else {
            String token = JwtUtil.generateToken(SIGNING_KEY, String.valueOf(username));
            CookieUtil.create(httpServletResponse, JWT_TOKEN_COOKIE_NAME, token, false, -1, "localhost");

            authPostResponse.setTimeSheetManagement(timeSheetManagement);

            responseEntity = ResponseEntity.ok()
                    .body(authPostResponse);
        }
        return responseEntity;
    }

}
