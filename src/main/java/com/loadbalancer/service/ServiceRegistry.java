package com.loadbalancer.service;

import com.loadbalancer.model.Server;

import java.util.List;


public interface ServiceRegistry {
    Boolean registerServer(Server server);
    Boolean deRegisterServer(Server server);
    List<Server> getServers();
}
