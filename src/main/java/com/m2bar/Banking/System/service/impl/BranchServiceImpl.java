package com.m2bar.Banking.System.service.impl;

import com.m2bar.Banking.System.mapper.BranchMapper;
import com.m2bar.Banking.System.models.BranchDto;
import com.m2bar.Banking.System.repository.BranchRepository;
import com.m2bar.Banking.System.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public BranchDto createBranch(BranchDto branch) {
        return BranchMapper.BRANCH_MAPPER.entityToDto(
                branchRepository.save(
                        BranchMapper.BRANCH_MAPPER.dtoToEntity(
                                branch
                        )
                )
        );
    }

    @Override
    public BranchDto getBranchById(Long branchId) {
        return BranchMapper.BRANCH_MAPPER.entityToDto(
                branchRepository.findById(branchId)
                        .orElseThrow(() -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, String.format("Branch with id %d was not found", branchId)
                                )
                        )
        );
    }

    @Override
    public List<BranchDto> getAllBranches() {
        return BranchMapper.BRANCH_MAPPER.entityToDto(
                branchRepository.findAll()
        );
    }

    @Override
    public BranchDto updateBranch(Long branchId, BranchDto branch) {
        BranchDto existingBranch = getBranchById(branchId);
        BranchMapper.BRANCH_MAPPER.update(
                BranchMapper.BRANCH_MAPPER.dtoToEntity(existingBranch),
                BranchMapper.BRANCH_MAPPER.dtoToEntity(branch)
        );
        return createBranch(existingBranch);
    }

    @Override
    public void deleteBranch(Long branchId) {
        branchRepository.delete(
                BranchMapper.BRANCH_MAPPER.dtoToEntity(
                        getBranchById(branchId)
                )
        );
    }
}
