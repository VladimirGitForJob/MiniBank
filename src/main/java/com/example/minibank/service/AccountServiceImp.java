package com.example.minibank.service;

import com.example.minibank.entity.Account;
import com.example.minibank.entity.Beneficiary;
import com.example.minibank.repository.AccountRepo;
import com.example.minibank.repository.BeneficiaryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service

public class AccountServiceImp implements AccountService {

    private final AccountRepo accountRepo;
    private final BeneficiaryRepo beneficiaryRepo;

    public AccountServiceImp(AccountRepo accountRepo, BeneficiaryRepo beneficiaryRepo) {
        this.accountRepo = accountRepo;
        this.beneficiaryRepo = beneficiaryRepo;
    }

    private boolean pinValidation(String pinCode) {
        if (pinCode.length() != 4)
            return false;
        return true;
    }

    private Long createAccountNumber() {
        long number = (long) Math.floor(Math.random() * 9_00_000_000L) + 1_00_000_000L;
        return number;
    }


    @Override
    public Beneficiary getBeneficiaryById(Long id) {
        Optional<Beneficiary> beneficiary = Optional.ofNullable(beneficiaryRepo.getBeneficiaryById(id));
        return beneficiary.orElse(null);
    }

    @Override
    @Transactional
    public void createAccount(Long id, String pinCode) {
        if (pinValidation(pinCode)) {
            Account account = new Account();
            account.setAccountNumber(createAccountNumber());
            account.setBeneficiary(getBeneficiaryById(id));
            account.setPinCode(pinCode);
            account.setBalance(BigDecimal.valueOf(0));
            accountRepo.save(account);
        } else {
            throw new IllegalArgumentException("Pin Code is not correct");
        }
    }

    @Override
    public void depositMoney(Long accountNumber, String pinCode, BigDecimal sum) {

    }

    @Override
    public void withdrawMoney(Long accountNumber, String pinCode, BigDecimal sum) {

    }

    @Override
    public void transferMoneyToOtherAccount(Long accountNumberTransferFrom, Long accountNumberTransferTo, String pinCode
            , BigDecimal sum) {

    }

    @Override
    public BigDecimal getBalanceByAccountNumber(Long accountNumber, String pinCode) {
        return null;
    }

    @Override
    public void deleteAccountById(Long accountNumber) {

    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }
}
