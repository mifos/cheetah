[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
<html>
  <head>
  	<title id="user.create.success.title">[@spring.message "user.create.success.title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  
  <!-- page: userCreateSuccess.ftl -->
  
  [@mifos.header currentTab="Admin" /]
  [#include "adminLeftPane.ftl" ] 
      
	<div id="page-content">
	
		[#assign userId = ["${model.user.userId}"]]
			
		<h2 id="user.create.success.heading">[@spring.messageArgs "user.create.success.heading" userId /]</h2>
	   
	</div>
  </body>
</html>
