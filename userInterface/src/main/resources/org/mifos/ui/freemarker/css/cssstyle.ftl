/********************************/
/* BEGIN ADDITIONS FOR CHEETAH */
/********************************/

#clientsAndAccountsPage {
	margin: 0 0 0 200px;
}

#loanDetailPage {
	margin: 0 0 0 200px;
}

#clientDetailPage-loanList {
	margin: 0 0 0 20px;
}

#clientDetailPage-loanSectionHeading {
	margin: 0 0 0 10px;
}

#clientDetailPage-loanSection {
	background-color: #d7deee;
	width: 400px;
	height: 200px;
	margin: 0 0 0 0px;	
}

#clientDetailPage {
	margin: 0 0 0 200px;
}

#loanProductName {
   text-align: left;
}

#loanAmount {
   text-align: left;
}

#clientName {
   text-align: left;
}

.fontnormalboldorangeheading {

	text-decoration:none; 
	color:#CC6601;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 11pt;
	font-weight: bold;
}
#homePageHeader { 
   border-bottom: 6px #FF9600 solid; 
}

#standardPageContent {
	margin:0 0 0 200px;
}
	
#homePageContent {
	margin:0 0 0 200px;
}

#adminPageContent {
	margin:0 0 0 200px;
}

#logo { 
   float:left; 
}

#top-menu-bar { 
   padding:0px 0px 0 0px;
   margin:0px 0 0px 0;
}

ul#simple-menu {
   list-style-type:none;
   position:relative;
   height:63px;
   font-family:Arial,Verdana, Helvetica, sans-serif;
   font-size:9pt;
   font-weight:bold;margin:0px 0 0 0;
   padding:11px 0 0 0;
}

ul#simple-menu li {
      display:block;
      float:left;
      margin:34px 0 0 2px;
      height:27px;
   }

ul#simple-menu li.left {
   margin:0; 
}

ul#simple-menu li a {
   display:block;
   float:left;
   color:#000166;background:#F2D1A6;
   line-height:27px;
   text-decoration:none;
   padding:0 17px 0 18px;
   height:27px;
}
ul#simple-menu li a.right {
   padding-right:19px;
}

ul#simple-menu li a:hover{
   background:#FF9600;
}

ul#simple-menu li a.current{
   color:#000166;
   background:#FF9600;
}

ul#simple-menu li a.current:hover {
   color:#000166;
   background:#FF9600;
}

#top-right-links {
   width: 100%;
   text-align: right;
}

a#settings {
   height:20px;
   margin:0 0 0 0;
   font-family: Arial, Verdana, Helvetica, sans-serif;  
   font-size: 9pt;
}

a#logout {
   height:20px;
   margin:8px 18px 0 0;
   font-family: Arial, Verdana, Helvetica, sans-serif;  
   font-size: 9pt;
}

p.boldOrange {
	text-decoration:none;  
	color:#CC6601;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;	
}
	
div#page-content {
	padding-top: 15px;
	min-height: 480px;
	background-color: white;
	padding-left: 5px;
	padding-right: 5px;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	margin: 0 0 0 10px;
}
	
div#page-content h2 {
	color: #CC6601;
	font-size: 11pt;
	font-weight: bold;
}

div#page-content h3 {
	color: #CC6601;
	font-size: 11pt;
	font-weight: bold;
}

div#page-content h4 {
	color: black;
	font-size: small;
	font-weight: bold;
}

div#page-content table {
		font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
}

#login {
	background-color: white;
	margin:auto;
	width: 500px;
	border:1px #d7deee solid;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: small;
	color: black;
	overflow: hidden; /* a trick to get the div to stretch to accommodate the floats inside */
}

#login-header {
	text-align: left;
	background-color: #d7deee;
	font-size: larger;
	font-weight: bold;
	padding: 2px 10px 2px 10px;
}

#login-welcome {
	text-align: left;
	width: 30%;
	float: left;
	padding-left: 10px;
	padding-top: 10px;
	height: auto;
}

#login-interaction {
	width: 60%;
	float: right;
	overflow: hidden;
	border-left:1px #d7deee solid;
}

