package com.m2bar.Banking.System.service;

import com.m2bar.Banking.System.models.AddressDto;

import java.util.List;

public interface AddressService {

    AddressDto createAddress(AddressDto address);

    AddressDto getAddressById(Long addressId);

    List<AddressDto> getAllAddresses();

    AddressDto updateAddress(Long addressId, AddressDto address);

    void deleteAddress(Long addressId);
}
