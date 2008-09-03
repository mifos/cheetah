[#ftl]
[#import "spring.ftl" as spring]
[#import "mifosMacros.ftl" as mifos]
<html>
  <head>
    <title>[@spring.message "systemInformation" /]</title>
    <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  [@mifos.header currentTab="Admin" /]
    <h1>[@spring.message "systemInformation" /]</h1>
    <ul>
      <li>[@spring.message "svnRevision" /]: ${model.appInfo.svnRevision}</li>
      <li>[@spring.message "buildTag" /]: ${model.appInfo.buildTag} </li>
      <li>[@spring.message "buildId" /]: ${model.appInfo.buildId} </li>
    </ul>
    [#include "footer.ftl"]    
  </body>
</html>
