[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
<html>
  <head>
  	<title id="hello.title">[@spring.message "title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  
  <!-- page: loanProductCreateSuccess.ftl -->
  
  [@mifos.header currentTab="Admin" /]
  [#include "adminLeftPane.ftl" ] 
      
	<div id="page-content">
	
	[#assign productName = ["${model.loanProduct.longName}"]]
			
	 <h2 id="page-content-heading">[@spring.messageArgs "loanProduct.create.success.heading" productName/]</h2>
	
	 <table>
	 	<tr>
	 		<td>[@spring.message "loanProduct.longName.description"/]:</td>
	 		<td id="longName">${model.loanProduct.longName}</td>
	 	</tr>
	 	<tr>
	 		<td>[@spring.message "loanProduct.shortName.description"/]:</td>
	 		<td id="shortName">${model.loanProduct.shortName}</td>
	 	</tr>
	 	<tr>
	 		<td>[@spring.message "loanProduct.minInterestRate.description"/]:</td>
	 		<td id="minInterestRate">${model.loanProduct.minInterestRate}</td>
	 	</tr>
	 	<tr>
	 		<td>[@spring.message "loanProduct.maxInterestRate.description"/]:</td>
	 		<td id="maxInterestRate">${model.loanProduct.maxInterestRate}</td>
	 	</tr>
	 	<tr>
	 		<td>[@spring.message "loanProduct.status.description"/]:</td>
	 		<td id = "status">${model.loanProduct.status}</td>
	 	</tr>
	 </table>
	   
	   </div>
  </body>
</html>
