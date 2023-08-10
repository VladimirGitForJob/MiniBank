package com.example.minibank.repository;

import com.example.minibank.entity.Beneficiary;
import com.example.minibank.entity.dto.BeneficiaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary, Long> {

    Beneficiary getBeneficiaryById(Long id);
    @Query("select new com.example.minibank.entity.dto.BeneficiaryDTO (b.name, SUM(a.balance)) " +
            "FROM Beneficiary b JOIN b.accountList a " +
            "GROUP BY b.id")
    List<BeneficiaryDTO> getBeneficiaryNameAndBalance();

}
