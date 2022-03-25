package com.example.metric.service;

import com.example.metric.model.Loan;
import com.example.metric.model.LoanMetric;
import java.util.Optional;

public interface LoanService {

  Optional<Loan> getLoan(Long loanId);

  Optional<LoanMetric> calculateLoanMetric(Long loanId);

  Optional<Loan> getMaxMonthlyPaymentLoan();
}
