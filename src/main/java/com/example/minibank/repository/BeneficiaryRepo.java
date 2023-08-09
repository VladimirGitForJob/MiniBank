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
//
//    SELECT b.name, SUM(a.BALANCE) as BALANCE from BENEFICIARIES b join ACCOUNTS a on b.ID = a.BENEFICIARY_ID GROUP BY a.BENEFICIARY_ID
//
//    @Query(value = "SELECT new com.example.minibank.entity.dto.BeneficiaryDTO (SELECT b.name as name, SUM(a.BALANCE) as balance from BENEFICIARIES b join ACCOUNTS a " +
//            "on b.ID = a.BENEFICIARY_ID GROUP BY a.BENEFICIARY_ID)",nativeQuery = true)
    @Query(value = "select new com.example.minibank.entity.dto.BeneficiaryDTO (b.name, SUM(a.balance) from Account a join Beneficiary b group by a.beneficiary)")
    List<BeneficiaryDTO> getBeneficiaryNameAndBalance();
}
