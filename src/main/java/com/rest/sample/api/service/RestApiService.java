package com.rest.sample.api.service;

import com.rest.sample.api.domain.Quote;
import com.rest.sample.api.domain.Vehicle;

import java.util.List;

public interface RestApiService {

    Quote randomClient();

    List<Vehicle> getAllVehicles();

    Vehicle getVehicleByRegistrationNumber(String registrationNumber);

    List<Vehicle> getByManufacturer(String manufacturer);

    Vehicle createVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Vehicle vehicle, String registration);

    void deleteVehicle(String registration);
}
