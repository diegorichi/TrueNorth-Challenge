package com.example.metric.core;

import static org.mockito.Mockito.when;

import com.example.metric.model.Loan;
import com.example.metric.model.LoanMetric;
import com.example.metric.model.LoanType;
import com.example.metric.repository.LoanRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = {"com.example.metric"})
@SpringBootTest
class ConsumerLoanMetricCalculatorTest {

  @MockBean MessageSource messageSource;
  @Autowired private LoanRepository loanRepository;
  @Autowired private ConsumerLoanMetricCalculator consumerLoanMetricCalculator;

  @BeforeEach
  void setUp() {

    when(messageSource.getMessage(Mockito.anyString(), Mockito.any(), Mockito.any()))
        .thenReturn("dummy message");
  }

  @Test
  @DisplayName("Calculate - Consumer Loan.")
  void calculate() {

    Optional<Loan> optionalLoan = loanRepository.findById(1l);
    optionalLoan.ifPresent(
        loan -> {
          LoanMetric calculate = consumerLoanMetricCalculator.calculate(loan);
          Assertions.assertEquals(0.005, calculate.getMonthlyRate(), "Monthly Rate is wrong!");

          Assertions.assertEquals(
              443.20, calculate.getMonthlyPayment(), "Monthly Payment is wrong!");
          Assertions.assertEquals(
              LoanType.CONSUMER, calculate.getLoanType(), "Loan Type is wrong!");
        });
    Assertions.assertTrue(optionalLoan.isPresent(), "Loan not found!");
  }

  @Test
  @DisplayName("Not Supported Type - Consumer Loan.")
  void notSupported() {
    Loan loan = loanRepository.findById(2l).get();
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> consumerLoanMetricCalculator.calculate(loan));

    Assertions.assertFalse(consumerLoanMetricCalculator.isSupported(LoanType.STUDENT));
    Assertions.assertTrue(consumerLoanMetricCalculator.isSupported(LoanType.CONSUMER));

  }
}
