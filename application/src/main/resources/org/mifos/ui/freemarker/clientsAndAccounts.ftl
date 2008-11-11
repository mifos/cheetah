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
    	<div id="clientsAndAccountsPage">
	    	<h2>Clients and Accounts</h2>
      	</div>
    </div> <!-- main pane content -->
  

</body>
</html>