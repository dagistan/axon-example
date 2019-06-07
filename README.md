# SCHEME microservice

1. Scheme Aggregate
    * SchemeAggregate
2. Commands
    * CreateSchemeCommand
    * UpdateSchemeCommand
3. Events
    * SchemeCreatedEvent
    * SchemeActivatedEvent
    * SchemeUpdatedEvent
    * SchemeDeactivatedEvent
4. Queries / Projections
    * FinAllSchemesQuery & AllSchemesProjector

![AXON](https://axoniqio.cdn.prismic.io/axoniqio%2Fc2165cfe-6807-4eae-8d5d-e4483328506b_axon-desktop.svg)

### AXON Framework

pom.xml (add dependencies)

### AXON Server

```shell
$ docker run -d --name stage-axon-server -p 8024:8024 -p 8124:8124 axoniq/axonserver
```

> http://localhost:8024 for AXON server UI

> also Swagger UI for rest endpoints http://localhost:8080/swagger-ui.html

## Missings / ToDos

* Running specific queries in an easy way
* SAGA pattern to achieve BASE (**B**asic **A**vailability, **S**oft State, **E**ventual Consistency) for distributed transaction
* Multiple Aggregates
* Bounded Contexts
* Distributed Axon Server (money talks :) OR getting rid of Acon Server and replacing with MongoDB+RabbitMQ (Command side) and Postgres (Query side)
