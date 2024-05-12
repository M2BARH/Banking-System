package com.m2bar.Banking.System.service.impl;

import com.m2bar.Banking.System.mapper.AddressMapper;
import com.m2bar.Banking.System.models.AddressDto;
import com.m2bar.Banking.System.repository.AddressRepository;
import com.m2bar.Banking.System.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressDto createAddress(AddressDto address) {
        return AddressMapper.ADDRESS_MAPPER.entityToDto(
                addressRepository.save(
                        AddressMapper.ADDRESS_MAPPER.dtoToEntity(address)
                )
        );
    }

    @Override
    public AddressDto getAddressById(Long addressId) {
        return AddressMapper.ADDRESS_MAPPER.entityToDto(
                addressRepository.findById(addressId)
                        .orElseThrow(() -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, String.format("Address with id %d was not found", addressId)
                                )
                        )
        );
    }

    @Override
    public List<AddressDto> getAllAddresses() {
        return AddressMapper.ADDRESS_MAPPER.entityToDto(
                addressRepository.findAll()
        );
    }

    @Override
    public AddressDto updateAddress(Long addressId, AddressDto address) {
        AddressDto existingAddress = getAddressById(addressId);
        AddressMapper.ADDRESS_MAPPER.update(
                AddressMapper.ADDRESS_MAPPER.dtoToEntity(existingAddress),
                AddressMapper.ADDRESS_MAPPER.dtoToEntity(address)
        );
        return createAddress(existingAddress);
    }

    @Override
    public void deleteAddress(Long addressId) {
        addressRepository.delete(
                AddressMapper.ADDRESS_MAPPER.dtoToEntity(
                        getAddressById(addressId)
                )
        );
    }
}
