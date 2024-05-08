package com.m2bar.Banking.System.controller;

import com.m2bar.Banking.System.api.BranchesApi;
import com.m2bar.Banking.System.models.BranchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BranchController implements BranchesApi {
    @Override
    public ResponseEntity<BranchDto> branchesBranchIdGet(Long branchId) {
        return null;
    }

    @Override
    public ResponseEntity<BranchDto> branchesBranchIdPut(Long branchId, BranchDto body) {
        return null;
    }

    @Override
    public ResponseEntity<List<BranchDto>> branchesGet() {
        return null;
    }

    @Override
    public ResponseEntity<BranchDto> branchesPost(BranchDto body) {
        return null;
    }
}
