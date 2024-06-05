package com.example.controller;

import com.example.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Mock
    private Logger logger;

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    @Test
    void addCustomer() {
        // Arrange
        String customerData = "{}";
        Long adminId = 1L;

        // Act
        assertDoesNotThrow(() -> adminController.addCustomer(adminId, customerData));

        // Assert
        verify(adminService, times(1)).addCustomer(adminId, customerData);
    }

    @Test
    void deleteCustomer() {
        // Arrange
        Long adminId = 1L;
        Long customerId = 2L;

        // Act
        assertDoesNotThrow(() -> adminController.deleteCustomer(adminId, customerId));

        // Assert
        verify(adminService, times(1)).deleteCustomer(adminId, customerId);
    }

   

}
