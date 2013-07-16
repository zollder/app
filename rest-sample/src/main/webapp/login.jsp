<!doctype html>
<%@page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%String localeId = "en";%>
<html dir="ltr" lang="<%= localeId %>" style="height:100%;">
	<head>
		<title>RestSample</title>
		<meta http-equiv="X-UA-Compatible" content="chrome=1" />
		<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
		<meta http-equiv="content-language" content="<%= localeId %>" />
		<link rel="shortcut icon" href="images/favicon.ico" />
		<link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
		<link href="/toolkit/js/dojo/dojo/resources/dojo.css" rel="stylesheet" type="text/css" />
		<link href="/toolkit/js/dojo/dijit/themes/claro/claro.css" rel="stylesheet" type="text/css" />
		<link href="/rest-sample/css/login.css" rel="stylesheet" type="text/css" />
	</head>
	<body class="claro">

		<!-- configure and initialize dojo -->
		<script
			data-dojo-config="
				async: true,
				baseUrl: '/toolkit/js/dojo/dojo/',
				dojoBlankHtmlUrl: '/toolkit/dojo_blank.html',
				isDebug: false,
				locale: 'en',
				parseOnLoad: false,
				packages: [{ name: 'js', location: '/restSample/js'}],
				useXDomain: false
			"
			src="/toolkit/js/dojo/dojo/dojo.js"
			type="text/javascript"
		></script>

		<!-- create login form with all required buttons and/or checkboxes -->
		<div id="restSampleContext">
			<img id="restSampleLogo" alt="RestSample" title="RestSample" src="images/rest-sample-logo.png">

			<div id="restSampleLogin" style="float:left">
				<form id="restSampleLoginForm" data-dojo-type="dijit.form.Form" method="post" action="/rest-sample/j_spring_security_check">
					<h2>Application Login</h2>
					<br/>
					<br/>
					<div class="field">
						<label for="j_username">User:</label>
						<input
							type="text"
							style="width: 240px;border:1px solid #B5BCC7;height:20px;"
							data-dojo-props="required:true"
							id="j_username"
							name="j_username"
							placeHolder="Your identifier"
						/>
					</div>
					<div class="field">
						<label for="j_password">Password:</label>
						<input
							type="password"
							style="width: 240px;border:1px solid #B5BCC7;height:20px;"
							data-dojo-props="required:true"
							id="j_password"
							name="j_password"
							placeHolder="Your password"
						/>
					</div>
					<br/>
					<div class="logMe">
						<button id="loginButton" data-dojo-type="dijit.form.Button" type="submit">Login</button>
					</div>
					<%if(request.getParameter("login_error") != null)
						{%><span class="errorMsg">Your login attempt failed. Please, verify your login details.<%}%></span>
					<br/>
					<br/>
					<br/>
					<br/>
				</form>
			</div>
		</div>

		<!-- Provides some intelligence around focus control -->
		<script type="text/javascript">
			// TODO: implement notification area (see example in /mei/Common.js and the original login page implementation)
			require(
				[
					'dojo/dom',
					'dojo/parser',
					'dojo/ready',
					'dijit/form/CheckBox',
					'dijit/form/Form',
					'dijit/form/TextBox',
					"dojo/domReady!"
				],
				function(dom, parser, ready, CheckBox, Form, TextBox)
				{
					parser.parse();

					ready(function()
					{
						var usernameField = dom.byId('j_username');
						var passwordField = dom.byId('j_password');

						// Moves the focus to the first empty form field or the submit button
						if (usernameField.value == '')
						{
							usernameField.focus();
						}
						else if (passwordField.value == '')
						{
							passwordField.focus();
						}
						else
						{
							dom.byId('loginButton').focus();
						}
					});
				}
			);
		</script>
	</body>
</html>