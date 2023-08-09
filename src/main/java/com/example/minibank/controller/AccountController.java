package com.example.minibank.controller;

import com.example.minibank.entity.Account;
import com.example.minibank.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/accounts")
@RequiredArgsConstructor
@Tag(name = "AccountController", description = "Контроллер обрабатывает запросы клиента направленные на " +
        "операции с банковским счетом ")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/all")
    @Operation(
            summary = "Получение списка всех аккаунтов банка",
            description = "Получение списка всех аккаунтов банка с информацией о номере счета, балансе, пользователе " +
                    "и пин-коде")
    ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }


    @PostMapping("/create/{id}/{pinCode}")
    @Operation(
            summary = "Открытие банковского счета",
            description = "Открытие счета возможно с указанием данных пользователя - id и пин-кода")
    ResponseEntity<Void> createAccount(@PathVariable Long id, @PathVariable String pinCode) {
        accountService.createAccount(id, pinCode);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/deposit/{accountNumber}/{pinCode}/{balance}")
    @Operation(summary = "Внесение денег на счет",
            description = "Внесение денег на счет возможно с указанием данных номера счета accountNumber и пин-кода")
    ResponseEntity<Void> depositMoney(@PathVariable Long accountNumber, @PathVariable String pinCode
            , @PathVariable BigDecimal balance) {
        accountService.depositMoney(accountNumber, pinCode, balance);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/withdraw/{accountNumber}/{pinCode}/{balance}")
    @Operation(summary = "Снятие денег со счета",
    description = "Снятие денег со счета возможно с указанием данных номера счета accountNumber и пин-кода ")
    ResponseEntity<Void> withdrawMoney(@PathVariable Long accountNumber, @PathVariable String pinCode
            , @PathVariable BigDecimal balance) {
        accountService.withdrawMoney(accountNumber, pinCode, balance);
        return ResponseEntity.ok().build();
    }

    @PutMapping("transfer/{accountNumberTransferFrom}/{accountNumberTransferTo}/{pinCode}/{sum}")
    @Operation(summary = "Перевод денег со счета на счет",
    description = "Перевод денег возможен с указанием счета отправителя accountNumberTransferFrom, " +
            "счета получателя accountNumberTransferTo, пин-кода pinCode и суммы перевода sum")
    ResponseEntity<Void> transferMoneyToOtherAccount(@PathVariable Long accountNumberTransferFrom
            , @PathVariable Long accountNumberTransferTo, @PathVariable String pinCode, @PathVariable BigDecimal sum) {
        accountService.transferMoneyToOtherAccount(accountNumberTransferFrom, accountNumberTransferTo, pinCode, sum);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/balance/{accountNumber}/{pinCode}")
    @Operation(
            summary = "Запрос баланса",
            description = "Запрос баланса по номеру счета"
    )
    ResponseEntity<BigDecimal> getBalance(@PathVariable Long accountNumber, @PathVariable String pinCode) {
        return ResponseEntity.ok(accountService.getBalanceByAccountNumber(accountNumber, pinCode));
    }

    @DeleteMapping("/delete/{accountNumber}/{pinCode}")
    @Operation(summary = "Удаление аккаунта")
    ResponseEntity<Void> deleteAccountByAccountNumber(@PathVariable Long accountNumber, @PathVariable String pinCode) {
        accountService.deleteAccountByAccountNumber(accountNumber, pinCode);
        return ResponseEntity.ok().build();
    }
}
