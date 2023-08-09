package com.example.minibank.service;

import com.example.minibank.entity.Beneficiary;
import com.example.minibank.repository.BeneficiaryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BeneficiaryServiceImp implements BeneficiaryService{

   private final BeneficiaryRepo beneficiaryRepo;

    @Override
    public void createNewBeneficiary(Beneficiary beneficiary) {
    beneficiaryRepo.save(beneficiary);
    }

    @Override
    public void updateBeneficiary(Beneficiary beneficiary) {

    }

    @Override
    public Beneficiary getBeneficiaryById(Long id) {
        return beneficiaryRepo.getBeneficiaryById(id);
    }

    @Override
    public void deleteBeneficiaryById(Long id) {
    beneficiaryRepo.deleteById(id);
    }

    @Override
    public List<Beneficiary> BeneficiariesList() {
        return beneficiaryRepo.findAll();
    }
}
