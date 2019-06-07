package com.si.scheme.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SchemeUpdateDTO {

    private String name;
    private String country;
    private String city;
    private String district;
    private String operator;
    private LocalDateTime time;
}
