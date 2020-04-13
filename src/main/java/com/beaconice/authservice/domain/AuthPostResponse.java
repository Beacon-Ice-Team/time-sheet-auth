package com.beaconice.authservice.domain;

import com.beaconice.authservice.entity.TimeSheetManagement;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthPostResponse {

    private TimeSheetManagement timeSheetManagement;

}
