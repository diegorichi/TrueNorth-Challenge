package com.example.metric.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Loan {

  private static final long serialVersionUID = 8592089026188312029L;

  @Id private long loanId;
  private double requestedAmount;
  private int termYears;
  private int termMonths;
  private double annualInterest;

  @Enumerated(EnumType.STRING)
  private LoanType type;

  private int loanOfficerId;
  @ManyToOne private Borrower borrower;
}
