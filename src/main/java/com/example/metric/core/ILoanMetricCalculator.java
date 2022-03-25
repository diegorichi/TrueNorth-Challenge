package com.example.metric.core;

import com.example.metric.model.Loan;
import com.example.metric.model.LoanMetric;
import com.example.metric.model.LoanType;
import com.example.metric.util.Constants;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.context.MessageSource;
import org.springframework.util.Assert;

public interface ILoanMetricCalculator {

  String ERROR_LOAN_NULL = "error.loan.null.message";

  default boolean isSupported(LoanType loanType) {
    return loanType != null && (getLoanType().compareTo(loanType) == 0);
  }

  LoanType getLoanType();

  default double getMonthlyInterestRate(Loan loan) {
    Assert.notNull(loan, getMessageSource().getMessage(ERROR_LOAN_NULL, null, null));
    return round(
        (loan.getAnnualInterest() / Constants.MONTHS) / 100,
        getRoundingDecimalPlacesInterestRate(),
        RoundingMode.DOWN);
  }

  MessageSource getMessageSource();

  default double getMonthlyPayment(Loan loan) {
    Assert.notNull(loan, getMessageSource().getMessage(ERROR_LOAN_NULL, null, null));
    double monthlyInterestRate = getMonthlyInterestRate(loan);
    double numerator = loan.getRequestedAmount() * monthlyInterestRate;
    double base = (1 + monthlyInterestRate);
    double pow = (-1) * (loan.getTermMonths() + loan.getTermYears() * Constants.MONTHS);
    double powResult = Math.pow(base, pow);
    return round(
        numerator / (1 - powResult), getRoundingDecimalPlacesMonthlyPayment(), RoundingMode.DOWN);
  }

  default double round(double value, int places, RoundingMode strategy) {
    BigDecimal bd = BigDecimal.valueOf(value);
    return bd.setScale(places, strategy).doubleValue();
  }

  default LoanMetric calculate(Loan loan) {
    Assert.isTrue(isSupported(loan.getType()), "Loan type not supported");
    LoanMetric loanMetric = new LoanMetric();
    loanMetric.setLoanType(getLoanType());

    // monthlyInterestRate = (annualInterest /12 ) / 100
    loanMetric.setMonthlyRate(getMonthlyInterestRate(loan));

    // monthlyPayment = 0.8 * (requestedAmount * monthlyInterestRate) / (1 - (1 +
    // monthlyInterestRate)^((-1) * termMonths) )
    loanMetric.setMonthlyPayment(getMonthlyPaymentForLoan(loan));

    return loanMetric;
  }

  default double getMonthlyPaymentForLoan(Loan loan) {
    return getMonthlyPaymentFactor() * getMonthlyPayment(loan);
  }

  double getMonthlyPaymentFactor();

  int getRoundingDecimalPlacesInterestRate();

  int getRoundingDecimalPlacesMonthlyPayment();
}
