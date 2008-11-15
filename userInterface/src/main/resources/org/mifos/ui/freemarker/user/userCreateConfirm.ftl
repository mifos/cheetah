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
	
		<h2 id="user.create.confirm.heading">[@spring.message "user.create.confirm.heading"/]</h2>
	
		<table>
			<tr>
				<td>[@spring.message "user.userId.description"/]</td>
				<td>${user.userId}</td>
			</tr>
			<tr>
				<td>[@spring.message "user.password.description"/]</td>
				<td>${user.password}</td>
			</tr>
			<tr>
				<td>[@spring.message "user.roles.description"/]</td>
				<td>
					<ul>
						[#list user.roles as role]
							<li> [@spring.message "user.role.${role}"/] </li>
						[/#list]
					</ul>
				</td>
			</tr>
		</table>	

  	</div>
  
      [#include "footer.ftl"]    
  </body>
  </html>