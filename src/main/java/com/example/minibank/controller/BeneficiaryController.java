package com.example.minibank.controller;

import com.example.minibank.entity.Beneficiary;
import com.example.minibank.service.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/beneficiary")
@RequiredArgsConstructor
public class BeneficiaryController {

   private final BeneficiaryService beneficiaryService;


    @GetMapping("/all")
    ResponseEntity<List<Beneficiary>> getAllBeneficiaries(){
        return ResponseEntity.ok(beneficiaryService.BeneficiariesList());
    }

    @PostMapping("/create")
    ResponseEntity<Void> createBeneficiary(@RequestBody Beneficiary beneficiary){
        beneficiaryService.createNewBeneficiary(beneficiary);
        return ResponseEntity.ok().build();
    }
}
