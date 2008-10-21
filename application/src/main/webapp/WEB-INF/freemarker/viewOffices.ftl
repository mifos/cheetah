[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
<html>
  <head>
  	<title id="view.offices.title">[@spring.message "title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  
  
  [@mifos.header currentTab="Admin" /]
  [#include "adminLeftPane.ftl" ]  
  
  <div id="page-content">     
  	  <div id="standardPageContent">
   
	    <h2>[@spring.message "viewOffices" /]</h2>
	  
	    	<p id="headOffice">${(headOffice.name)!"No head office defined"}</p>
	    	    
	    	<h4>[@spring.message "offices" /]</h4>
	    	   		
			[#if model.offices?size == 0] 
	    	
	    		<p>[@spring.message "noOfficesDefined" /]</p>
	    	
			[#else]
	
	    		[#assign num = 0]
	    	    <table id="office-table">
			    [#list model.offices as office]
			    [#assign num = num + 1]
				<tr>
		        	<td id="office-${num}">${office.name}</td>
		    	</tr>
		    	[/#list]
		    	<table>
		    [/#if]
	  </div>
	 </div>

  </body>
</html>
