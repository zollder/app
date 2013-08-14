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
			([
				"dojo/dom",
				"dojo/parser",
				"dojo/on",
				"dojo/request",
				"dojo/json",
				"dijit/layout/BorderContainer",
				"dijit/layout/TabContainer",
				"dijit/layout/ContentPane",
				"dijit/layout/StackContainer",
				"dijit/layout/StackController",
				'dijit/form/Button',
				'dijit/form/CheckBox',
				'dijit/form/Form',
				'dijit/form/TextBox',
				'dijit/form/CheckBox',
				'dijit/form/DropDownButton',
				'dijit/TooltipDialog',
				'dijit/Dialog',
				'dojox/grid/DataGrid',
				"dojo/domReady!"
			],
			function(dom, parser, on, request, JSON)
			{
				parser.parse();	// manually invoke parser

				// Attach the onsubmit event handler of the form
				on( dom.byId("userContainer.insertUserDialog.save"), "click",
					function()
					{
						var userDialogData = 
						{
							"primaryKey" : "",
							"firstName" : dom.byId("userContainer.insertUserDialog.firstName").value,
							"lastName" : dom.byId("userContainer.insertUserDialog.lastName").value,
							"userName" : dom.byId("userContainer.insertUserDialog.userName").value,
							"password" : dom.byId("userContainer.insertUserDialog.password").value,
							"email" : dom.byId("userContainer.insertUserDialog.email").value,
							"isEnabled" : dom.byId("userContainer.insertUserDialog.isEnabled").value,
							"canLogin" : dom.byId("userContainer.insertUserDialog.canLogin").value,
							"isAdmin" : dom.byId("userContainer.insertUserDialog.isAdmin").value
						}

						// Post user data to the server
						request.post("user",
						{
							headers: { "Content-Type": 'application/json'},
							handleAs : "json",
							data : JSON.stringify(userDialogData)
						}).then(
						function(Response)
						{
							alert('Saved: ' + dojo.toJson(Response, true));
						},
						function(error)
						{
							alert("Your dialog data cannot be sent, try again.");
						},
						function(evt)
						{

						})
					}
				)
			});
		</script>

		<!-- main placeholder (border container) for inner layout containers -->
		<div
			id = "appLayout"
			class = "appLayout"
			data-dojo-type = "dijit/layout/BorderContainer"
			data-dojo-props = "design:'sidebar'">

			<!-- Header container (top): contains main application menu -->
			<div id="topContainer" class="edgePanel" data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region: 'top'">
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

				<!-- 1. Dashboard pane -->
				<div data-dojo-type="dijit/layout/ContentPane" title="Dashboard">
					<h4>Dashboard</h4>
					<p> Dashboard content (tables, graphs, diagrams, etc.). </p>
				</div>

				<!-- 2. Administration pane containing Users, Groups and Roles tabs -->
				<div
					id="adminContainer"
					class="centerPanel"
					title="Administration"
					data-dojo-type="dijit/layout/TabContainer"
					data-dojo-props="region:'center', tabPosition:'top'">

					<!-- 2.1 Users container -->
					<div id = "userContainer" title="Users"	data-dojo-type="dijit/layout/BorderContainer">
						<!-- User search form container -->
						<div data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region:'top',layoutPriority:1">

							<!-- Insert user button -->
							<button
								id="userContainer.insertUserButton"
								data-dojo-type="dijit.form.Button"
								type="button">
								<img src="/rest-sample/images/insert.png" width="16" height="16" />Insert
								<script type="dojo/method" data-dojo-event="onClick" data-dojo-args="evt">
									dijit.byId("userContainer.insertUserDialog").show();
								</script>
							</button>

							<!-- Insert user dialog portion -->
							<div
								id="userContainer.insertUserDialog"
								data-dojo-type="dijit.Dialog"
								title="Insert User"
								execute="alert('submitted w/args:\n' + dojo.toJson(arguments[0], true));">

								<table>
									<tr>
										<td><label for="firstName">First name: </label></td>
										<td><input data-dojo-type="dijit.form.TextBox" type="text" data-dojo-props="required:true" name="firstName" id="userContainer.insertUserDialog.firstName"></td>
									</tr>
									<tr>
										<td><label for="lastName">Last name: </label></td>
										<td><input data-dojo-type="dijit.form.TextBox" type="text" data-dojo-props="required:true" name="lastName" id="userContainer.insertUserDialog.lastName"></td>
									</tr>
									<tr>
										<td><label for="userName">Username: </label></td>
										<td><input data-dojo-type="dijit.form.TextBox" type="text" data-dojo-props="required:true" name="userName" id="userContainer.insertUserDialog.userName"></td>
									</tr>
									<tr>
										<td><label for="password">Password: </label></td>
										<td><input data-dojo-type="dijit.form.TextBox" type="text" data-dojo-props="required:true" name="password" id="userContainer.insertUserDialog.password"></td>
									</tr>
									<tr>
										<td><label for="email">Email: </label></td>
										<td><input data-dojo-type="dijit.form.TextBox" type="text" data-dojo-props="required:true" name="email" id="userContainer.insertUserDialog.email"></td>
									</tr>
									<tr>
										<td><label for="isEnabled">Is enabled: </label></td>
										<td><input data-dojo-type="dijit.form.CheckBox" type="checkbox" name="isEnabled" value="true" id="userContainer.insertUserDialog.isEnabled"></td>
									</tr>
									<tr>
										<td><label for="canLogin">Can login: </label></td>
										<td><input data-dojo-type="dijit.form.CheckBox" type="checkbox" name="canLogin" value="true" id="userContainer.insertUserDialog.canLogin"></td>
									</tr>
									<tr>
										<td><label for="isAdmin">Is admin: </label></td>
										<td><input data-dojo-type="dijit.form.CheckBox" type="checkbox" name="isAdmin" value="true" id="userContainer.insertUserDialog.isAdmin"></td>
									</tr>
									<tr>
										<td align="center" colspan="2">
											<button
												id="userContainer.insertUserDialog.save"
												data-dojo-type="dijit.form.Button"
												type="submit"
												data-dojo-props="onClick:function(){ return dijit.byId('userContainer.insertUserDialog').isValid(); }">Save
											</button>
											<button
												id="userContainer.insertUserDialog.cancel"
												data-dojo-type="dijit.form.Button"
												type="button"
												data-dojo-props="onClick:function(){ dijit.byId('userContainer.insertUserDialog').hide(); }">Cancel
											</button>
										</td>
									</tr>
								</table>
							</div>

							<!-- Search user button -->
							<div
								id="userContainer.searchUserButton"
								data-dojo-type="dijit/form/DropDownButton">
								<!-- Button text-->
								<span>Search</span>
								<!-- The dialog portion -->
								<div data-dojo-type="dijit/TooltipDialog" id="userContainer.searchUserDialog">
									<label for="userContainer.userSearchForm.userCode" style="display:inline-block; width:100px;">Code:</label>
									<div data-dojo-type="dijit/form/TextBox" id="userContainer.userSearchForm.userCode"></div>
										<br />
									<label for="userContainer.userSearchForm.userName" style="display:inline-block;width:100px;">Username:</label>
									<div data-dojo-type="dijit/form/TextBox" id="userContainer.userSearchForm.userName"></div>
										<br />
									<label for="userContainer.userSearchForm.email" style="display:inline-block;width:100px;">Email:</label>
									<div data-dojo-type="dijit/form/TextBox" id="userContainer.userSearchForm.email"></div>
										<br />
									<button
										data-dojo-type="dijit/form/Button"
										type="submit"
										data-dojo-props="onClick:function(){alert('Searching ...')}">
										Submit
									</button>
								</div>
							</div>
						</div>
						<!-- User list (header with user-related menu items) -->
						<div data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region:'top',layoutPriority:2,style:'border:1px solid #99CDFF;padding:5px;'">
							<span id="userContainer.userListCount">Users List (0)</span>
							<img src="/rest-sample/images/delete_Disabled.png" width="16" height="16" hspace="2" title="Delete" style="float:right;" />
							<img src="/rest-sample/images/edit_Disabled.png" width="16" height="16" hspace="2" title="Edit" style="float:right;" />
							<img
								src="/rest-sample/images/insert.png"
								width="16" height="16" hspace="2" title="Insert"
								style="float:right;"
								onclick=dijit.byId("userContainer.insertUserDialog").show();
							/>
						</div>
						<!-- User list (table) -->
						<table id="userContainer.userList" data-dojo-type="dojox.grid.DataGrid" data-dojo-props="region:'center',style:'min-height:120px;'">
							<thead>
								<tr>
									<th field="lastName" width="20%">Last Name</th>
									<th field="firstName" width="20%">First Name</th>
									<th field="userName" width="20%">User Name</th>
									<th field="email" width="20%">Email</th>
									<th field="isAdmin" width="10%">Is Administrator</th>
								</tr>
							</thead>
						</table>
					</div>

					<!-- 2.2 Groups container -->
					<div id="groupContainer" title="Groups" data-dojo-type="dijit/layout/ContentPane">
						<h4>Groups</h4>
						<p>Groups administration panel</p>
					</div>

					<!-- 2.2 Roles container -->
					<div id="rolesContainer" title="Roles" data-dojo-type="dijit/layout/ContentPane">
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