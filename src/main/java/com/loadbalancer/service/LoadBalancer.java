package com.loadbalancer.service;

import com.loadbalancer.model.Server;

public interface LoadBalancer {
    Server getNextServerRoundRobin();
    Server getNextServerRandom();
}
