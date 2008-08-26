[#ftl]
[#import "spring.ftl" as spring]
<html>
   <head>
      <title id="login.title">Login</title>
   </head>

   <body>
      [#assign error=model.request.getParameter("error")!""]
      [#if error =="true" ]
        <font color="red">
        <span id="login.errorcaption">Login error:</span> <span id="login.errormessage">${model.request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION").message}</span><br/>
        </font>
      [/#if ]
      
      <form method="POST" action="j_spring_security_check" id="login.form">
      <table>
        <tr>
          <td align="right">[@spring.message "UserName" /]</td>
          <td><input type="text" name="j_username" id="login.form.username"/></td>
        </tr>
        <tr>
          <td align="right">Password</td>
          <td><input type="password" name="j_password" id="login.form.password"/></td>
        </tr>
        <tr>
          <td colspan="2" align="right">
            <input type="submit" value="Login" id="login.form.submit"/>
            <input type="reset" value="Reset" id="login.form.reset"/>
          </td>
        </tr>
      </table>
      </form>
   </body>
</html>
