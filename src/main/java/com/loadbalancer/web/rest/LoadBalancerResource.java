package com.loadbalancer.web.rest;


import com.loadbalancer.model.Server;
import com.loadbalancer.service.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/load-balancer")
public class LoadBalancerResource {

    private final LoadBalancer loadBalancer;

    @Autowired
    public LoadBalancerResource(LoadBalancer loadBalancer){
        this.loadBalancer = loadBalancer;
    }

    @GetMapping(value = "/select/server", produces = MediaType.APPLICATION_JSON_VALUE)
    public Server getServer(@RequestParam(required = false, defaultValue = "round-robin") String strategy)  {
        if ("round-robin".equalsIgnoreCase(strategy)) {
            return loadBalancer.getNextServerRoundRobin();
        } else if ("random".equalsIgnoreCase(strategy)) {
            return loadBalancer.getNextServerRandom();
        } else {
            throw new IllegalArgumentException("Invalid strategy parameter. Allowed values are 'round-robin' or 'random'.");
        }
    }
}
