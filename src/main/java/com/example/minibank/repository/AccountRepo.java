package com.example.minibank.repository;

import com.example.minibank.entity.Account;
import com.example.minibank.entity.dto.BeneficiaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    Optional<Account> getAccountByAccountNumber(Long accountNumber);


}
