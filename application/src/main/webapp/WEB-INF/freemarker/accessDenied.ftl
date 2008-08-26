[#ftl]
[#import "spring.ftl" as spring]
<html>
  <head>
  	<title>[@spring.message "accessDeniedTitle" /]</title>
  </head>
  <body>
    <h1>[@spring.message "accessDeniedHeading" /]</h1>
    <p>[@spring.message "accessDeniedMessage" /]</p>
    [#include "footer.ftl"]    
  </body>
</html>
