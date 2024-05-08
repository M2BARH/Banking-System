package com.m2bar.Banking.System.controller;

import com.m2bar.Banking.System.api.CustomersApi;
import com.m2bar.Banking.System.models.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController implements CustomersApi {
    @Override
    public ResponseEntity<Void> customersCustomerIdDelete(Long customerId) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> customersCustomerIdGet(Long customerId) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> customersCustomerIdPut(Long customerId, CustomerDto body) {
        return null;
    }

    @Override
    public ResponseEntity<List<CustomerDto>> customersGet() {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> customersIdentityNumberGet(Long identityNumber) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> customersPost(CustomerDto body) {
        return null;
    }
}
