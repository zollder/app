<!DOCTYPE HTML>
<%@page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%
	// Constants (to be updated dynamically later)
	String localeId = "en";
%>
<html dir="ltr" lang="<%= localeId %>">
	<head>
		<title>Rest-Sample</title>
		<meta http-equiv="X-UA-Compatible" content="IE=IE9; chrome=1" />
		<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
		<meta http-equiv="content-language" content="<%= localeId %>" />
		<link rel="shortcut icon" href="images/favicon.ico" />
		<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" href="/rest-sample/css/dashboard.css" media="screen" type="text/css">
		<link rel="stylesheet" href="/toolkit/js/dojo/dijit/themes/claro/claro.css" media="screen" type="text/css">
	</head>
	<body class="claro">

		<!-- configure and initialize dojo -->
		<script>
			dojoConfig =
			{
				async: true,
				parseOnLoad: false,
				packages:
				[{
					name: 'gateway',
					location: '/rest-sample/js/gateway'
				}]
			};
		</script>
		<script
			src="/toolkit/js/dojo/dojo/dojo.js">
		</script>

		<!-- Load required modules and manually invoke parser -->
		<script>
			require
			(
				[
					"dojo/dom",
					"dojo/parser",
					"dijit/layout/BorderContainer",
					"dijit/layout/TabContainer",
					"dijit/layout/ContentPane",
					"dijit/layout/StackContainer",
					"dijit/layout/StackController",
					"dojo/domReady!"
				],
				function(dom, parser)
				{
					parser.parse();	// manually invoke parser
				}
			);
		</script>

		<!-- main placeholder (border container) for inner layout containers -->
		<div
			id = "appLayout"
			class = "mainLayout"
			data-dojo-type = "dijit/layout/BorderContainer"
			data-dojo-props = "design:'sidebar'">

			<!-- Header container (top): contains main application menu -->
			<div
				id="topContainer"
				class="edgePanel"
				data-dojo-type="dijit/layout/ContentPane"
				data-dojo-props="region: 'top'">

				<div
					data-dojo-type="dijit/layout/StackController"
					data-dojo-props="containerId:'contentStack'">
				</div>
			</div>

			<!-- Center container: holds a stack of ContentPanes -->
			<div
				id="centerContainer"
				class="centerPanel"
				data-dojo-type="dijit/layout/StackContainer"
				data-dojo-props="region:'center', id:'contentStack'">

				<!-- Dashboard pane -->
				<div data-dojo-type="dijit/layout/ContentPane" title="Dashboard">
					<h4>Dashboard</h4>
					<p>
						Dashboard content (tables, graphs, diagrams, etc.).
					</p>
				</div>

				<!-- Administration pane containing Users, Groups and Roles tabs -->
				<div
					id="administrationContainer"
					class="centerPanel"
					title="Administration"
					data-dojo-type="dijit/layout/TabContainer"
					data-dojo-props="region:'center', tabPosition:'top'">

					<div data-dojo-type="dijit/layout/ContentPane" title="Users">
						<h4>Users</h4>
						<p>User account administration panel</p>
					</div>

					<div data-dojo-type="dijit/layout/ContentPane" title="Groups">
						<h4>Groups</h4>
						<p>Groups administration panel</p>
					</div>

					<div data-dojo-type="dijit/layout/ContentPane" title="Roles">
						<h4>Roles</h4>
						<p>Roles administration panel</p>
					</div>
				</div>

				<!-- Device pane -->
				<div
					id="deviceContainer"
					title="Devices"
					class="centerPanel"
					data-dojo-type="dijit/layout/BorderContainer"
					data-dojo-props="design:'sidebar'">

						<div
							id="centerDeviceContainer"
							class="centerPanel"
							data-dojo-type="dijit/layout/ContentPane"
							data-dojo-props="region:'center'">
							<h4>Devices</h4>
							<p>
								Device management section:<br>
								- add new devices;<br>
								- modify or delete existing devices;<br>
								- group devices by type;<br>
								- search/filter device inventory<br>
								- create reports/graphs/device statistics? (TBD)<br>
								- scheduler? (TBD)<br>
							</p>
						</div>
						<div
							id="leftDeviceContainer"
							class="edgePanel"
							data-dojo-type="dijit/layout/ContentPane"
							data-dojo-props="region: 'left', splitter: true">
							Sidebar content (left)
						</div>
				</div>

				<!-- Scheduler pane -->
				<div data-dojo-type="dijit/layout/ContentPane" title="Scheduler">
					<h4>Scheduler</h4>
					<p>
						Device scheduler section (could also become a part of device section).
					</p>
				</div>

				<!-- Scheduler pane -->
				<div data-dojo-type="dijit/layout/ContentPane" title="Reports &amp; Graphs">
					<h4>Reports &amp; Graphs</h4>
					<p>
						Reports &amp; Graphs section:<br>
						- create detailed statistics about device operation;<br>
						- search/filter and print logs;<br>
						- create graphs and diagrams;<br>
					</p>
				</div>

			</div>

		</div>
	</body>
</html>