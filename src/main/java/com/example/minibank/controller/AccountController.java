package com.example.minibank.controller;

import com.example.minibank.entity.Account;
import com.example.minibank.entity.Beneficiary;
import com.example.minibank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/accounts")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService  accountService;

  @RequestMapping("/all")
  ResponseEntity<List<Account>> getAllAccounts(){
      return ResponseEntity.ok(accountService.getAllAccounts());
  }


  @PostMapping("/create/{id}/{pinCode}")
  ResponseEntity<Void> createAccount (@PathVariable Long id, @PathVariable String pinCode){
      accountService.createAccount(id, pinCode);
      return ResponseEntity.ok().build();
  }
}
