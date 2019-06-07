package com.si.scheme.query;

import com.si.scheme.event.SchemeActivatedEvent;
import com.si.scheme.event.SchemeCreatedEvent;
import com.si.scheme.event.SchemeDeactivatedEvent;
import com.si.scheme.event.SchemeUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AllSchemesProjector {

    private final Map<UUID, AllSchemes> allSchemesMap = new HashMap<>();

    @EventHandler
    public void on(SchemeCreatedEvent event) {
        UUID id = event.getId();
        allSchemesMap.put(id, new AllSchemes(id, event.getName()));
    }

    @EventHandler
    public void on(SchemeActivatedEvent event) {
        allSchemesMap.computeIfPresent(event.getId(), ((uuid, allSchemesMap) -> {
            allSchemesMap.setSchemeActivated();
            return allSchemesMap;
        }));
    }

    @EventHandler
    public void on(SchemeUpdatedEvent event) {
        allSchemesMap.computeIfPresent(event.getId(), ((uuid, allSchemesMap) -> {
            allSchemesMap.setSchemeUpdated();
            return allSchemesMap;
        }));
    }

    @EventHandler
    public void on(SchemeDeactivatedEvent event) {
        allSchemesMap.computeIfPresent(event.getId(), ((uuid, allSchemesMap) -> {
            allSchemesMap.setSchemeDeactivated();
            return allSchemesMap;
        }));
    }

    @QueryHandler
    public List<AllSchemes> handle(FindAllSchemesQuery query) {
        return new ArrayList<>(allSchemesMap.values());
    }


}
