package com.rest.sample.api.repository;

import com.rest.sample.api.domain.Vehicle;

public interface CustomVehicleRepository {

    Vehicle updateVehicleByRegistration(Vehicle vehicle, String registration);
}
