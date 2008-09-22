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

	<div class="page-contents">
	
		<h2>Create a new loan</h2>
				
		 [@form.form action="createLoan.ftl" commandName="loan"]
		 [@form.errors path="*" cssClass="error-messages"/]
		
			<fieldset>
			
				<legend>Loan  Details</legend>

				<label for="loan.form.loanProductId" accesskey="p">Loan Product Id:</label>
					[@form.input path="loanProductId"/]
					<br/>
						
				<label for="loan.form.amount" accesskey="a">Amount:</label>
					[@form.input path="amount"/]
					<br/>
			
				<label  for="loan.form.interestRate" accesskey="i">Interest rate:</label>
					[@form.input path="interestRate"/]
					<br/>
	
				<label for="kludge"></label>
					<input type="submit" value="Submit" class="buttn" id="loan.form.submit" tabindex="3">
			
			</fieldset>
	
		[/@form.form]
  	
  	</div>
    
  </body>
  </html>