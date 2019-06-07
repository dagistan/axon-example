package com.si.scheme.controller;

import com.si.scheme.dto.SchemeDTO;
import com.si.scheme.dto.SchemeUpdateDTO;
import com.si.scheme.service.CommandService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/scheme")
@Api(value = "Scheme Commands")
public class CommandController {

    private final CommandService commandService;

    @Autowired
    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public CompletableFuture<String> createScheme(@RequestBody SchemeDTO dto) {
        return commandService.createScheme(dto);
    }

    @PutMapping(value = "/{uuid}")
    public CompletableFuture<String> updateScheme(@PathVariable(value = "uuid") UUID uuid,
                                                  @RequestBody SchemeUpdateDTO dto) {
        return commandService.updateScheme(uuid, dto);
    }

}
