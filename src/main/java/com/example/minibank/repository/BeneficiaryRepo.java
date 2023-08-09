package com.example.minibank.repository;

import com.example.minibank.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary, Long> {

    Beneficiary getBeneficiaryById(Long id);

}
