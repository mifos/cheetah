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
  [#include "adminLeftPane.ftl" ]  
  
  <div id="page-content">      
    <h2>Loan Products</h2>
   
[#if model.loanProducts?size == 0] 
    	
    	<p> [@spring.message "viewLoanProductsNoneDefined" /] </p>
    	
[#else]
    
    	[#assign lpnum = 0]
    	
        <table id="loan-product-table">
	    <tr>
	    	<td>Short Name</td>
	    	<td>Long Name</td>
	    </tr>
	     [#list model.loanProducts as product]
	     [#assign lpnum = lpnum + 1]
		 <tr>
	        <td id="short-name-${lpnum}"><a href="viewLoanProduct.ftl?id=${product.id}">${product.shortName}</a></td>
	        <td id="long-name-${lpnum}"><a href="viewLoanProduct.ftl?id=${product.id}">${product.longName}</a></td>
	     </tr>
	    [/#list]
    <table>
    
[/#if]

  </div>

  </body>
</html>
