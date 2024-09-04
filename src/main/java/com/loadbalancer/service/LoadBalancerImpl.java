package com.loadbalancer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loadbalancer.model.Server;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
public class LoadBalancerImpl implements LoadBalancer {

    @Autowired
    private RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper to deserialize the response

    private final String serviceRegistryUrl = "http://localhost:8080/service-registry/servers";
    private final AtomicInteger roundRobinCounter = new AtomicInteger(0);
    private static final Server EMPTY_SERVER = new Server(-1, "empty");
    private final Random random = new Random();

    @Override
    public Server getNextServerRoundRobin() {
        final List<Server> servers = fetchServers();
        if (servers.isEmpty()) {
            return EMPTY_SERVER;
        }
        final int index = roundRobinCounter.getAndUpdate(i -> (i + 1) % servers.size());
        return servers.get(index);
    }

    @Override
    public Server getNextServerRandom() {
        final List<Server> servers = fetchServers();
        if (servers.isEmpty()) {
            return EMPTY_SERVER;
        }
        final int index = random.nextInt(servers.size());
        return servers.get(index);
    }

    private List<Server> fetchServers(){
        ResponseEntity<List<Server>> response = restTemplate.exchange(
                serviceRegistryUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Server>>() {}
        );
        return response.getBody();
    }
}

