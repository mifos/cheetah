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
  [#include "homeLeftPane.ftl" ]  
  
  <div id="page-content">
  	<div id="homePageContent">
      <h2>[@spring.message "welcomeToMifos" /]</h2>
    </div>
  </div>

  </body>
</html>
