package com.example.metric.repository;

import com.example.metric.model.Loan;
import com.example.metric.util.Constants;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
// exposes as rest repository to easy add registers into memory
@RestResource(path = "loans", rel = "loans")
public interface LoanRepository extends CrudRepository<Loan, Long> {

  @Query(
      value =
          "SELECT * FROM loan ORDER BY (loan.annual_interest/"
              + Constants.MONTHS
              + ")/100 desc limit 1",
      nativeQuery = true)
  Optional<Loan> findByMaxMonthlyPayment();
}
