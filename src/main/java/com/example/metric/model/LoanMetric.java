package com.example.metric.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanMetric {

  private static final long serialVersionUID = 207056366377073404L;

  private LoanType loanType;
  private double monthlyRate;
  private double monthlyPayment;
}
