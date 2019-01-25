package com.rest.sample.api.service.implementation;

import com.rest.sample.api.domain.Quote;
import com.rest.sample.api.service.RestApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:application.properties")
public class RestApiServiceImpl implements RestApiService {

    @Value("${randomUrl.path}")
    private String randomUrl;

    @Override
    public ResponseEntity<Quote> random() {
        return new ResponseEntity<Quote>(new RestTemplate().getForObject(randomUrl, Quote.class), HttpStatus.OK);
    }
}
