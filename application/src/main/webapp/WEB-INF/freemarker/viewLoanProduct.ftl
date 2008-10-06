[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
[#assign security=JspTaglibs["/WEB-INF/tld/security.tld"]]
<html>
  <head>
  	<title id="hello.title">[@spring.message "title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  [@mifos.header currentTab="Admin" /]
  [#include "adminLeftPane.ftl" ] 
      
	<div id="page-content">
	
			<h2 id="page-content-heading">Details for loan product "${model.loanProduct.longName}"</h2>
		 
		<table>
		 	<tr>
		 		<td>Long name:</td>
		 		<td id="longName">${model.loanProduct.longName}</td>
		 	</tr>
		 	<tr>
		 		<td>Short name:</td>
		 		<td id="shortName">${model.loanProduct.shortName}</td>
		 	</tr>
		 	<tr>
		 		<td>Minimum interest rate:</td>
		 		<td id="minInterestRate">${model.loanProduct.minInterestRate}</td>
		 	</tr>
		 	<tr>
		 		<td>Maximum interest rate:</td>
		 		<td id="maxInterestRate">${model.loanProduct.maxInterestRate}</td>
		 	</tr>
		 	<tr>
		 		<td>Status:</td>
		 		<td id = "status">${model.loanProduct.status}</td>
		 	</tr>
		</table>
		  
		[@security.authorize ifAnyGranted="ROLE_ADMIN"]
		<div class="navigation-list">
			<ul>
				<li> <a href="updateLoanProduct.ftl?id=${model.loanProduct.id}" id="update-loan-product">Update</a></li>   
				<li> <a href="deleteLoanProduct.ftl?id=${model.loanProduct.id}" id="delete-loan-product">Delete</a></li>
			<ul>
		</div> 
		[/@security.authorize]
		
	</div>
  </body>
</html>
