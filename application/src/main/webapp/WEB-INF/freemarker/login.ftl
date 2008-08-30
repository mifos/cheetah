[#ftl]
[#import "spring.ftl" as spring]
<html>
   <head>
      <title id="login.title">Login</title>
      <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
   </head>

   <body>
   
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="188" rowspan="2"><IMG height=74 alt=""
				src="images/logo.gif" width=188></td>

			<td align="right" bgcolor="#ffffff" class="fontnormal">&nbsp;</td>
		</tr>

		<tr>
			<td align="left" valign="bottom" bgcolor="#ffffff">&nbsp;</td>
		</tr>

		<tr>
			<td colspan="2" class="bgorange"><IMG height=6
				src="images/trans.gif" width=6></td>
		</tr>

		<tr>
			<td colspan="2" class="bgwhite"><IMG height=2
				src="images/trans.gif" width=100></td>
		</tr>
	</table>
    
      
     <form method="POST" action="j_spring_security_check" id="login.form">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="480" align="left" valign="top" bgcolor="#ffffff">
			<table width="50%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td align="center" class="blueline">&nbsp;</td>
				</tr>
			</table>

			<table width="50%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="bluetableborder">
				<tr>
					<td colspan="2" align="left" valign="top"
						class="tableContentLightBlue"><span class="heading">Login</td>
				</tr>
				<tr>
					<td width="45%" align="left" valign="top"
						style="BORDER-RIGHT: #d7deee 1px solid; PADDING-RIGHT: 10px; PADDING-LEFT: 10px;
					PADDING-BOTTOM: 10px; PADDING-TOP: 10px"><span
						class="fontnormal">Welcome to Mifos<br>
					<br>
					</span><br>
					</td>

					<td width="55%" align="left" valign="top"
						style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px">
					<table width="100%" border="0" cellpadding="3" cellspacing="0">
					   <tr><td colspan="2">

					      [#assign error=model.request.getParameter("error")!""]
					      [#if error =="true" ]
					        <font class="fontnormalRedBold">
					        <span id="login.errorcaption">Login error:</span> <span id="login.errormessage">${model.request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION").message}</span><br/>
					        </font>
					      [/#if ]
							
							</td>
							</tr>
						<tr class="fontnormal">
							<td width="33%" align="right">[@spring.message "UserName" /]:</td>

							<td width="67%"><input type="text" name="j_username" id="login.form.username"/></td>
						</tr>

						<tr class="fontnormal">
							<td align="right">Password:</td>

							<td><input type="password" name="j_password" id="login.form.password"/></td>
						</tr>
						<tr class="fontnormal">
							<td align="right"></td>

							<td><br>
				            <input class="buttn" type="submit" value="Login" id="login.form.submit"/>
						</tr>
					</table>
					<br>
					</td>
				</tr>
			</table>
			<br>
			</td>
		</tr>
	</table>
    </form>
           
    </body>
</html>
