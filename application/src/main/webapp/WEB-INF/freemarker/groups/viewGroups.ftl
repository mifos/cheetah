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
    <h2>[@spring.message "groups.view.heading" /]</h2>
   
[#if model.groups?size == 0] 
    	<p> [@spring.message "groups.view.noneDefined" /] </p>
[#else]
    	[#assign groupIndex = 0]
	    [@spring.message "groups.view.heading" /]
	    [#list model.groups as groups]
	    [#assign groupIndex = groupIndex + 1]
	      <span id="short-name-${groupIndex}">${group.name}</span>
	    [/#list]
[/#if]

  </div>

  </body>
</html>
