package com.example.minibank.service;

import com.example.minibank.entity.Account;
import com.example.minibank.entity.Beneficiary;
import com.example.minibank.repository.AccountRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImpTest {
    @Mock
    private AccountRepo accountRepo;
    @InjectMocks
    private AccountServiceImp accountServiceImp;

    Account account;
    Account accountTo;
    Beneficiary beneficiary;



    @BeforeEach
    public void setup(){

        beneficiary = Beneficiary.builder()
                .id(1L)
                .name("Richi")
                .surname("Handricks")
                .accountList(null)
                .build();

        account = Account.builder()
                .id(1L)
                .accountNumber(1234567890L)
                .balance(BigDecimal.valueOf(1000))
                .beneficiary(beneficiary)
                .pinCode("1234")
                .build();

        accountTo = Account.builder()
                .id(2L)
                .accountNumber(1234561000L)
                .balance(BigDecimal.valueOf(1000))
                .beneficiary(beneficiary)
                .pinCode("4321")
                .build();

    }

    @Test
    void depositMoney() {
       Mockito.when(accountRepo.getAccountByAccountNumber(1234567890L)).thenReturn(Optional.of(account));
        accountServiceImp.depositMoney(1234567890L, "1234", BigDecimal.valueOf(1000));
        BigDecimal result =  account.getBalance();
        assertEquals( BigDecimal.valueOf(2000), result);
    }

    @Test
    void withdrawMoney() {
        Mockito.when(accountRepo.getAccountByAccountNumber(1234567890L)).thenReturn(Optional.of(account));
        accountServiceImp.withdrawMoney(1234567890L, "1234", BigDecimal.valueOf(500));
        BigDecimal result =  account.getBalance();
        assertEquals( BigDecimal.valueOf(500), result);
    }

    @Test
    void transferMoneyToOtherAccount() {
        Mockito.when(accountRepo.getAccountByAccountNumber(1234567890L)).thenReturn(Optional.of(account));
        Mockito.when(accountRepo.getAccountByAccountNumber(1234561000L)).thenReturn(Optional.of(accountTo));
        accountServiceImp.transferMoneyToOtherAccount(1234567890L
                ,1234561000L,"1234", BigDecimal.valueOf(500));
        BigDecimal result1 =  account.getBalance();
        assertEquals( BigDecimal.valueOf(500), result1);
        BigDecimal result2 =  accountTo.getBalance();
        assertEquals( BigDecimal.valueOf(1500), result2);


    }
}