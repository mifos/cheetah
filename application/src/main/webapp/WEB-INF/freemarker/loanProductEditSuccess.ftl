[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
<html>
  <head>
  	<title id="hello.title">[@spring.message "title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  
  <!-- page: loanProductEditSuccess.ftl -->
  
  [@mifos.header currentTab="Admin" /]
  [#include "adminLeftPane.ftl" ] 
      
	<div id="page-content">
	
	[#assign productName = ["${model.loanProduct.longName}"]]
			
	 <h2 id="page-content-heading">[@spring.messageArgs "loanProduct.edit.success.heading" productName/]</h2>
	 
	 <table>
	 	<tr><td>Long name:</td><td id="longName">${model.loanProduct.longName}</td></tr>
	 	<tr><td>Short name:</td><td id="shortName">${model.loanProduct.shortName}</td></tr>
	 	<tr><td>Minimum interest rate:</td><td id="minInterestRate">${model.loanProduct.minInterestRate}</td></tr>
	 	<tr><td>Maximum interest rate:</td><td id="maxInterestRate">${model.loanProduct.maxInterestRate}</td></tr>
	 	<tr><td>Status:</td><td id = "status">${model.loanProduct.status}</td></tr>
	 </table>
	   
	   </div>
  </body>
</html>
