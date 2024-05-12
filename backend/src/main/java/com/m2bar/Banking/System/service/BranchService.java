package com.m2bar.Banking.System.service;

import com.m2bar.Banking.System.models.BranchDto;

import java.util.List;

public interface BranchService {

    BranchDto createBranch(BranchDto branch);

    BranchDto getBranchById(Long branchId);

    List<BranchDto> getAllBranches();

    BranchDto updateBranch(Long branchId, BranchDto branch);

    void deleteBranch(Long branchId);
}
