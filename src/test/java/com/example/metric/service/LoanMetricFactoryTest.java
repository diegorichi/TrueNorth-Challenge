package com.example.metric.service;

import static org.mockito.Mockito.when;

import com.example.metric.core.ConsumerLoanMetricCalculator;
import com.example.metric.core.StudentMetricCalculator;
import com.example.metric.model.Loan;
import com.example.metric.model.LoanMetric;
import com.example.metric.model.LoanType;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = {"com.example.metric.service","com.example.metric.core"})
@SpringBootTest

//you can define for example "test" profile and use it with @ActiveProfiles("test")
//and create the file application-test.properties to override values.
@ActiveProfiles("default")
class LoanMetricFactoryTest {

  @Autowired private LoanMetricFactory loanMetricFactory;
  @MockBean
  MessageSource messageSource;

  @Test
  @DisplayName("Test Factory")
  void testCalculatorFactory() {

    when(messageSource.getMessage(Mockito.anyString(), Mockito.any(), Mockito.any()))
        .thenReturn("dummy message");

    Assertions.assertEquals(2, loanMetricFactory.getAvailableLoan().size());

  }

}
