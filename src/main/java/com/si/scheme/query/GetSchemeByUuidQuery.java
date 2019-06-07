package com.si.scheme.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class GetSchemeByUuidQuery {
    private final UUID uuid;
}
