package com.m2bar.Banking.System.controller;

import com.m2bar.Banking.System.api.AddressesApi;
import com.m2bar.Banking.System.models.AddressDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController implements AddressesApi {
    @Override
    public ResponseEntity<Void> addressesAddressIdDelete(Long addressId) {
        return null;
    }

    @Override
    public ResponseEntity<AddressDto> addressesAddressIdGet(Long addressId) {
        return null;
    }

    @Override
    public ResponseEntity<AddressDto> addressesAddressIdPut(Long addressId, AddressDto body) {
        return null;
    }

    @Override
    public ResponseEntity<List<AddressDto>> addressesGet() {
        return null;
    }

    @Override
    public ResponseEntity<AddressDto> addressesPost(AddressDto body) {
        return null;
    }
}
