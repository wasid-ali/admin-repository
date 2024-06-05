package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.Admin;
import com.example.repository.AdminRepository;

@Service
public class AdminService {

    private static final String CUSTOMER_SERVICE_URL = "http://localhost:8181/api/customers";

    @Autowired
    private RestTemplate restTemplate;

    public void addCustomer(Long adminId, String customerData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(customerData, headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(CUSTOMER_SERVICE_URL + "/add",
                HttpMethod.POST, requestEntity, Void.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to add customer");
        }
    }

    public void deleteCustomer(Long adminId, Long customerId) {
        ResponseEntity<Void> responseEntity = restTemplate.exchange(CUSTOMER_SERVICE_URL + "/delete/{customerId}",
                HttpMethod.DELETE, null, Void.class, customerId);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to delete customer");
        }
    }

    public String listCustomers(Long adminId) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(CUSTOMER_SERVICE_URL + "/list",
                HttpMethod.GET, null, String.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to list customers");
        }

        return responseEntity.getBody();
    }
}