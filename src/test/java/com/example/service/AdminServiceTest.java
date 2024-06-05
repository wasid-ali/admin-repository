package com.example.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AdminService adminService;

    @Test
    void addCustomer_Success() {
        // Mocking the successful response from the customer service
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(Void.class)))
                .thenReturn(responseEntity);

        // Call the method under test
        assertDoesNotThrow(() -> adminService.addCustomer(1L, "{}"));
    }

    @Test
    void addCustomer_Failure() {
        // Mocking a failure response from the customer service
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(Void.class)))
                .thenReturn(responseEntity);

        // Call the method under test and assert that it throws a RuntimeException
        assertThrows(RuntimeException.class, () -> adminService.addCustomer(1L, "{}"));
    }

    @Test
    void deleteCustomer_Success() {
        // Mocking the successful response from the customer service
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.DELETE), isNull(), eq(Void.class), anyLong()))
                .thenReturn(responseEntity);

        // Call the method under test
        assertDoesNotThrow(() -> adminService.deleteCustomer(1L, 2L));
    }

    @Test
    void deleteCustomer_Failure() {
        // Mocking a failure response from the customer service
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.DELETE), isNull(), eq(Void.class), anyLong()))
                .thenReturn(responseEntity);

        // Call the method under test and assert that it throws a RuntimeException
        assertThrows(RuntimeException.class, () -> adminService.deleteCustomer(1L, 2L));
    }

    @Test
    void listCustomers_Success() {
        // Mocking the successful response from the customer service
        ResponseEntity<String> responseEntity = new ResponseEntity<>("customerData", HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), isNull(), eq(String.class)))
                .thenReturn(responseEntity);

        // Call the method under test
        assertEquals("customerData", adminService.listCustomers(1L));
    }

    @Test
    void listCustomers_Failure() {
        // Mocking a failure response from the customer service
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), isNull(), eq(String.class)))
                .thenReturn(responseEntity);

        // Call the method under test and assert that it throws a RuntimeException
        assertThrows(RuntimeException.class, () -> adminService.listCustomers(1L));
    }
}

