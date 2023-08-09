package com.example.minibank.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiaryDTO {
    private String name;
    private BigDecimal balance;
}
