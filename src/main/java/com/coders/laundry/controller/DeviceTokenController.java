package com.coders.laundry.controller;

import com.coders.laundry.domain.dto.DeviceTokenSaveRequestDTO;
import com.coders.laundry.service.DeviceTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/device-token")
public class DeviceTokenController {

    private final DeviceTokenService service;

    @Autowired
    public DeviceTokenController(DeviceTokenService deviceTokenService) {
        this.service = deviceTokenService;
    }

    // TODO: 응답모델 적절하게 설계
    @PostMapping
    public HttpStatus save(
        @RequestBody DeviceTokenSaveRequestDTO requestDTO
    ) {
        int savedRow =  service.save(
            requestDTO.getMemberId(),
            requestDTO.getToken(),
            requestDTO.getDeviceType()
        );

        return savedRow > 0 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