#login-table {
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: small;
	min-width: 250px;
	border-left:1px #d7deee solid;
	padding: 0px 10px 10px 10px;
}

table#login-table td.label {
	text-align: right;
}

table#login-table td#login-submit {
	padding-top: 20px;
}

#login-page-header {
	background-attachment: scroll;
	background-image: url(../images/logo.gif);
	background-repeat: no-repeat;
	background-position: left top;
	background-color: white;
	height: 75px;
	width: 100%;
	border-bottom: 6px orange solid;
}

.error-messages {
	color: red;
	font-weight: bold;
	padding: 10px 10px 0px 10px;
}

/* default styles for forms */

form {  /* set width in form, not fieldset (still takes up more room w/ fieldset width */
  margin: 0;
  padding: 0;

}

fieldset {  /* Setting border-color for the tag alone does not work in Firefox */
	border-color: #d7deee;
	border-width: 2px;
   border-style: solid;
   padding: 10px;
   margin: 0;
   width: 600px;
}

fieldset legend {
	font-size:10pt; /* bump up legend font size, not too large or it'll overwrite border on left */
                       /* be careful with padding, it'll shift the nice offset on top of border  */
}

fieldset label {
	display: block;  /* block float the labels to left column, set a width */
	float: left;
	padding: 0;
	margin: 5px 0 0; /* set top margin same as form input - textarea etc. elements */
	text-align: right;
	width: 300px;
}

fieldset input, form textarea, form select {
	/* display: inline; inline display must not be set or will hide submit buttons in IE 5x mac */
	width:auto;      /* set width of form elements to auto-size, otherwise watch for wrap on resize */
	margin:5px 0 0 10px; /* set margin on left of form elements rather than right of
                              label aligns textarea better in IE */
}

fieldset fieldset label:first-letter { /* use first-letter pseudo-class to underline accesskey, note that */
	text-decoration:underline;    /* Firefox 1.07 WIN and Explorer 5.2 Mac don't support first-letter */
                                    /* pseudo-class on legend elements, but do support it on label elements */
                                    /* we instead underline first letter on each label element and accesskey */
                                    /* each input. doing only legends would  lessens cognitive load */
                                   /* opera breaks after first letter underlined legends but not labels */
}

form small {
	display: block;
	margin: 0 0 5px 160px; /* instructions/comments left margin set to align w/ right column inputs */
	padding: 1px 3px;
	font-size: 88%;
}

fieldset .required{ /* uses class instead of div, more efficient */
	font-weight:bold;
}

/* form styles for login */

form#login {
  min-width: 400px;
  max-width: 600px;
  width: 450px;
}

/* form loan product overrides */

form.loan-product {
	width: 600px;
}

form.loan-product fieldset label{
	width: 300px;
}

/* Left pane */

.left-pane-heading-group {
   margin:0 0 0 10px;
}

.left-pane {
   margin-top: 2px;
   float: left;
   height: 100%;
   background:#F2D1A6;
   width: 180px;
}

.left-pane-top-spacer {
   height: 3px;
   background-color: #FFFFFF;
}

.left-pane-header {
   padding-top: 2px;
   padding-bottom: 2px;
   padding-left: 4px;
   font-family: Arial, Verdana, Helvetica, sans-serif;
   font-size: 10pt;
   font-weight: bold;
   color:#FFFFFF;
   background-color:#FF9600;
   }

.left-pane-content {
   padding-left: 5px;
   font-family: Arial, Verdana, Helvetica, sans-serif;
   font-size: 9pt;
}

.left-pane-content h2 {
   padding-left: 5px;
   font-family: Arial, Verdana, Helvetica, sans-serif;
   font-size: 9pt;
}

.left-pane-content a {
   font-weight: normal;
   font-size: 9pt;  
}

.content-pane {
   margin-top: 5px;
   margin-left: 5px; 
   width:800px;  
}

.content-pane h2 {
	color: red;
}

/* create client page */
.createClient form { 
  font-family:verdana,arial,sans-serif;
  margin: 0;
  padding: 0;
}

