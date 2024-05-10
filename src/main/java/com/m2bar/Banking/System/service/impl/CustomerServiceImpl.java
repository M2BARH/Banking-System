package com.m2bar.Banking.System.service.impl;

import com.m2bar.Banking.System.mapper.CustomerMapper;
import com.m2bar.Banking.System.models.CustomerDto;
import com.m2bar.Banking.System.repository.CustomerRepository;
import com.m2bar.Banking.System.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customer) {
        if (customer != null) {
            if (customer.getIdentityNumber() == null || customer.getIdentityNumber().isEmpty()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Identity Number can not be empty"
                );
            }

            if (customer.getIdentityNumber().length() != 13) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Identity Number must be 13 digits"
                );
            }

            if (customer.getPhoneNumber() == null || customer.getPhoneNumber().isEmpty()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Phone Number can not be empty"
                );
            }

            if (customer.getPhoneNumber().length() == 12 && customer.getPhoneNumber().startsWith("27")) {
                String formattedNumber = customer.getPhoneNumber().replace("27", "0");
                customer.setPhoneNumber(formattedNumber);
            }

            if (customer.getPhoneNumber().length() == 13 && customer.getPhoneNumber().startsWith("+27")) {
                String formattedNumber = customer.getPhoneNumber().replace("+27", "0");
                customer.setPhoneNumber(formattedNumber);
            }

            if (customer.getPhoneNumber().length() != 10 || customer.getPhoneNumber().length() != 12 || customer.getPhoneNumber().length() != 13) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Invalid Phone Number format. \n Valid formats: 0608764319, +27608764319 & 27608764319"
                );
            }

            if (customer.getAlternateNumber() == null || customer.getAlternateNumber().isEmpty()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Alternate Phone Number can not be empty"
                );
            }

            if (customer.getAlternateNumber().length() == 12 && customer.getAlternateNumber().startsWith("27")) {
                String formattedNumber = customer.getAlternateNumber().replace("27", "0");
                customer.setAlternateNumber(formattedNumber);
            }

            if (customer.getAlternateNumber().length() == 13 && customer.getAlternateNumber().startsWith("+27")) {
                String formattedNumber = customer.getAlternateNumber().replace("+27", "0");
                customer.setAlternateNumber(formattedNumber);
            }

            if (customer.getAlternateNumber().length() != 10 || customer.getAlternateNumber().length() != 12 || customer.getAlternateNumber().length() != 13) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Invalid Alternate Phone Number format. \n Valid formats: 0608764319, +27608764319 & 27608764319"
                );
            }

            if (customer.getDateOfBirth() == null || !isValidDateFormat(customer.getDateOfBirth().toString())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Invalid Date of Birth format. \n Valid format: yyyy-MM-dd"
                );
            }

            return CustomerMapper.CUSTOMER_MAPPER.entityToDto(
                    customerRepository.save(
                            CustomerMapper.CUSTOMER_MAPPER.dtoToEntity(
                                    customer
                            )
                    )
            );
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Customer object is null"
            );
        }
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        return CustomerMapper.CUSTOMER_MAPPER.entityToDto(
                customerRepository.findById(customerId)
                        .orElseThrow(() -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, String.format("Customer with id %d not found", customerId)
                                )
                        )
        );
    }

    @Override
    public CustomerDto getCustomerByIdentityNumber(String identityNumber) {
        return CustomerMapper.CUSTOMER_MAPPER.entityToDto(
                customerRepository.findByIdentityNumber(identityNumber)
        );
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return CustomerMapper.CUSTOMER_MAPPER.entityToDto(
                customerRepository.findAll()
        );
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customer) {
        CustomerDto existingCustomerDto = getCustomerById(customerId);
        CustomerMapper.CUSTOMER_MAPPER.update(
                CustomerMapper.CUSTOMER_MAPPER.dtoToEntity(existingCustomerDto),
                CustomerMapper.CUSTOMER_MAPPER.dtoToEntity(customer)
        );
        return createCustomer(existingCustomerDto);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.delete(
                CustomerMapper.CUSTOMER_MAPPER.dtoToEntity(
                        getCustomerById(customerId)
                )
        );
    }

    private boolean isValidDateFormat(String dateOfBirth) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formatter.parse(dateOfBirth);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
