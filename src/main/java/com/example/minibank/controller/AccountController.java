package com.example.minibank.controller;

import com.example.minibank.entity.Account;
import com.example.minibank.entity.Beneficiary;
import com.example.minibank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/accounts")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService  accountService;

  @GetMapping("/all")
  ResponseEntity<List<Account>> getAllAccounts(){
      return ResponseEntity.ok(accountService.getAllAccounts());
  }


  @PostMapping("/create/{id}/{pinCode}")
  ResponseEntity<Void> createAccount (@PathVariable Long id, @PathVariable String pinCode){
      accountService.createAccount(id, pinCode);
      return ResponseEntity.ok().build();
  }

  @PutMapping("/deposit/{accountNumber}/{pinCode}/{balance}")
  ResponseEntity<Void> depositMoney(@PathVariable Long accountNumber, @PathVariable String pinCode
          , @PathVariable BigDecimal balance){
      accountService.depositMoney(accountNumber, pinCode, balance);
      return ResponseEntity.ok().build();
  }
    @PutMapping("/withdraw/{accountNumber}/{pinCode}/{balance}")
    ResponseEntity<Void> withdrawMoney(@PathVariable Long accountNumber, @PathVariable String pinCode
            , @PathVariable BigDecimal balance){
        accountService.withdrawMoney(accountNumber, pinCode, balance);
        return ResponseEntity.ok().build();
    }
    @PutMapping("transfer/{accountNumberTransferFrom}/{accountNumberTransferTo}/{pinCode}/{sum}")
    ResponseEntity<Void> transferMoneyToOtherAccount(@PathVariable Long accountNumberTransferFrom
            ,@PathVariable Long accountNumberTransferTo,@PathVariable String pinCode, @PathVariable BigDecimal sum){
      accountService.transferMoneyToOtherAccount(accountNumberTransferFrom, accountNumberTransferTo, pinCode, sum);
      return ResponseEntity.ok().build();
    }

    @GetMapping("/balance/{accountNumber}/{pinCode}")
    ResponseEntity<BigDecimal> getBalance(@PathVariable Long accountNumber,@PathVariable String pinCode){
      return ResponseEntity.ok(accountService.getBalanceByAccountNumber(accountNumber, pinCode));
    }

    @DeleteMapping("/delete/{accountNumber}/{pinCode}")
    ResponseEntity<Void> deleteAccountByAccountNumber(@PathVariable Long accountNumber,@PathVariable String pinCode){
       accountService.deleteAccountByAccountNumber(accountNumber, pinCode);
       return ResponseEntity.ok().build();
    }
}
