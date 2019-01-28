package com.rest.sample.api.service;

import com.rest.sample.api.domain.Quote;
import com.rest.sample.api.domain.Vehicle;

public interface RestApiService {

    Quote randomClient();

    Vehicle findVehicleByRegistrationNumber(String registrationNumber);
}
