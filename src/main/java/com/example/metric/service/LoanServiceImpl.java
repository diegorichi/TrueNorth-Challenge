package com.example.metric.service;

import com.example.metric.core.ILoanMetricCalculator;
import com.example.metric.model.Loan;
import com.example.metric.model.LoanMetric;
import com.example.metric.repository.LoanRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {

  @Autowired private LoanMetricFactory loanMetricFactory;
  @Autowired private LoanRepository loanRepository;

  public Optional<Loan> getLoan(Long loanId) {
    return loanRepository.findById(loanId);
  }

  public Optional<LoanMetric> calculateLoanMetric(Long loanId) {
    return loanRepository
        .findById(loanId)
        .map(
            loan -> {
              ILoanMetricCalculator loanMetricCalculator =
                  loanMetricFactory.getLoanMetricCalculator(loan.getType());
              return loanMetricCalculator.calculate(loan);
            });
  }

  public Optional<Loan> getMaxMonthlyPaymentLoan() {
    return loanRepository.findByMaxMonthlyPayment();
  }
}
