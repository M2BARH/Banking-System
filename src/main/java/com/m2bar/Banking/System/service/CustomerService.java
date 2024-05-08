package com.m2bar.Banking.System.service;

import com.m2bar.Banking.System.models.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto createCustomer(CustomerDto customer);

    CustomerDto getCustomerById(Long customerId);

    CustomerDto getCustomerByIdentityNumber(String identityNumber);

    List<CustomerDto> getAllCustomers();

    CustomerDto updateCustomer(Long customerId, CustomerDto customer);

    void deleteCustomer(Long customerId);
}
