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

<h1>Welcome to Mifos</h1>

     		<form method="POST" action="j_spring_security_check" id="login.form">

					<div class="error-messages">
				      [#assign error=model.request.getParameter("error")!""]
				      [#if error =="true" ]
				        <span id="login.errorcaption">Login error:</span> <span id="login.errormessage">${model.request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION").message}</span><br/>
				        </font>
				      [/#if ]
					</div>

	<fieldset class="login">
	<legend>Login</legend>
		<label for="login.form.username" accesskey="n">[@spring.message "UserName" /]:</label>
			<input type="text" name="j_username" id="login.form.username" tabindex="1" value="" title="User Name" /><br>
		<label for="login.form.password" accesskey="l">Password: </label>
			<input type="password" id="login.form.password" name="j_username" tabindex="2" title="Password" /><br>
		<label for="kludge"></label>
			<input type="submit" value="Login" class="buttn" id="login.form.submit" tabindex="3" />
	</fieldset>
					
	</div>

</body>
</html>
   