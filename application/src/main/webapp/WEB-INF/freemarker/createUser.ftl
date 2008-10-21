[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
[#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]]

<html>

  <head>
  	<title id="createUser.title">[@spring.message "user.create.title" /]</title>
    <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  
  <!-- page: creatUser.ftl -->
  
  [@mifos.header currentTab="Admin" /]
  [#include "adminLeftPane.ftl" ] 

	<div id="page-content">
	
		<h2>[@spring.message "user.create.heading"/]</h2>
				
		 [@form.form action="createUser.ftl" commandName="user"]
		 [@form.errors path="*" cssClass="error-messages"/]
		
			<fieldset>
				<legend>User Login Details</legend>
			
				<label for="user.form.userId">[@spring.message "user.userId"/]:</label>
					[@form.input path="userId"/]
					<br/>
			
				<label  for="user.form.password">[@spring.message "user.password"/]:</label>
					[@form.input path="password"/]
					<br/>
			
				<label for="user.confirmPassword">[@spring.message "user.confirmPassword"/]:</label>
					[@form.input path="confirmPassword"/]
					<br/>			
				<label for="kludge"></label>
					<input type="submit" value="[@spring.message "submit"/]" class="buttn" id="user.form.submit">
			</fieldset>
			
		[/@form.form]
  	
  	</div>
  
      [#include "footer.ftl"]    
  </body>
  </html>