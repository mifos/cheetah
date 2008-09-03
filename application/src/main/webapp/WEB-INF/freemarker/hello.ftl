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
      
    <p id="hello.heading" class="boldOrange">[@spring.message "welcomeToMifos" /]</p>
    <p>[@spring.message "greeting" /] <span id="hello.longtime">${model.now?datetime?string.long}</span></p>
    <p>[@spring.message "greeting" /] <span id="hello.shorttime">${model.now?date?string.short}</span></p>
    [#include "footer.ftl"]    
  </body>
</html>
