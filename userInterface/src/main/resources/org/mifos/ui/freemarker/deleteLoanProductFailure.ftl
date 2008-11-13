[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
<html>
  <head>
  	<title id="deleteLoanProductFailure.title">[@spring.message "deleteLoanProductFailure.title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  [@mifos.header currentTab="Admin" /]
  [#include "adminLeftPane.ftl" ]
    <div class="content-pane">
      <span id="deleteLoanProductFailure.message">[@spring.message "deleteLoanProductFailure.couldNotDelete" /] '<span id="deleteLoanProductFailure.loanProduct.longName">${model.loanProduct.longName}</span>' [@spring.message "deleteLoanProductFailure.becauseLoansExist" /]</span>
      <p/>
      <a href="viewLoanProducts.ftl">Back to View Loan Products</a>
    </div>
</body>
</html>