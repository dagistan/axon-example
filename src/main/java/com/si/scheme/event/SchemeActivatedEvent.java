package com.si.scheme.event;

import com.si.scheme.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SchemeActivatedEvent extends BaseEvent<UUID> {

    private final Status status;
    private LocalDateTime time;

    public SchemeActivatedEvent(UUID id, Status status, LocalDateTime time) {
        super(id);
        this.status = status;
        this.time = time;
    }
}
