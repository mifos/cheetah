[#ftl]
[#import "spring.ftl" as spring]
<html>
   <head>
      <title id="login.title">Login</title>
      <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
   </head>

   <body>
   
	<!-- this is the header portion of the login page - should be put in its own freemarker file and included in each page -->

	<div id="login-page-header">
	</div>

	<div class="page-content">

		<div id="login">
     		<form method="POST" action="j_spring_security_check" id="login.form">

				<div id="login-header">
					Login
				</div>
	
				<div id="login-welcome">
					Welcome to Mifos
				</div>
	
				<div id="login-interaction">
				
					<div class="error-messages">
				      [#assign error=model.request.getParameter("error")!""]
				      [#if error =="true" ]
				        <span id="login.errormessage">${model.request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION").message}</span><br/>
				        </font>
				      [/#if ]
					</div>
					
					<table id="login-table">
						<tr>
							<td id="login-name-label" class="label">[@spring.message "UserName" /]:</td>
							<td><input type="text"name="j_username" id="login.form.username"></td>
						</tr>
	
						<tr>
							<td id="login-password-label" class="label">Password:</td>
							<td><input type="password" name="j_password" id="login.form.password"></td>
						</tr>
	
						<tr>
							<td></td>
							<td id="login-submit"><input type="submit" value="Login" class="buttn" id="login.form.submit" /></td>
						</tr>
	
					</table> <!-- login-table -->
				</div> <!-- login-interaction -->
			</div> <!-- login -->
		</form>
	</div> <!-- page-content -->
</body>
</html>
   