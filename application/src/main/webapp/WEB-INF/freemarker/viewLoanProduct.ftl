[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
<html>
  <head>
  	<title id="hello.title">[@spring.message "title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  [@mifos.header currentTab="Admin" /]
      
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
		 
		<div class="button-list">
			<ul>
				<li> <input type="submit" value="Edit" class="buttn"></li>   
				<li> <input type="submit" value="Delete" class="buttn"></li>
			<ul>
		</div> 
	</div>
  </body>
</html>
