package com.coders.laundry.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class DeviceTokenSaveRequestDTO {

    private int memberId;

    private String token;

    private String deviceType;
}
