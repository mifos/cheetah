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
    <div id="content-pane">
      <h2>Client search results</h2>
    </div> <!-- main pane content -->
  
  
</body>
</html>