[#ftl]
[#import "spring.ftl" as spring]
<html>
  <head>
  	<title>[@spring.message "systemInformation" /]</title>
  </head>
  <body>
    <h1>[@spring.message "systemInformation" /]</h1>
    <ul>
      <li>Svn Revision: ${model.appInfo.svnRevision}</li>
      <li>Build id: ${model.appInfo.buildTag} </li>
    </ul>
    [#include "footer.ftl"]    
  </body>
</html>
