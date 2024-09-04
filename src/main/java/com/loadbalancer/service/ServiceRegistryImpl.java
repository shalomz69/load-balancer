package com.loadbalancer.service;

import com.loadbalancer.model.Server;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ServiceRegistryImpl implements ServiceRegistry {
    private final Set<Server> servers = ConcurrentHashMap.newKeySet();

    @Override
    public Boolean registerServer(Server server) {
        return servers.add(server);
    }

    @Override
    public Boolean deRegisterServer(Server server) {
        return servers.remove(server);
    }

    @Override
    public List<Server> getServers() {
        return new ArrayList<>(servers);
    }
}
