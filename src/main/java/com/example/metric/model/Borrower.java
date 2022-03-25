package com.example.metric.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Borrower {

  private static final long serialVersionUID = 8746629205348014588L;

  @Id private long borrowerId;
  private String name;
  private String annualIncome;
  private boolean delinquentDebt;
  private double annualDebt;
  private int creditHistory;
}
