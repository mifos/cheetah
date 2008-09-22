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
	
	 <h2>The loan has been created</h2>
	 
	 <table>
	 	<tr><td>Amount:</td><td>${model.loan.amount}</td></tr>
	 	<tr><td>Interest rate:</td><td>${model.loan.interestRate}</td></tr>
	 </table>
	   
	   </div>
  </body>
</html>
