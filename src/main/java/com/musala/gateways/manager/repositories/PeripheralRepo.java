package com.musala.gateways.manager.repositories;

import com.musala.gateways.manager.entities.Peripheral;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeripheralRepo extends CrudRepository<Peripheral,Integer> {

}
