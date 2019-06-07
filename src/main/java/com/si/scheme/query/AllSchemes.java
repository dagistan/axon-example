package com.si.scheme.query;

import com.si.scheme.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AllSchemes {

    private final UUID id;
    private final String name;
    private Status status;

    public AllSchemes(UUID id, String name) {
        this.id = id;
        this.name = name;
        status = Status.CREATED;
    }

    public void setSchemeActivated() {
        this.status = Status.ACTIVATED;
    }

    public void setSchemeUpdated() {
        this.status = Status.UPDATED;
    }

    public void setSchemeDeactivated() {
        this.status = Status.DEACTIVATED;
    }
}
