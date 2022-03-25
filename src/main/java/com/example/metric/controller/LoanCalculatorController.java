package com.example.metric.controller;

import com.example.metric.model.Loan;
import com.example.metric.model.LoanMetric;
import com.example.metric.service.LoanService;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/loan_calculator")
public class LoanCalculatorController {

  @Autowired private LoanService loanService;

  @GetMapping(value = "/{loanId}")
  public ResponseEntity<Loan> getLoan(
      @PathVariable(value = "loanId", required = true) @Min(1) Long loanId) {
    return loanService
        .getLoan(loanId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping(value = "/calculate")
  public ResponseEntity<LoanMetric> calculateLoanMetric(
      @RequestParam(value = "loanId", required = true) @Min(1) Long loanId) {
    return loanService
        .calculateLoanMetric(loanId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping(value = "/max_payment")
  public ResponseEntity<Loan> calculateAllLoanMetrics() {
    return loanService
        .getMaxMonthlyPaymentLoan()
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
