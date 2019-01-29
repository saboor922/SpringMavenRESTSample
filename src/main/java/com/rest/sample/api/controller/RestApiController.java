package com.rest.sample.api.controller;

import com.rest.sample.api.domain.Quote;
import com.rest.sample.api.domain.Vehicle;
import com.rest.sample.api.error.ApiError;
import com.rest.sample.api.service.implementation.RestApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RestApiController {

    @Autowired
    private RestApiServiceImpl restApiService;

    @RequestMapping("/hello")
    public String home(@RequestParam(value = "name", defaultValue = "Guest") String name) {
        return "Welcome " + name + ". Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/randomClient", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Quote> randomClient() {
        try {
            return new ResponseEntity<>(restApiService.randomClient(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/vehicles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        try {
            return new ResponseEntity<>(restApiService.getAllVehicles(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/vehicles/{registration}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getVehicleByRegistration(@PathVariable("registration") String registration) {
        try {
            if (!registration.matches("^(?=.{1,7})(([a-zA-Z]?){1,3}(\\d){1,3}([a-zA-Z]?){1,3})$")) {
                return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "Malformed Registration Error", "UK Registrations must follow the following regex format: ^(?=.{1,7})(([a-zA-Z]?){1,3}(\\d){1,3}([a-zA-Z]?){1,3})$"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(restApiService.getVehicleByRegistrationNumber(registration), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/vehicles/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vehicle>> getVehicleByManufacturer(@RequestParam(value = "manufacturer", required = false) String manufacturer) {
        try {
            return new ResponseEntity<>(restApiService.getByManufacturer(manufacturer), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/vehicles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createVehicle(@RequestBody Vehicle vehicle) {
        try {
            if (!vehicle.getRegistrationNumber().matches("^(?=.{1,7})(([a-zA-Z]?){1,3}(\\d){1,3}([a-zA-Z]?){1,3})$")) {
                return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "Malformed Registration Error", "UK Registrations must follow the following regex format: ^(?=.{1,7})(([a-zA-Z]?){1,3}(\\d){1,3}([a-zA-Z]?){1,3})$"), HttpStatus.BAD_REQUEST);
            }
            if (Optional.ofNullable(restApiService.getVehicleByRegistrationNumber(vehicle.getRegistrationNumber())).isPresent()) {
                return new ResponseEntity<>(new ApiError(HttpStatus.CONFLICT, "Vehicle Registration already exists!", "Avoid duplicate vehicle registration entries. Else try updating the existing entry."), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(restApiService.createVehicle(vehicle), HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/vehicles/{registration}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateVehicleByRegistration(@RequestBody Vehicle vehicle, @PathVariable(value = "registration") String registration) {
        try {
            if (!registration.matches("^(?=.{1,7})(([a-zA-Z]?){1,3}(\\d){1,3}([a-zA-Z]?){1,3})$")) {
                return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "Malformed Registration Error", "UK Registrations must follow the following regex format: ^(?=.{1,7})(([a-zA-Z]?){1,3}(\\d){1,3}([a-zA-Z]?){1,3})$"), HttpStatus.BAD_REQUEST);
            }
            if (!Optional.ofNullable(restApiService.getVehicleByRegistrationNumber(registration)).isPresent()) {
                return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, "Vehicle with the given registration doesn't exist!", "Try using POST /api/vehicles resource instead."), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(restApiService.updateVehicle(vehicle, registration), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/vehicles/{registration}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteVehicleByRegistration(@PathVariable(value = "registration") String registration) {
        try {
            if (!registration.matches("^(?=.{1,7})(([a-zA-Z]?){1,3}(\\d){1,3}([a-zA-Z]?){1,3})$")) {
                return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "Malformed Registration Error", "UK Registrations must follow the following regex format: ^(?=.{1,7})(([a-zA-Z]?){1,3}(\\d){1,3}([a-zA-Z]?){1,3})$"), HttpStatus.BAD_REQUEST);
            }
            if (!Optional.ofNullable(restApiService.getVehicleByRegistrationNumber(registration)).isPresent()) {
                return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, "Vehicle with the given registration doesn't exist!", "Try using POST /api/vehicles resource instead."), HttpStatus.NOT_FOUND);
            } else {
                restApiService.deleteVehicle(registration);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
