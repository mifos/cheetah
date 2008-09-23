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
      
	<div id="page-content">
	
		<h2 id="adminHome.heading">Administrative Tasks</h2>
		
			<p>
			Welcome to the mifos administrative area. Click on a link below to begin.
			</p>
		
			<h3>Manage Organization</h3>
			
				<h4>Users</h4>
				
					<ul>
					<li>View users | <a href="createLoanAccount.ftl">Define a new user</a></li>
					</ul>
		
				<h4>System Information</h4>
				
					<ul>
						<li><a href="appInfo.ftl">View System Information</a></li>
					</ul>
				
			<h3>Manage Products</h3>
	
				<h4>Manage Loan Products</h4>
		
					<ul>
						<li>View loan products | <a href="createLoanProduct.ftl">Define a new loan product</a></li>
					</ul>
			
	</div>
</body>
</html>