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
	    	<h2 id="loanProductName">${model.loan.loanProductDto.longName}</h2>
	    	<p>[@spring.message "client" /]: ${model.clientName}</p>
	    	<p>[@spring.message "loanAmount" /]: ${model.loan.amount}</p>
	    	<p>[@spring.message "interestRate" /]: ${model.loan.interestRate}</p>
	    	<p>[@spring.message "disbursalDate" /]: <span id="disbursalDate">${(model.loanDisbursalDate?date?string.short)!"None"}</span></p>
	    	<p><a id="disburseLoan" href="disburseLoan.ftl?id=${model.loan.id}">[@spring.message "disbursalLoan" /]</a></p>
      	</div>
    </div> <!-- main pane content -->
  </body>
</html>