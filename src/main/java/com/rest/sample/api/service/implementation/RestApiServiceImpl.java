package com.rest.sample.api.service.implementation;

import com.rest.sample.api.domain.Quote;
import com.rest.sample.api.domain.Vehicle;
import com.rest.sample.api.repository.VehicleRepository;
import com.rest.sample.api.service.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:application.properties")
public class RestApiServiceImpl implements RestApiService {

    @Value("${randomUrl.path}")
    private String randomUrl;

    @Autowired
    private VehicleRepository vehicleRepositoryImpl;

    @Override
    public Quote randomClient() {
        return new RestTemplate().getForObject(randomUrl, Quote.class);
    }

    @Override
    public Vehicle findVehicleByRegistrationNumber(String registrationNumber) {
        return vehicleRepositoryImpl.findByRegistrationNumber(registrationNumber);
    }
}
