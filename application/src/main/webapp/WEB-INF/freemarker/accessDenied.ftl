[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
<html>
  <head>
  	<title>[@spring.message "accessDeniedTitle" /]</title>
    <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
	  [@mifos.header currentTab="Home" /]

    <h1 id="accessDeniedHeading">[@spring.message "accessDeniedHeading" /]</h1>
    <p id="accessDeniedMessage">[@spring.message "accessDeniedMessage" /]</p>
  </body>
</html>
