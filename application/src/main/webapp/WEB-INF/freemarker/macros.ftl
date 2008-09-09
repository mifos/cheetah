[#ftl]
[#import "spring.ftl" as spring]

[#-- To use macros defined here add the following directive at the top of an ftl file --]
[#-- [#import "macros.ftl" as mifos] --]

[#-- header macro --]
[#-- Generate the Mifos header with tabbed navigation --]
[#-- currentTab values: "Home","ClientsAndAccounts","Reports","Admin" --]
[#-- usage   [@mifos.header currentTab="Home" /] --]
[#macro header currentTab]
<div id = "homePageHeader">
	<IMG id="logo" height=74 alt=""
					src="images/logo.gif" width=188></td>
	
	<div id="top-right-links">
      <a id="settings" href="#">Your settings</a> |
   	<a id="logout" href="j_spring_security_logout">Logout</a>
	</div>			
	<div id="top-menu-bar">
		<ul id="simple-menu">
		<li><a href="hello.ftl" title="[@spring.message "tab.Home" /]" [#if currentTab == "Home"]class="current"[/#if]>[@spring.message "tab.Home" /]</a></li>
		<li><a href="clientsAndAccounts.ftl" title="[@spring.message "tab.ClientsAndAccounts" /]" [#if currentTab == "ClientsAndAccounts"]class="current"[/#if]>[@spring.message "tab.ClientsAndAccounts" /]</a></li>
		<li><a href="" title="[@spring.message "tab.Reports" /]" [#if currentTab == "Reports"]class="current"[/#if]>[@spring.message "tab.Reports" /]</a></li>
		<li><a href="adminHome.ftl" title="[@spring.message "tab.Admin" /]" [#if currentTab == "Admin"]class="current"[/#if]>[@spring.message "tab.Admin" /]</a></li>
		</ul>
	</div>			
</div>
[/#macro]
