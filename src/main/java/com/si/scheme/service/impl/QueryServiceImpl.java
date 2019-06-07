package com.si.scheme.service.impl;

import com.si.scheme.service.QueryService;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class QueryServiceImpl implements QueryService {

    private final EventStore eventStore;

    @Autowired
    public QueryServiceImpl(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public List<Object> listEventsForScheme(UUID uuid) {
        return eventStore.readEvents(uuid.toString()).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
    }

}
