package com.example.minibank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Arrays;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "ACCOUNTS")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", length = 10)
    private Long accountNumber;
    @Column(name = "pin_code", length = 4)
    private String pinCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiary_id")
    @JsonIgnore
    private Beneficiary beneficiary;
    @Column(name = "balance")
    private BigDecimal balance;

    public Account(String pinCode, Beneficiary beneficiary, BigDecimal balance) {
        this.pinCode = pinCode;
        this.beneficiary = beneficiary;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;

        if (!getId().equals(account.getId())) return false;
        if (!getAccountNumber().equals(account.getAccountNumber())) return false;
        if (!getPinCode().equals(account.getPinCode())) return false;
        if (!getBeneficiary().equals(account.getBeneficiary())) return false;
        return getBalance().equals(account.getBalance());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getAccountNumber().hashCode();
        result = 31 * result + getPinCode().hashCode();
        result = 31 * result + getBeneficiary().hashCode();
        result = 31 * result + getBalance().hashCode();
        return result;
    }
}
