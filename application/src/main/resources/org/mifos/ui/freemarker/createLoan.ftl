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
	<div id="page-content">
		<h2 id="createLoanHeading">[@spring.message "createANewLoan" /]</h2>
		 [@form.form action="createLoan.ftl" commandName="loan"]
		 [@form.errors path="*" cssClass="error-messages"/]
			<fieldset>
				<legend>[@spring.message "loanDetails" /]</legend>
				<label id="clientLabel">[@spring.message "client" /]:<label id="clientName">${clientName}</label></label>
					<br/>
				<label id="loanProductLabel">[@spring.message "loanProduct" /]:<label id="loanProductName">${loanProductName}</label></label>
					<br/>
				<label for="loan.form.amount" accesskey="a">[@spring.message "amount" /]:</label>
					[@form.input path="amount"/]
					<br/>
				<label  for="loan.form.interestRate" accesskey="i">[@spring.message "interestRate" /]:</label>
					[@form.input path="interestRate"/]
					<br/>
				<label for="kludge"></label>
					<input type="submit" value="Submit" class="buttn" id="loan.form.submit" tabindex="3" />
			</fieldset>	
		[/@form.form]
  	</div>
  </body>
</html>