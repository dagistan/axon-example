package com.si.scheme.query;

import com.si.scheme.aggregate.SchemeAggregate;
import com.si.scheme.event.SchemeCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class SchemeByUuidProjector {

    private final Repository<SchemeAggregate> repository;
    private final Map<UUID, AllSchemes> allSchemesMap = new HashMap<>();

    public SchemeByUuidProjector(Repository<SchemeAggregate> repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(SchemeCreatedEvent event) {
        UUID id = event.getId();
        allSchemesMap.put(id, new AllSchemes(id, event.getName()));
    }

    @QueryHandler
    public SchemeAggregate handle(GetSchemeByUuidQuery query) throws InterruptedException, ExecutionException {
        CompletableFuture<SchemeAggregate> future = new CompletableFuture<SchemeAggregate>();
        repository.load("" + query.getUuid()).execute(future::complete);
        return future.get();
    }

}
