package com.example.minibank.service;

import com.example.minibank.entity.Account;
import com.example.minibank.entity.Beneficiary;
import com.example.minibank.repository.AccountRepo;
import com.example.minibank.repository.BeneficiaryRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImp implements AccountService {

    Logger logger = LoggerFactory.getLogger(AccountServiceImp.class);
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

    private boolean pinVerification(String incomingPinCode, String accountPinCode) {
        if (incomingPinCode.equals(accountPinCode))
            return true;
        return false;
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
            logger.info("Account for id{}, is created", id);
        } else {
            throw new IllegalArgumentException("Pin Code is fit to requirements and must consist 4 digits");
        }
    }


    public Account getAccount(Long accountNumber){
        Optional<Account> account = accountRepo.getAccountByAccountNumber(accountNumber);
        return account.orElse(null);
    }

    @Override
    @Transactional
    public void depositMoney(Long accountNumber, String pinCode, BigDecimal sum) {
        Account account = getAccount(accountNumber);
        String accountPinCode = account.getPinCode();
        if (pinVerification(pinCode, accountPinCode)) {
            account.setBalance(account.getBalance().add(sum));
            accountRepo.save(account);
            logger.info("Deposit is made for accountNumber {} the sum is {}", accountNumber, sum);
        } else {
            throw new IllegalArgumentException("Pin is not correct");
        }
    }

    @Override
    @Transactional
    public void withdrawMoney(Long accountNumber, String pinCode, BigDecimal sum) {
        Account account = getAccount(accountNumber);
        String accountPinCode = account.getPinCode();
        if (pinVerification(pinCode, accountPinCode)) {
            BigDecimal balance = account.getBalance();
            if (balance.compareTo(sum) >= 0) {
                account.setBalance(account.getBalance().subtract(sum));
                accountRepo.save(account);
                logger.info("Withdraw is made for accountNumber {} the sum is {}", accountNumber, sum);
            } else {
                throw new IllegalArgumentException("There are not enough funds in your account");
            }

        } else {
            throw new IllegalArgumentException("Pin is not correct");
        }
    }


    @Override
    @Transactional
    public void transferMoneyToOtherAccount(Long accountNumberTransferFrom, Long accountNumberTransferTo, String pinCode
            , BigDecimal sum) {
        Account accountSource = getAccount(accountNumberTransferFrom);
        Account accountTarget = getAccount(accountNumberTransferTo);
        String accountPinCode = accountSource.getPinCode();
        if (pinVerification(pinCode, accountPinCode)) {
            BigDecimal balanceSourceAccount = accountSource.getBalance();
            if (balanceSourceAccount.compareTo(sum) >= 0) {
                accountSource.setBalance(balanceSourceAccount.subtract(sum));
                accountRepo.save(accountSource);
                accountTarget.setBalance(accountTarget.getBalance().add(sum));
                accountRepo.save(accountTarget);
                logger.info("Transfer is made from accountNumber {} to new accountNumber {} the sum is {}"
                        , accountNumberTransferFrom, accountNumberTransferTo, sum);
            } else {
                throw new IllegalArgumentException("There are not enough funds in your account");
            }

        } else {
            throw new IllegalArgumentException("Pin is not correct");
        }

    }

    @Override
    public BigDecimal getBalanceByAccountNumber(Long accountNumber, String pinCode) {
        Account account = getAccount(accountNumber);
        String accountPinCode = account.getPinCode();
        BigDecimal balance = BigDecimal.valueOf(0);
        if (pinVerification(pinCode, accountPinCode)) {
            balance = account.getBalance();
        } else {
            throw new IllegalArgumentException("Pin is not correct");
        }
        return balance;
    }

    @Override
    public void deleteAccountByAccountNumber(Long accountNumber, String pinCode) {
        Account account = getAccount(accountNumber);
        String accountPinCode = account.getPinCode();
        if (pinVerification(pinCode, accountPinCode)) {
            accountRepo.delete(account);
        } else {
            throw new IllegalArgumentException("Pin is not correct");
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }
}
