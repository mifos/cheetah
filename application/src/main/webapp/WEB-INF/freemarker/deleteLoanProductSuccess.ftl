[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
<html>
  <head>
   <title id="deleteLoanProductSuccess.title">[@spring.message "deleteLoanProductSuccess.title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  [@mifos.header currentTab="Admin" /]
  [#include "adminLeftPane.ftl" ]
    <div class="content-pane">
      <span id="deleteLoanProductSuccess.message">[@spring.message "deleteLoanProductSuccess.successfullyDeleted" /] '<span id="deleteLoanProductSuccess.loanProduct.longName">${model.loanProduct.longName}</span>'.</span>
      <p/>
      <a href="viewLoanProducts.ftl">Back to View Loan Products</a>
    </div>
</body>
</html>