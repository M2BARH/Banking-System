package com.m2bar.Banking.System.service.impl;

import com.m2bar.Banking.System.models.CustomerDto;
import com.m2bar.Banking.System.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto createCustomer(CustomerDto customer) {
        return null;
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        return null;
    }

    @Override
    public CustomerDto getCustomerByIdentityNumber(String identityNumber) {
        return null;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return null;
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customer) {
        return null;
    }

    @Override
    public void deleteCustomer(Long customerId) {

    }
}
