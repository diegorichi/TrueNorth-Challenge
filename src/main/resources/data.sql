INSERT INTO borrower(borrower_id, name, annual_income, delinquent_debt
, annual_debt,credit_history) values(1,'Bob Defilet','10000',false,3000,6);


INSERT INTO loan(loan_id,requested_amount,term_years,term_months,annual_interest,type,loan_officer_id,BORROWER_BORROWER_ID) VALUES
 (1,10000,2,0,6,'CONSUMER',100,1);
INSERT INTO loan(loan_id,requested_amount,term_years,term_months,annual_interest,type,loan_officer_id,BORROWER_BORROWER_ID) VALUES
 (2,25000,5,6,10,'STUDENT',100,1);
