package com.si.scheme.command;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateCommand extends BaseCommand<UUID> {

    private final String name;
    private final String country;
    private final String city;
    private final String district;
    private final String operator;

    public CreateCommand(UUID id, String name, String country, String city, String district, String operator) {
        super(id);
        this.name = name;
        this.country = country;
        this.city = city;
        this.district = district;
        this.operator = operator;
    }
}
