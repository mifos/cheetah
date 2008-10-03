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
		<h2>Disburse loan</h2>
		 [@form.form action="disburseLoan.ftl" commandName="loan"]
		 [@form.errors path="*" cssClass="error-messages"/]
			<fieldset>
				<label id="loanProductLabel">Loan Product:<label id="loanProductName">${loan.loanProductDto.longName}</label></label>
					<br/>
				<label id="loanAmountLabel">Loan Amount:<label id="loanAmount">${loan.amount}</label></label>
					<br/>
                <label for="loan.form.disbursalDate" accesskey="l">[@spring.message "disbursalDate" /] (<span id="datePattern">${datePattern}</span>):</label>
                    [@form.input path="disbursalDate"/]<br/>
				<label for="kludge"></label>
					<input type="submit" value="[@spring.message "submit"/]" class="buttn" id="disburseLoan.form.submit" tabindex="2">
			</fieldset>	
		[/@form.form]
  	</div>
  </body>
</html>