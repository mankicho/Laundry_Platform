package com.coders.laundry.domain.entity;

import java.time.Instant;
import lombok.Data;

@Data
public class DeviceTokenEntity {

    private int memberId;

    private String token;

    private DeviceType type;

    private Instant createdAt;

    public enum DeviceType {
        Android, Apple
    }
}
