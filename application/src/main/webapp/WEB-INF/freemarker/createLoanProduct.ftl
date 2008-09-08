[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
<html>
  <head>
  	<title id="hello.title">[@spring.message "title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  [@mifos.header currentTab="Home" /]

	<div class="page-content">
	
		<h2>Create a new loan product</h2>
 
		 <form action="POST" class="loan-product">
		
			<fieldset>
			
				<legend>Loan Product Details</legend>
			
				<label for="loanproduct.form.longname" accesskey="l">Long name:</label>
					<input  type="text" name="j_longname" id="loanproduct.form.longname" tabindex="1" value="" title="Long Name"><br/>
			
				<label  for="loanproduct.form.shortname" accesskey="s">Short name:</label>
					<input type="text" name="j_shortname" id="loanproduct.form.shortname" tabindex="2" value="" title="Short Name"/><br/>
			
				<label for="loanproduct.form.mininterest" accesskey="m">Minimum interest percent rate:</label>
					<input  class="loan-product"type="text" name="j_mininterest" id="loanproduct.form.mininterest" tabindex="3" value="" title="Minimum Interest Percentage Rate (0 to 999)"/><br/>
			
				<label for="loanproduct.form.maxinterest" accesskey="a">Maximum interest percent rate:</label>
					<input type="text" name="j_maxinterest" id="loanproduct.form.maxinterest" tabindex="4" value="" title="Maximum interest percent rate (0 to 999, and greater than minimum interest)"/><br/>
			
				<label for="loanproduct.form.status" accesskey="t">Status:</label>
					<select name="j_status" id="loanproduct.form.status" tabindex="5">
							<option value="ACTIVE" selected>Active</option>
							<option value="INACTIVE">Inactive</option>
						</select> <br/>
			
				<label for="kludge"></label>
					<input type="submit" value="Submit" class="buttn" id="login.form.submit" tabindex="6">
			
			</fieldset>
			
		</form>
  	
  	</div>
  
      [#include "footer.ftl"]    
  </body>
  </html>