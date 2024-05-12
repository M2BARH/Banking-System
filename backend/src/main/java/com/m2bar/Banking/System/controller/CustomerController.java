package com.m2bar.Banking.System.controller;

import com.m2bar.Banking.System.api.CustomersApi;
import com.m2bar.Banking.System.models.CustomerDto;
import com.m2bar.Banking.System.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CustomerController implements CustomersApi {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<Void> deleteCustomerById(Long customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete customer", ex);
        }
    }

    @Override
    public ResponseEntity<CustomerDto> getCustomerById(Long customerId) {
        try {
            return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve customer", ex);
        }
    }

    @Override
    public ResponseEntity<CustomerDto> updateCustomerById(Long customerId, CustomerDto body) {
        try {
            return new ResponseEntity<>(customerService.updateCustomer(customerId, body), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update customer", ex);
        }
    }

    @Override
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        try {
            return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve customers", ex);
        }
    }

    @Override
    public ResponseEntity<CustomerDto> getCustomerByIdentityNumber(String identityNumber) {
        try {
            return new ResponseEntity<>(customerService.getCustomerByIdentityNumber(identityNumber), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve customer by identity number", ex);
        }
    }

    @Override
    public ResponseEntity<CustomerDto> createCustomer(CustomerDto body) {
        try {
            return new ResponseEntity<>(customerService.createCustomer(body), HttpStatus.CREATED);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create customer", ex);
        }
    }
}
