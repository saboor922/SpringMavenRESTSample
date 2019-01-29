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

import java.util.List;

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
    public List<Vehicle> getAllVehicles() {
        return vehicleRepositoryImpl.findAll();
    }

    @Override
    public Vehicle getVehicleByRegistrationNumber(String registrationNumber) {
        return vehicleRepositoryImpl.findByRegistrationNumberIgnoreCase(registrationNumber);
    }

    @Override
    public List<Vehicle> getByManufacturer(String manufacturer) {
        return vehicleRepositoryImpl.findByManufacturerIgnoreCase(manufacturer);
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepositoryImpl.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle, String registration) {
        return vehicleRepositoryImpl.updateVehicleByRegistration(vehicle, registration);
    }

    @Override
    public void deleteVehicle(String registration) {
        vehicleRepositoryImpl.deleteVehicleByRegistrationNumberIgnoreCase(registration);
    }


}
