package com.si.scheme.aggregate;

import com.si.scheme.Status;
import com.si.scheme.command.CreateCommand;
import com.si.scheme.command.UpdateCommand;
import com.si.scheme.event.SchemeActivatedEvent;
import com.si.scheme.event.SchemeCreatedEvent;
import com.si.scheme.event.SchemeDeactivatedEvent;
import com.si.scheme.event.SchemeUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
public class SchemeAggregate {

    @AggregateIdentifier
    private UUID id;
    private String name;
    private String country;
    private String city;
    private String district;
    private String operator;
    private Status status;

    protected SchemeAggregate() {
        // for Axon to instantiate Aggregate instance
    }

    @CommandHandler
    public SchemeAggregate(CreateCommand cmd) {
        apply(new SchemeCreatedEvent(cmd.getId(), cmd.getName(), cmd.getCountry(), cmd.getCity(), cmd.getDistrict(),
                cmd.getOperator(), LocalDateTime.now()));
    }

    @EventSourcingHandler
    protected void on(SchemeCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.country = event.getCountry();
        this.city = event.getCity();
        this.district = event.getDistrict();
        this.operator = event.getOperator();
        this.status = Status.CREATED;

        apply(new SchemeActivatedEvent(this.id, Status.ACTIVATED, LocalDateTime.now()));
    }

    @EventSourcingHandler
    protected void on(SchemeActivatedEvent schemeActivatedEvent) {
        this.status = schemeActivatedEvent.getStatus();
    }

    @CommandHandler
    protected void handle(UpdateCommand cmd) {
        apply(new SchemeUpdatedEvent(cmd.getId(), cmd.getCountry(), cmd.getCity(), cmd.getDistrict(),
                cmd.getOperator(), LocalDateTime.now()));
    }

    @EventSourcingHandler
    protected void on(SchemeUpdatedEvent event) {
        this.id = event.getId();
        this.country = event.getCountry();
        this.city = event.getCity();
        this.district = event.getDistrict();
        this.operator = event.getOperator();
        this.status = Status.UPDATED;

        // for example if all fields are set to null/empty then we deactivate this account
        if (null == event.getCountry() && null == event.getCity() && null == event.getDistrict() && null == event.getOperator()) {
            apply(new SchemeDeactivatedEvent(this.id, Status.DEACTIVATED, LocalDateTime.now()));
        }
    }

    @EventSourcingHandler
    protected void on(SchemeDeactivatedEvent schemeDeactivatedEvent) {
        this.status = schemeDeactivatedEvent.getStatus();
    }

}
