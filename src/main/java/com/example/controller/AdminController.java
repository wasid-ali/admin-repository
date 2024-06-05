package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.AdminService;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AdminService adminService;

    @PostMapping("/{adminId}/customers")
    public void addCustomer(@PathVariable Long adminId, @RequestBody String customerData) {
        adminService.addCustomer(adminId, customerData);
    }

    @DeleteMapping("/{adminId}/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long adminId, @PathVariable Long customerId) {
        adminService.deleteCustomer(adminId, customerId);
    }

	
	  @GetMapping("/{adminId}/customers") public String listCustomers(@PathVariable
	  Long adminId) { 
		  LOG.info("ADMIN");
		  return adminService.listCustomers(adminId); }
	 
}