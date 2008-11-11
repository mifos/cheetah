[#ftl]
[#import "spring.ftl" as spring]
<html>
  <head>
  	<title>[@spring.message "title" /]</title>
  </head>
  <body>
    <h1>[@spring.message "welcomeToMifos" /]</h1>
    <p>Chinese text: [@spring.message "chineseText" /]</p>
    <p>Arabic text: [@spring.message "arabicText" /] </p>
    <p>Hindi text: [@spring.message "hindiText" /] </p>
    <p>French text: [@spring.message "frenchText" /] </p>
    [#include "footer.ftl"]    
  </body>
</html>
