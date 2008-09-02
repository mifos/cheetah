[#ftl]
[#import "spring.ftl" as spring]
<html>
   <head>
     <title id="priceincrease.title">[@spring.message "title"/]</title>
     <style>
       .error { color: red; }
     </style>  
   </head>
   <body>
   <h1 id="priceincrease.heading">[@spring.message "welcomeToMifos" /]</h1>														
      <form action="priceincrease.ftl" method="POST" id="priceincrease.form">
        <table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
          <tr>
            <td align="right" width="20%">Increase (%):</td>
              <td width="20%">
                [@spring.formInput "priceIncrease.percentage", "" /]
              </td>
              <td width="60%">
      		  <span id="priceincrease.errors">[@spring.showErrors "<br/>", "" /]</span>
              </td>
          </tr>
        </table>
        <br>
        <input type="submit" align="center" value="Execute" id="priceincrease.form.submit">
      </form>
      [#include "footer.ftl"]
   </body>
</html>