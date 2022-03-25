package com.example.metric.core;

import com.example.metric.model.LoanType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@EqualsAndHashCode
@Data
public class StudentMetricCalculator implements ILoanMetricCalculator {

  @Autowired MessageSource messageSource;

  @Value("${payment.factor.consumer}")
  private double paymentFactor;

  @Value("${rounding.decimal.places.interest_rate:3}")
  private int roundingDecimalPlacesInterestRate;

  @Value("${rounding.decimal.places.monthly_payment:2}")
  private int roundingDecimalPlacesMonthlyPayment;

  @Override
  public double getMonthlyPaymentFactor() {
    return paymentFactor;
  }

  @Override
  public LoanType getLoanType() {
    return LoanType.STUDENT;
  }

  @Override
  public MessageSource getMessageSource() {
    return messageSource;
  }
}
