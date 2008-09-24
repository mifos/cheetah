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
  [#include "homeLeftPane.ftl" ]  
  
  <div id="page-content">      
    <h2>Loan Products</h2>
    
        <table id="loan-product-table">
	    <tr>
	    	<td>Short</td>
	    	<td>Long</td>
	    	<td>Minimum Annual</td>
	    	<td>Maximum Annual</td>
	    	<td><td>
	    	<td><td>
	    	<td><td>
	    </tr>
	    <tr>
	    	<td>Name</td>
	    	<td>Name</td>
	    	<td>Interest Rate</td>
	    	<td>Interest Rate</td>
	    	<td>Status</td>
	    	<td></td>
	    	<td></td>
	     </tr>
	     [#list model.loanProducts as product]
		 <tr>
	        <td>${product.shortName}</td>
	        <td>${product.longName}</td>
	        <td>${product.minInterestRate}</td>
	        <td>${product.maxInterestRate}</td>
	        <td>${product.status}</td>
	        <td><input type="submit" value="Modify" class="buttn"/></td>
	        <td><input type="submit" value="Delete" class="buttn"/></td>
	     </tr>
	    [/#list]
    <table>
    
    
  </div>

  </body>
</html>
