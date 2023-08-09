package com.example.minibank.controller;

import com.example.minibank.entity.Beneficiary;
import com.example.minibank.entity.dto.BeneficiaryDTO;
import com.example.minibank.service.BeneficiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/beneficiary")
@RequiredArgsConstructor
@Tag(name = "BeneficiaryController", description = "Контроллер обрабатывает запросы клиента направленные на " +
        "операции с со списком пользователей ")
public class BeneficiaryController {

   private final BeneficiaryService beneficiaryService;


    @GetMapping("/all")
    @Operation(summary = "Получение полного списка бенефициаров банка")
    ResponseEntity<List<Beneficiary>> getAllBeneficiaries(){
        return ResponseEntity.ok(beneficiaryService.BeneficiariesList());
    }

    @PostMapping("/create")
    @Operation(summary = "Внесение нового бенефициара")
    ResponseEntity<Void> createBeneficiary(@RequestBody Beneficiary beneficiary){
        beneficiaryService.createNewBeneficiary(beneficiary);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/total_balance")
    ResponseEntity<List<BeneficiaryDTO>> getBeneficiaryNameAndBalance(){
         return ResponseEntity.ok(beneficiaryService.getBeneficiaryNameAndBalance());
    }
}
