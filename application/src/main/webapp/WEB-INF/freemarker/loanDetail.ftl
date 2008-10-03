[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
<html>
  <head>
  	<title id="clientsAndAccounts.title">[@spring.message "clientsAndAccounts.title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  [@mifos.header currentTab="ClientsAndAccounts" /]
  [#include "clientsAndAccountsLeftPane.ftl" ]
    <div id="page-content">
    	<div id="loanDetailPage">
	    	<h2>${model.loan.loanProductDto.longName}</h2>
	    	<p>Client: ${model.clientName}</p>
	    	<p>Loan Amount: ${model.loan.amount}</p>
	    	<p>Interest Rate: ${model.loan.interestRate}</p>
	    	<p>Disbursal Date: ${(model.loanDisbursalDate?date?string.short)!"None"}</p>
	    	<p><a href="disburseLoan.ftl?id=${model.loan.id}">Disburse Loan</a></p>
      	</div>
    </div> <!-- main pane content -->
  </body>
</html>