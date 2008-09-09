[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
<html>
  <head>
  	<title id="clientsAndAccounts.title">[@spring.message "clientsAndAccounts.title" /]</title>
   <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  [@mifos.header currentTab="ClientsAndAccounts" /]
  [#include "clientsAndAccountsLeftPane.ftl" ]
    <div id="content-pane">
      <form method="POST" action="" id="createClient.form">
      <div class="error-messages">
        [#assign error=model.request.getParameter("error")!""]
        [#if error =="true" ]
          <span id="createClient.errorcaption">Error:</span> <span id="createClient.errormessage"></span><br/>
          </font>
        [/#if ]  
     </div>
     <fieldset class="createClient">
     <legend>[@spring.message "createClient" /]</legend>
        <label for="createClient.form.first.name" accesskey="n">[@spring.message "firstName" /]:</label>
           <input type="text" name="first.name" id="createClient.form.first.name" tabindex="1" value="" title="[@spring.message "firstName" /]"><br>
        <label for="createClient.form.last.name" accesskey="l">[@spring.message "lastName" /]: </label>
           <input type="text" id="createClient.form.last.name" name="last.name" tabindex="2" value="" title="Last name"><br>
        <label for="createClient.form.date.of.birth" accesskey="l">[@spring.message "dateOfBirth" /]: </label>
           <input type="text" id="createClient.form.date.of.birth" name="date.of.birth" tabindex="3" value="" title="[@spring.message "dateOfBirth" /]"><br>
        <label for="kludge"></label>
           <input type="submit" value="Create" class="buttn" id="login.form.submit" tabindex="4">
     </fieldset>
  </div> <!-- main pane content -->
  
  
</body>
</html>