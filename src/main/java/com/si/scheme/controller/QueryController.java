package com.si.scheme.controller;

import com.si.scheme.query.AllSchemes;
import com.si.scheme.query.FindAllSchemesQuery;
import com.si.scheme.query.GetSchemeByUuidQuery;
import com.si.scheme.service.QueryService;
import io.swagger.annotations.Api;
import io.swagger.models.Scheme;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/scheme")
@Api(value = "Scheme Queries")
public class QueryController {

    private final QueryService queryService;

    private final QueryGateway queryGateway;

    @Autowired
    public QueryController(QueryService queryService, QueryGateway queryGateway) {
        this.queryService = queryService;
        this.queryGateway = queryGateway;
    }

    @GetMapping("/{uuid}")
    public List<Object> listSchemesByUUIDUsingEventStore(@PathVariable(value = "uuid") String uuid) {
        return queryService.listEventsForScheme(UUID.fromString(uuid));
    }

    @GetMapping("/scheme/{uuid}")
    public Object listSchemesByUUIDUsingQueryGateway(@PathVariable UUID uuid) throws ExecutionException,
            InterruptedException {
        return queryGateway.query(new GetSchemeByUuidQuery(uuid), Scheme.class).get();
    }

    @GetMapping("/scheme")
    public List<AllSchemes> findAllOrderedProductsUsingQueryGateway() {
        return queryGateway.query(new FindAllSchemesQuery(),
                ResponseTypes.multipleInstancesOf(AllSchemes.class))
                .join();
    }

}
