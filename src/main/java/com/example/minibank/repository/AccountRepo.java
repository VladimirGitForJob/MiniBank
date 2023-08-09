package com.example.minibank.repository;

import com.example.minibank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    Account getAccountByAccountNumber(Long accountNumber);

}
