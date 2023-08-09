package com.example.minibank.service;

import com.example.minibank.entity.Account;
import com.example.minibank.entity.Beneficiary;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    void createAccount(Long id, String pin);


    void depositMoney(Long accountNumber, String pinCode, BigDecimal sum);

    void withdrawMoney(Long accountNumber, String pinCode, BigDecimal sum);

    void transferMoneyToOtherAccount(Long accountNumberTransferFrom, Long accountNumberTransferTo
            , String pinCode, BigDecimal sum);

    BigDecimal getBalanceByAccountNumber(Long accountNumber, String pinCode);

    void deleteAccountByAccountNumber(Long accountNumber,String pinCode);

    List<Account> getAllAccounts();

    public Beneficiary getBeneficiaryById(Long id);

}
