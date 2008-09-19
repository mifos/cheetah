[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
<html>
  <head>
  	<title id="hello.title">[@spring.message "title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  [@mifos.header currentTab="Home" /]
      
	<div style="page-content">
	
	 <h2>The loan product "${model.loanProduct.shortName}" has been created</h2>
	 
	 <table>
	 	<tr><td>Long name:</td><td>${model.loanProduct.longName}</td></tr>
	 	<tr><td>Short name:</td><td>${model.loanProduct.shortName}</td></tr>
	 	<tr><td>Minimum interest rate:</td><td>${model.loanProduct.minInterestRate}</td></tr>
	 	<tr><td>Maximum interest rate:</td><td>${model.loanProduct.maxInterestRate}</td></tr>
	 	<tr><td>Status:</td><td>${model.loanProduct.status}</td></tr>
	 </table>
	   
	   </div>
  </body>
</html>
