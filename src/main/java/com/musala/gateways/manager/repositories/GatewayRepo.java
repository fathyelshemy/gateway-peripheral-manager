package com.musala.gateways.manager.repositories;

import com.musala.gateways.manager.entities.Gateway;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GatewayRepo extends CrudRepository<Gateway,Long> {
     Optional<Gateway> findGatewayBySerialNumber(String serialNumber);
}
