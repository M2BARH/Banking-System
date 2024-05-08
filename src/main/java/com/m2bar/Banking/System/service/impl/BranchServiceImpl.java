package com.m2bar.Banking.System.service.impl;

import com.m2bar.Banking.System.models.BranchDto;
import com.m2bar.Banking.System.service.BranchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {
    @Override
    public BranchDto createBranch(BranchDto branch) {
        return null;
    }

    @Override
    public BranchDto getBranchById(Long branchId) {
        return null;
    }

    @Override
    public List<BranchDto> getAllBranches() {
        return null;
    }

    @Override
    public BranchDto updateBranch(Long branchId, BranchDto branch) {
        return null;
    }

    @Override
    public void deleteBranch(Long branchId) {

    }
}
