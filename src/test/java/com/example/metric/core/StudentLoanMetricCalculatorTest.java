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
class StudentLoanMetricCalculatorTest {

  @MockBean MessageSource messageSource;
  @Autowired private LoanRepository loanRepository;
  @Autowired private StudentMetricCalculator studentMetricCalculator;

  @BeforeEach
  void setUp() {

    when(messageSource.getMessage(Mockito.anyString(), Mockito.any(), Mockito.any()))
        .thenReturn("dummy message");
  }

  @Test
  @DisplayName("Calculate - Student Loan.")
  void calculate() {

    Optional<Loan> loanOptional = loanRepository.findById(2l);
    loanOptional.ifPresent(
        loan -> {
          LoanMetric calculate = studentMetricCalculator.calculate(loan);
          Assertions.assertEquals(0.008, calculate.getMonthlyRate(), "Monthly Rate is wrong!");

          Assertions.assertEquals(
              489.02, calculate.getMonthlyPayment(), "Monthly Payment is wrong!");
          Assertions.assertEquals(LoanType.STUDENT, calculate.getLoanType(), "Loan Type is wrong!");
        });
    Assertions.assertTrue(loanOptional.isPresent(), "Loan not found!");
  }

  @Test
  @DisplayName("Not Supported Type - Student Loan.")
  void notSupported() {
    Loan loan = loanRepository.findById(1l).get();

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> studentMetricCalculator.calculate(loan));

    Assertions.assertFalse( studentMetricCalculator.isSupported(LoanType.CONSUMER));
    Assertions.assertTrue( studentMetricCalculator.isSupported(LoanType.STUDENT));
  }
}
