[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
[#assign form=JspTaglibs["/WEB-INF/tld/spring-form.tld"]]
<html>
  <head>
  	<title id="clientsAndAccounts.title">[@spring.message "clientsAndAccounts.title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  [@mifos.header currentTab="ClientsAndAccounts" /]
  [#include "clientsAndAccountsLeftPane.ftl"]
    <div id="content-pane">
      [@form.form action="createClient.ftl" commandName="client"]
         [@form.errors path="*" cssClass="error-messages"/]
         <fieldset class="createClient">
           <legend>[@spring.message "createClient" /]</legend>
              <label for="createClient.form.first.name" accesskey="n">[@spring.message "firstName" /]:</label>
                [@form.input path="firstName"/]
              <label for="createClient.form.last.name" accesskey="l">[@spring.message "lastName" /]: </label>
                [@form.input path="lastName"/]
              <label for="createClient.form.date.of.birth" accesskey="l">[@spring.message "dateOfBirth" /] (${datePattern}):</label>
                [@form.input path="localDateOfBirth"/]
              <label for="kludge"></label>
                 <input type="submit" value="Create" class="buttn" id="client.form.submit" tabindex="4">
         </fieldset>
      [/@form.form]
     
  </div> <!-- content-pane -->
  
</body>
</html>