package com.example.minibank.service;

import com.example.minibank.entity.Beneficiary;

import java.util.List;

public interface BeneficiaryService {

    void createNewBeneficiary(Beneficiary beneficiary);

    void updateBeneficiary(Beneficiary beneficiary);

    Beneficiary getBeneficiaryById(Long id);

    void deleteBeneficiaryById(Long id);

    List<Beneficiary> BeneficiariesList();
}
