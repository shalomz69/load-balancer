package com.loadbalancer.web.rest;


import com.loadbalancer.model.Server;
import com.loadbalancer.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-registry")
public class ServiceRegistryResource {

    private final ServiceRegistry serviceRegistry;

    @Autowired
    public ServiceRegistryResource(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean registerServer(@RequestBody Server server) {
        return serviceRegistry.registerServer(server);
    }

    @PostMapping(value = "/deregister",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deRegisterServer(@RequestBody Server server) {
        return serviceRegistry.deRegisterServer(server);
    }

    @GetMapping(value= "/servers",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Server> getServers() {
        return serviceRegistry.getServers();
    }
}
