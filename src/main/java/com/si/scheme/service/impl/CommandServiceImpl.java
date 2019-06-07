package com.si.scheme.service.impl;

import com.si.scheme.command.CreateCommand;
import com.si.scheme.command.UpdateCommand;
import com.si.scheme.dto.SchemeDTO;
import com.si.scheme.dto.SchemeUpdateDTO;
import com.si.scheme.service.CommandService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class CommandServiceImpl implements CommandService {

    private final CommandGateway commandGateway;

    public CommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createScheme(SchemeDTO dto) {
        return commandGateway.send(new CreateCommand(UUID.randomUUID(), dto.getName(),
                dto.getCountry(), dto.getCity(), dto.getDistrict(), dto.getOperator()));
    }

    @Override
    public CompletableFuture<String> updateScheme(UUID uuid, SchemeUpdateDTO dto) {
        return commandGateway.send(new UpdateCommand(uuid, dto.getName(),
                dto.getCountry(), dto.getCity(), dto.getDistrict(), dto.getOperator()));
    }
}
