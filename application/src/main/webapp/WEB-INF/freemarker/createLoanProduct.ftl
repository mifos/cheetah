[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
[#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]]

<html>

  <head>
  	<title id="hello.title">[@spring.message "title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  [@mifos.header currentTab="Home" /]

	<div class="page-content">
	
		<h2>Create a new loan product</h2>
				
		 [@form.form action="createLoanProduct.ftl" commandName="loanProduct"]
		 [@form.errors path="*" cssClass="error-messages"/]
		
			<fieldset>
			
				<legend>Loan Product Details</legend>
			
				<label for="loanproduct.form.longname" accesskey="l">Long name:</label>
					[@form.input path="longName"/]
					[@form.errors path="longName" cssClass="error-messages"/]
					<br/>
			
				<label  for="loanproduct.form.shortname" accesskey="s">Short name:</label>
					[@form.input path="shortName"/]
					<br/>
			
				<label for="loanproduct.form.mininterest" accesskey="m">Minimum interest percent rate:</label>
					[@form.input path="minInterestRate"/]
					<br/>
			
				<label for="loanproduct.form.maxinterest" accesskey="a">Maximum interest percent rate:</label>
					[@form.input path="maxInterestRate"/]
					<br/>
			
				<label for="loanproduct.form.status" accesskey="t">Status:</label>
					[@form.select path="status"
								  items=availableCategories /]
					<br/>
			
				<label for="kludge"></label>
					<input type="submit" value="Submit" class="buttn" id="login.form.submit" tabindex="6">
			
			</fieldset>
			
		[/@form.form]
  	
  	</div>
  
      [#include "footer.ftl"]    
  </body>
  </html>