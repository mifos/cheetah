[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
<html>
  <head>
  	<title id="deleteLoanProduct.title">[@spring.message "deleteLoanProduct.title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  [@mifos.header currentTab="Admin" /]
  [#include "adminLeftPane.ftl" ]
    <div class="content-pane">
      <span id="deleteLoanProduct.errorMessage">[@spring.message "deleteLoanProduct.couldNotDelete" /] '<span id="deleteLoanProduct.loanProduct.longName">${model.loanProduct.longName}</span>' [@spring.message "deleteLoanProduct.becauseLoansExist" /]</span>
      <p/>
      <a href="viewLoanProducts.ftl">Back to View Loan Products</a>
    </div>
</body>
</html>