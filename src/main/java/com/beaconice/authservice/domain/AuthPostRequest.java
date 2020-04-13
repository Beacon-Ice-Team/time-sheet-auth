package com.beaconice.authservice.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthPostRequest {

    private String username;

    private String password;

}
