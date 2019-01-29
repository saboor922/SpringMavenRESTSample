package com.rest.sample.api.repository.implementation;

import com.rest.sample.api.domain.Vehicle;
import com.rest.sample.api.repository.CustomVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class CustomVehicleRepositoryImpl implements CustomVehicleRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Vehicle updateVehicleByRegistration(Vehicle vehicle, String registration) {

        Query query = new Query().addCriteria(Criteria.where("registration_number").is(vehicle.getRegistrationNumber()));
        mongoTemplate.update(Vehicle.class).matching(query).apply(new Update()
                .set("registration_number", registration)
                .set("manufacturer", vehicle.getManufacturer())
                .set("model", vehicle.getModel())
                .set("sub_model", vehicle.getSubModel())
                .set("year", vehicle.getYear())
                .set("category", vehicle.getCategory())).first();
        return vehicle;
    }

}
