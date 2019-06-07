package com.si.scheme.service;

import com.si.scheme.dto.SchemeDTO;
import com.si.scheme.dto.SchemeUpdateDTO;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface CommandService {

    CompletableFuture<String> createScheme(SchemeDTO dto);

    CompletableFuture<String> updateScheme(UUID uuid, SchemeUpdateDTO dto);
}
