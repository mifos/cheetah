[#ftl]
[#import "spring.ftl" as spring]
<html>
  <head>
  	<title>[@spring.message "accessDeniedTitle" /]</title>
  </head>
  <body>
    <h1 id="accessDeniedHeading">[@spring.message "accessDeniedHeading" /]</h1>
    <p id="accessDeniedMessage">[@spring.message "accessDeniedMessage" /]</p>
  </body>
</html>
