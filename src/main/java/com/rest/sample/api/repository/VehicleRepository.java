package com.rest.sample.api.repository;

import com.rest.sample.api.domain.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "vehicle", path = "/api/vehicle")
public interface VehicleRepository extends MongoRepository<Vehicle, String> {

    public Vehicle findByRegistrationNumber(String registration_number);

    public List<Vehicle> findByManufacturer(String manufacturer);

    public List<Vehicle> findByCategory(String category);

    public List<Vehicle> findByYear(String year);

}
