<p align="center">
  <a href="" rel="noopener">
 <img width=200px height=200px src="https://i.imgur.com/6wj0hh6.jpg" alt="Project logo"></a>
</p>

<h3 align="center">Loan Metric Calculator</h3>

---

<p align="center"> Loan Calculation.
    <br> 
</p>

## 📝 Table of Contents

- [About](#about)
- [Prerequisites](#prerequisites)
- [Test](#test)
- [Usage](#usage)
- [Todo](#todo)

## 🧐 About <a name = "about"></a>

Project that exposes Rest api:

1) /v1/loan_calculator/ <br>
    /{loanId} <br>
   --> public Loan getLoan(Long loanId) <br>
    /calculate?loanId={loanId} <br>
  --> public LoanMetric calculateLoanMetric(Long loanId) <br>
  /max_payment <br>
  --> public Loan getMaxMonthlyPaymentLoan() <br>

<br><br>

2) Also, the repository is exposed as RestRepository under /api/loans path

<br>

## 🎈 Prerequisites <a name = "prerequisites"></a>

Java 11.<br><br>


## 🔧 Running the tests <a name = "tests"></a>

Run the following command: mvn test


## 🎈 Usage <a name="usage"></a>

Run the following command: mvn spring-boot:run

## 🚀 TODO: <a name = "todo"></a>

- Add authorization
- Define packaging strategy
- Define loan in production environment.
- Define the database.
- And a lot of things. :D
