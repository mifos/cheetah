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
    	<div id="clientDetailPage">
	    	<h2>Client Name</h2>
	      	<h2>Account Information</h2>
	      	<div id="clientDetailPage-loanSection">
	    		<h4 id="clientDetailPage-loanSectionHeading">Loan</h4>
	    		<div id="clientDetailPage-loanList">
		    		<p><a href="loanDetail.ftl">Loan 1</a></p>
		    		<p><a href="loanDetail.ftl">Loan 2</a></p>
	    		</div>
	      	</div>
      	</div>
    </div> <!-- main pane content -->
  
  
</body>
</html>