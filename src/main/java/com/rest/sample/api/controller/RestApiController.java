package com.rest.sample.api.controller;

import com.rest.sample.api.domain.Quote;
import com.rest.sample.api.domain.Vehicle;
import com.rest.sample.api.service.implementation.RestApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/vehicle/{registration}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> getVehicleByRegistration(@PathVariable("registration") String registration) {
        try {
            return new ResponseEntity<>(restApiService.findVehicleByRegistrationNumber(registration), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
