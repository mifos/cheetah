[#ftl]
[#import "spring.ftl" as spring]
<html>
  <head>
  	<title id="hello.title">[@spring.message "title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
   [#include "headerForHomepage.ftl"]
      
	<div class="page-content fontnormal">
	
		<p class="fontnormalboldorange">
		Administrative Tasks
		</p>
		<p class="fontnormal">
		Welcome to mifos administrative area. Click on a link below to begin.
		</p>
		
		<p class="fontnormalboldorange">
		Manage Organization
		</p>
		
		<p class="fontnormalbold>Users</p>
		<ul>
		<li>View Users</li>
		<li>Define a new user</li>
		</ul>
		
		<p class="fontnormalboldorange">
		Manage Products
		</p>

		<p class="fontnormalbold">
		Manage Loan Products
		</p>

		<ul>
			<li>View loan products</li>
			<li><a href="createLoanProduct.ftl">Define a new loan product</a></li>
		</ul>
		
	</div>
</body>
</html>