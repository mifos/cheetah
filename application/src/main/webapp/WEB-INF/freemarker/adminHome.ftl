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
      
	<div class="page-content fontnormal">
	
		<p class="fontnormalboldorangeheading">
		Administrative Tasks
		</p>
		<p class="fontnormal">
		Welcome to the mifos administrative area. Click on a link below to begin.
		</p>
		
		<p class="fontnormalboldorangeheading">
		Manage Organization
		</p>
		
		<p class="fontnormalbold">Users</p>
		<ul>
		<li>View users | <a href="">Define a new user</a></li>
		</ul>

		<p class="fontnormalboldorangeheading">
		System Information
		</p>
		<ul>
			<li><a href="appInfo.ftl">View System Information</a></li>
		</ul>
		
		<p class="fontnormalboldorangeheading">
		Manage Products
		</p>

		<p class="fontnormalbold">
		Manage Loan Products
		</p>

		<ul>
			<li>View loan products | <a href="">Define a new loan product</a></li>
		</ul>
		
	</div>
</body>
</html>