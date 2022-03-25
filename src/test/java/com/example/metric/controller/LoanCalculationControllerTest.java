package com.example.metric.controller;

import static org.mockito.Mockito.when;

import com.example.metric.model.Borrower;
import com.example.metric.model.Loan;
import com.example.metric.model.LoanMetric;
import com.example.metric.model.LoanType;
import com.example.metric.repository.LoanRepository;
import com.example.metric.service.LoanService;
import java.io.IOException;
import java.util.Optional;
import okhttp3.mockwebserver.MockWebServer;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = {"com.example.metric"})
@SpringBootTest
class LoanCalculationControllerTest {

  @MockBean MessageSource messageSource;
  @Autowired LoanCalculatorController loanCalculatorController;
  @MockBean private LoanRepository loanRepository;
  @MockBean private LoanService loanService;
  private MockWebServer mockBackEnd;
  private String baseUrl = "http://localhost:";

  @BeforeEach
  void setUp() throws IOException {

    when(messageSource.getMessage(Mockito.anyString(), Mockito.any(), Mockito.any()))
        .thenReturn("dummy message");

  }

  @Test
  @DisplayName("Calculate - Controller Consumer Loan.")
  void calculate() {

    when(loanService.calculateLoanMetric(1l)).thenReturn(Optional.of(new LoanMetric(LoanType.CONSUMER,0.2,15.6)));

    ResponseEntity<LoanMetric> loanMetricResponseEntity = loanCalculatorController.calculateLoanMetric(
        1l);

    LoanMetric loanMetric = loanMetricResponseEntity.getBody();

    Assertions.assertNotNull(loanMetric);
    Assertions.assertEquals(loanMetric.getLoanType(), LoanType.CONSUMER);
    Assertions.assertEquals(0.2, loanMetric.getMonthlyRate());
    Assertions.assertEquals(15.6, loanMetric.getMonthlyPayment());

}
  @Test
  @DisplayName("Calculate - Controller Consumer Loan.")
  void getLoan() {
    Borrower borrower = new Borrower(100, "", "", false, 10.0, 1);

    when(loanService.getLoan(1l)).thenReturn(
        Optional.of(new Loan(1L, 10000.00, 1, 6, 6.2, LoanType.CONSUMER, 1, borrower)));

    ResponseEntity<Loan> responseEntity = loanCalculatorController.getLoan(1L);

    Loan loan = responseEntity.getBody();
    Assertions.assertNotNull(loan);
    Assertions.assertEquals(loan.getType(), LoanType.CONSUMER);

    Assertions.assertEquals(6.2, loan.getAnnualInterest());
    Assertions.assertEquals(6, loan.getTermMonths());
    Assertions.assertEquals(1, loan.getTermYears());

  }

  @Test
  @DisplayName("GetLoan - Negative loan param.")
  void getLoanNegative() {
    // test negative loan id
    ResponseEntity<Loan> responseEntity = loanCalculatorController.getLoan(-1L);

    Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());


  }
}
