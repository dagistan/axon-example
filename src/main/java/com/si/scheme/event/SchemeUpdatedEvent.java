package com.si.scheme.event;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SchemeUpdatedEvent extends BaseEvent<UUID> {

    private final String country;
    private final String city;
    private final String district;
    private final String operator;
    private LocalDateTime time;

    public SchemeUpdatedEvent(UUID id, String country, String city, String district, String operator,
                              LocalDateTime time) {
        super(id);
        this.country = country;
        this.city = city;
        this.district = district;
        this.operator = operator;
        this.time = time;
    }
}
