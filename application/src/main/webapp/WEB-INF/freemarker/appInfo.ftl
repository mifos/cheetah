[#ftl]
[#import "spring.ftl" as spring]
[#import "macros.ftl" as mifos]
<html>
  <head>
    <title>[@spring.message "systemInformation" /]</title>
    <link href="css/cssstyle.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  [@mifos.header currentTab="Admin" /]
  [#include "adminLeftPane.ftl" ] 
        
	<div id="page-content">
	  <div id="standardPageContent">
	    <h2>[@spring.message "systemInformation" /]</h2>
	    <ul>
	      <li>[@spring.message "svnRevision" /]: ${model.appInfo.svnRevision}</li>
	      <li>[@spring.message "buildTag" /]: ${model.appInfo.buildTag} </li>
	      <li>[@spring.message "buildId" /]: ${model.appInfo.buildId} </li>
	    </ul>
	  </div>
	</div>
  </body>
</html>
