[#ftl]
[#import "spring.ftl" as spring]
<html>
  <head>
  	<title id="hello.title">[@spring.message "title" /]</title>
    <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
   [#include "headerForHomepage.ftl"]
         
    <h1 id="hello.heading">[@spring.message "welcomeToMifos" /]</h1>
    <p>[@spring.message "greeting" /] <span id="hello.longtime">${model.now?datetime?string.long}</span></p>
    <p>[@spring.message "greeting" /] <span id="hello.shorttime">${model.now?date?string.short}</span></p>
    <h3>[@spring.message "Products" /]</h3>
    <table border="1">
	    <tr>
	    	<td>[@spring.message "ID" /]</td>
	    	<td>[@spring.message "Description" /]</td>
	    	<td>[@spring.message "Price" /]</td>
	     </tr>
	     [#list model.products as product]
		 <tr>
	        <td>${product.id}</td>
	        <td>${product.description}</td>
	        <td>${product.price}</td>
	     </tr>
	    [/#list]
    <table>
    [#include "footer.ftl"]    
  </body>
</html>