form fieldset.noborder {
  border-width: 1px;
  border-style: none;
  padding: 10px;
  margin: 0;
}

form fieldset.createClient legend {
   font-family: Arial, Verdana, Helvetica, sans-serif;
   font-size: 12pt;
}

form fieldset.createClient label {
   font-family: Arial, Verdana, Helvetica, sans-serif;
   font-size: 11pt;
}

/* Display a list on one line, each entry separated by "|" */
.navigation-list
{
list-style: none;
padding: 0;
margin: 0 0 0 10pt;
}

.navigation-list li
{
display: inline;
padding: 0;
margin: 0;
}

.navigation-list li:before { content: "| "; }
.navigation-list li:first-child:before { content: ""; }
}


/******************************/
/* END ADDITIONS FOR CHEETAH */
/******************************/

body {margin:0px; background-color:#FFFFF;}
a {
	text-decoration:underline; 
	color:#000166;
}
a:visited{
	text-decoration:underline; 
}
a:hover {
	text-decoration:none;
}

.bglightblue{
	background-color:#D7DEEE;
}
.bgorange{
	background-color:#FF9600;
}
.bgwhite{
	background-color:#FFFFFF;
}
.bgorangeleft{
	background-color: #F2D1A6;
}
.leftpanehead{
	padding-left: 8px;
	padding-top: 5px;
	padding-bottom: 5px;
	background-color:#FF9600;
	text-decoration:none; 
	color:#FFFFFF;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 10pt;
	font-weight: bold;
}
.leftpanelinks{
	padding-left: 8px;
	padding-top: 15px;
	padding-bottom: 8px;
}
.fontnormalbold{
	text-decoration:none; 
	color:#000000;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
}
.fontnormal{
	text-decoration:none; 
	color:#000000;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: normal;
}
.fontnormal8pt{
	text-decoration:none; 
	color:#000000;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 8pt;
	font-weight: normal;
}

.fontnormal8pt a{
	text-decoration:underline; 
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 8pt;
	color:#000166;
}
.fontnormal8pt a:visited{
	text-decoration:underline; 
}
.fontnormal8pt a:hover{
	text-decoration:none; 
}
.fontnormal8ptbold{
	text-decoration:none; 
	color:#000000;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 8pt;
	font-weight: bold;
}
.headingorange{
	text-decoration:none; 
	color:#CC6601;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 11pt;
	font-weight: bold;
}
.taborange{
	padding-left: 15px;
	padding-top: 5px;
	padding-bottom: 5px;
	padding-right: 15px;
	background-color:#FF9600;
	text-decoration:none; 
	color:#FFFFFF;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
}
.tablightorange{
	padding-left: 15px;
	padding-top: 5px;
	padding-bottom: 5px;
	padding-right: 15px;
	background-color:#F2D1A6;
	text-decoration:none; 
	color:#000065;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
}
.orangetableborder{
	border-left:1px #F0D4A5 solid;
	border-right:1px #F0D4A5 solid;
	border-bottom:1px #F0D4A5 solid;
}
.orangetablehead05{
	padding-left: 10px;
	padding-bottom: 4px;
	background-color:#F0D4A5;
	padding-top: 3px;

}

.bluetableborder{
	border-left:1px #D7DEEE solid;
	border-right:1px #D7DEEE solid;
	border-bottom:1px #D7DEEE solid;
}

.bluetablehead{
	padding-left: 5px;
	padding-top: 3px;
	padding-bottom: 3px;
	border: 1px solid #D7DEEE;
	padding-right: 3px;


}
.bluetablehead05{
	padding-left: 10px;
	padding-bottom: 4px;
	background-color:#D7DEEE;
	padding-top: 3px;

}

.paddingleft{
	padding-top: 15px;

}
.paddingleft05{
	padding-left: 5px;
	padding-top: 5px;

}
.paddingleft05BottomBorder{
	padding-left: 5px;
	padding-top: 5px;
	border-bottom:1px #D7DEEE solid;
}
.paddingleft05BottomLeftBorder{
	padding-left: 5px;
	padding-top: 5px;
	border-bottom:1px #D7DEEE solid;
	border-left:1px #D7DEEE solid;
}

.buttn
{
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: normal;
	color: #000000;
    border-right: #926030 1px solid;
	border-bottom: #926030 1px solid;
	border-left: #C29F7C 1px solid;
	border-top: #C29F7C 1px solid;
    background-image: url(../images/buttons/buttonbg.jpg);
	background-repeat: repeat-x;
    cursor: pointer;
    height: 20px
}

.cancelbuttn
{
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: normal;
	color: #000000;
    border-right: #696969 1px solid;
	border-bottom: #696969 1px solid;
	border-left: #A2A2A2 1px solid;
	border-top: #A2A2A2 1px solid;
    background-image: url(../images/buttons/buttonbgcancel.jpg);
	background-repeat: repeat-x;
    cursor: pointer;
    height: 20px
}
.insidebuttn
{
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 8pt;
	font-weight: normal;
	color: #000000;
    border-right: #696969 1px solid;
	border-bottom: #696969 1px solid;
	border-left: #A2A2A2 1px solid;
	border-top: #A2A2A2 1px solid;
    background-image: url(../images/buttons/buttonbgcancel.jpg);
	background-repeat: repeat-x;
    cursor: pointer;
    height: 17px
}
.insidebuttnRed
{
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 8pt;
	font-weight: bold;
	color: #ffffff;
    border-right: #696969 1px solid;
	border-bottom: #696969 1px solid;
	border-left: #A2A2A2 1px solid;
	border-top: #A2A2A2 1px solid;
    background-image: url(../images/buttons/buttonbgred.jpg);
	background-repeat: repeat-x;
    cursor: pointer;
    height: 20px
}

.drawtable {
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-right-color: #D7DEEE;
	border-bottom-color: #D7DEEE;
	border-left-color: #D7DEEE;

}
.drawtablehd {
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
	text-decoration: none;
	background-color: #D7DEEE;
	padding-top: 2px;
	padding-right: 5px;
	padding-bottom: 2px;
	padding-left: 5px;
	border-top-width: 1px;
	border-top-style: solid;
	border-top-color: #D7DEEE;

}
.drawtablerow {
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: normal;
	text-decoration: none;
	background-color: #FFFFFF;
	padding-top: 2px;
	padding-right: 5px;
	padding-bottom: 2px;
	padding-left: 5px;
	border-top-width: 1px;
	border-top-style: solid;
	border-top-color: #D7DEEE;
}
.drawtablerowbold {

	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
	text-decoration: none;
	background-color: #FFFFFF;
	padding-top: 2px;
	padding-right: 5px;
	padding-bottom: 3px;
	padding-left: 5px;
	border-top-width: 1px;
	border-top-style: solid;
	border-top-color: #D7DEEE;
	vertical-align: top;
}
.drawtablerownoline {

	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: normal;
	text-decoration: none;
	background-color: #FFFFFF;
	padding-top: 2px;
	padding-right: 5px;
	padding-bottom: 2px;
	padding-left: 5px;
}

.drawtablerowover {

	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: normal;
	text-decoration: none;
	background-color: #FFF9EE;
	padding-top: 2px;
	padding-right: 5px;
	padding-bottom: 2px;
	padding-left: 5px;
	border-top-width: 1px;
	border-top-style: solid;
	border-top-color: #D7DEEE;
}
input {
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: normal;
}
select {
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: normal;
}

.heading {

	text-decoration:none; 
	color:#000000;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 11pt;
	font-weight: bold;
}
.blueline {
	border-bottom:1px #D7DEEE solid;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 9pt;

}
.fontnormalboldorange {

	text-decoration:none; 
	color:#CC6601;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
}
.fontnormal7pt {

	text-decoration:none; 
	color:#000000;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 7pt;
	font-weight: normal;
}
.tableHeaderLightBrown{
	background-color: #F2D1A6;
	padding-top: 2px;
	padding-right: 5px;
	padding-bottom: 2px;
	padding-left: 10px;
}
.tableContentLightBlue{
	background-color: #D7DEEE;
	padding-top: 2px;
	padding-right: 5px;
	padding-bottom: 2px;
	padding-left: 10px;
}
.fontnormalorange {


	text-decoration:none; 
	color:#CC6601;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: normal;
}
.fontnormalboldorangelight {


	text-decoration:none; 
	color:#F4CC9C;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
}
.fontnormalboldgray {



	text-decoration:none; 
	color:#C6C6C6;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
}
.paddingtop05 {
	padding-top: 5px;
}
.paddingleft05notop {
	padding-left: 5px;


}
.paddingleft1 {
	padding-top: 15px;
	padding-left: 10px;

}
.paddingleftmain {
	padding-top: 5px;
	padding-left: 15px;

}
.paddingleft03 {
	padding-top: 5px;
}
.paddingright03 {
	padding-top: 5px;
	padding-right: 5px;

}
.paddingbottom03 {
	padding-bottom: 3px;

}
.timelineboldorange {
	text-decoration:none;
	color:#CC6601;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
	padding-right: 10px;
	padding-left: 5px;


}
.timelineboldorangelight {
	text-decoration:none;
	color:#F4CC9C;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
	padding-right: 10px;
	padding-left: 3px;


}
.timelineboldgray {
	text-decoration:none;
	color:#C6C6C6;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
	padding-right: 10px;
	padding-left: 3px;

}
.paddingleftCreates {
	padding-top: 15px;
	padding-left: 30px;

}
.tabfontwhite {
	background-color:#FF9600;
	text-decoration:none; 
	color:#FFFFFF;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
}
.headingblue {
	text-decoration:none; 
	color:#050259;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 11pt;
	font-weight: bold;
}
.headingblue a{
	text-decoration:underline; 
}
.headingblue a:visited{
	text-decoration:underline; 
}
.headingblue a:hover{
	text-decoration:none; 
}
.paddingleft20notop {

	padding-left: 20px;
}
.paddingleft40notop {


	padding-left: 40px;
}
.fontnormalRed {

	text-decoration:none; 
	color:#FF0000;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: normal;
}
.fontnormalRedBold {


	text-decoration:none; 
	color:#FF0000;
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
}
.paddingL30T15 {
	padding-top: 15px;
	padding-left: 30px;
}
.paddingL15T15 {
	padding-top: 15px;
	padding-left: 15px;
}
.paddingL10 {
	padding-left: 10px;
}
.bluetableborderFull {
	border: 1px solid #D7DEEE;

}
.drawtablerowboldnoline {


	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
	text-decoration: none;
	background-color: #FFFFFF;
	padding-top: 2px;
	padding-right: 5px;
	padding-bottom: 3px;
	padding-left: 5px;
	vertical-align: top;
}
.drawtablehdorange {
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
	text-decoration: none;
	background-color: #F0D4A5;
	padding-top: 2px;
	padding-right: 5px;
	padding-bottom: 2px;
	padding-left: 5px;
	border: 1px solid #F0D4A5;

}
.drawtablerowSmall {

	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 8pt;
	font-weight: normal;
	text-decoration: none;
	background-color: #FFFFFF;
	padding-top: 2px;
	padding-right: 5px;
	padding-bottom: 2px;
	padding-left: 5px;
	border-top-width: 1px;
	border-top-style: solid;
	border-top-color: #D7DEEE;
}
.drawtablerowboldSmall {


	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 8pt;
	font-weight: bold;
	text-decoration: none;
	background-color: #FFFFFF;
	padding-top: 2px;
	padding-right: 5px;
	padding-bottom: 3px;
	padding-left: 5px;
	border-top-width: 1px;
	border-top-style: solid;
	border-top-color: #D7DEEE;
	vertical-align: top;
}

.drawtablerowboldnolinebg {
	font-family: Arial, Verdana, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
	text-decoration: none;
	background-color: #D7DEEE;
	padding-top: 2px;
	padding-right: 5px;
	padding-bottom: 3px;
	padding-left: 5px;
	vertical-align: middle;
	height: 25px;

}




	