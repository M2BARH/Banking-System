package com.m2bar.Banking.System.controller;

import com.m2bar.Banking.System.api.AddressesApi;
import com.m2bar.Banking.System.models.AddressDto;
import com.m2bar.Banking.System.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AddressController implements AddressesApi {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public ResponseEntity<Void> deleteAddressById(Long addressId) {
        try {
            addressService.deleteAddress(addressId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete address", ex);
        }
    }

    @Override
    public ResponseEntity<AddressDto> getAddressById(Long addressId) {
        try {
            return new ResponseEntity<>(addressService.getAddressById(addressId), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve address", ex);
        }
    }

    @Override
    public ResponseEntity<AddressDto> updateAddressById(Long addressId, AddressDto body) {
        try {
            return new ResponseEntity<>(addressService.updateAddress(addressId, body), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update address", ex);
        }
    }

    @Override
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        try {
            return new ResponseEntity<>(addressService.getAllAddresses(), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve addresses", ex);
        }
    }

    @Override
    public ResponseEntity<AddressDto> createAddress(AddressDto body) {
        try {
            return new ResponseEntity<>(addressService.createAddress(body), HttpStatus.CREATED);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create address", ex);
        }
    }
}
