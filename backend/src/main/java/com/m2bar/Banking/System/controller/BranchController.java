package com.m2bar.Banking.System.controller;

import com.m2bar.Banking.System.api.BranchesApi;
import com.m2bar.Banking.System.models.BranchDto;
import com.m2bar.Banking.System.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class BranchController implements BranchesApi {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @Override
    public ResponseEntity<BranchDto> getBranchById(Long branchId) {
        try {
            return new ResponseEntity<>(branchService.getBranchById(branchId), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve branch", ex);
        }
    }

    @Override
    public ResponseEntity<BranchDto> updateBranchById(Long branchId, BranchDto body) {
        try {
            return new ResponseEntity<>(branchService.updateBranch(branchId, body), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update branch", ex);
        }
    }

    @Override
    public ResponseEntity<List<BranchDto>> getAllBranches() {
        try {
            return new ResponseEntity<>(branchService.getAllBranches(), HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve branches", ex);
        }
    }

    @Override
    public ResponseEntity<BranchDto> createBranch(BranchDto body) {
        try {
            branchService.createBranch(body);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create branch", ex);
        }
    }
}
