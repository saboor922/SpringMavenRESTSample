package com.rest.sample.api.repository;

import com.rest.sample.api.domain.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "vehicle", path = "/api/vehicle")
public interface VehicleRepository extends MongoRepository<Vehicle, String>, CustomVehicleRepository {

    Vehicle findByRegistrationNumberIgnoreCase(String registration_number);

    List<Vehicle> findByManufacturerIgnoreCase(String manufacturer);

    void deleteVehicleByRegistrationNumberIgnoreCase(String registration_number);

}
