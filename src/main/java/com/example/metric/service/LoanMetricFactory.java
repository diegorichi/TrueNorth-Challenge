package com.example.metric.service;

import com.example.metric.core.ILoanMetricCalculator;
import com.example.metric.model.LoanType;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@AllArgsConstructor
@Getter
public class LoanMetricFactory {

  @Autowired private ApplicationContext applicationContext;

  private final Map<String, ILoanMetricCalculator> availableLoan = new HashMap<>();

  @PostConstruct
  void init() {
    applicationContext
        .getBeansOfType(ILoanMetricCalculator.class)
        .values()
        .forEach(
            loanMetricCalculator ->
                availableLoan.put(loanMetricCalculator.getLoanType().name(), loanMetricCalculator));
  }

  public ILoanMetricCalculator getLoanMetricCalculator(LoanType loan) {
    Assert.notNull(loan, "Loan type cannot be null");
    return availableLoan.get(loan.name());
  }
}
