[#ftl]
[#import "spring.ftl" as spring]
<html>
  <head>
    <title>[@spring.message "systemInformation" /]</title>
    <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
     [#include "headerForHomepage.ftl"]
    <h1>[@spring.message "systemInformation" /]</h1>
    <ul>
      <li>Svn Revision: ${model.appInfo.svnRevision}</li>
      <li>Build tag: ${model.appInfo.buildTag} </li>
      <li>Build id: ${model.appInfo.buildId} </li>
    </ul>
    [#include "footer.ftl"]    
  </body>
</html>
