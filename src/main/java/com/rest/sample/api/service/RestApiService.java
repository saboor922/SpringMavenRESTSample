package com.rest.sample.api.service;

import com.rest.sample.api.domain.Quote;
import org.springframework.http.ResponseEntity;

public interface RestApiService {

    ResponseEntity<Quote> randomClient();
}
