package com.si.scheme.service;

import java.util.List;
import java.util.UUID;

public interface QueryService {

    List<Object> listEventsForScheme(UUID uuid);
}
