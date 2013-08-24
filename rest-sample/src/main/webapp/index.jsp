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
		<link rel="stylesheet" href="/toolkit/js/dojo/dojox/grid/resources/Grid.css" type="text/css" />
		<link rel="stylesheet" href="/toolkit/js/dojo/dojox/grid/resources/claroGrid.css" type="text/css" />
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
				"dijit/registry",
				"dojo/parser",
				"dojo/on",
				"dojo/request",
				"dojo/json",
				'dojox/grid/DataGrid',
				"dojo/data/ObjectStore",
				"dojo/store/Memory",
				"dijit/layout/BorderContainer",
				"dijit/layout/TabContainer",
				"dijit/layout/ContentPane",
				"dijit/layout/StackContainer",
				"dijit/layout/StackController",
				"dijit/form/Button",
				"dijit/form/CheckBox",
				"dijit/form/Form",
				"dijit/form/TextBox",
				"dijit/form/Textarea",
				"dijit/form/CheckBox",
				"dijit/form/DropDownButton",
				"dijit/TooltipDialog",
				"dijit/Dialog",
				"dojo/domReady!"
			],
			function(dom, registry, parser, on, request, JSON, DataGrid, ObjectStore, Memory, Button, Form, Textarea)
			{
				parser.parse();	// manually invoke parser

				var userGrid, userDataStore, deleteUserDialog, editUserDialog;
				var mainLayout = this;

				request.get("user/all",
				{
					headers: { "Content-Type": 'application/json'},
					handleAs : "json"
				}).then(
				function(data)
				{
					var objectStore = new Memory({ data: data, idProperty: "primaryKey" });

					// global user store
					userDataStore = new ObjectStore({ objectStore: objectStore});

					// user grid structure
					var userGridStructure = [
						{ name: "primaryKey",	field: "primaryKey",	hidden: true },
						{ name: "First Name",	field: "firstName",		width: "100px" },
						{ name: "Last Name",	field: "lastName",		width: "100px" },
						{ name: "Username",		field: "userName",		width: "100px" },
						{ name: "password",		field: "password",		hidden: true },
						{ name: "E-mail",		field: "email",			width: "100px" },
						{ name: "isEnabled",	field: "isEnabled",		width: "100px" },
						{ name: "canLogin",		field: "canLogin",		width: "100px" },
						{ name: "isAdmin",		field: "isAdmin",		width: "100px" },
						{ name: "newPassword",	field: "newPassword",	hidden: true },
						{ name: "confirmPassword", field: "confirmPassword", hidden: true }
					];

					// create user grid
					userGrid = new DataGrid(
					{
						store: userDataStore,
						query: { id: "*" },
						queryOptions: {},
						selectionMode: "single",
						structure: userGridStructure,
						id: "primaryKey",
						onRowDblClick: function(evt)
						{
							createEditDialog();
						},
						onSelectionChanged: function()
						{
							if (userGrid.canEdit() && userGrid.canDelete())
							{
								document["userContainer.deleteUserImg"].src = "/rest-sample/images/delete.png";
								document["userContainer.editUserImg"].src = "/rest-sample/images/edit.png"
							}
							else
							{
								document["userContainer.deleteUserImg"].src = "/rest-sample/images/delete_Disabled.png";
								document["userContainer.editUserImg"].src = "/rest-sample/images/edit_Disabled.png"
							}
						},
						canEdit: function()
						{
							if (userGrid.selection.getSelectedCount() > 0)
								return true;
							else
								return false;
						},
						canDelete: function()
						{
							if (userGrid.selection.getSelectedCount() > 0)
								return true;
							else
								return false;
						}
					}, "userContainer.userGrid");

					// render the table
					userGrid.startup();

					userGrid.on("RowClick",
					function(evt)
						{
							var rowIndex = evt.rowIndex;
							var rowData = userGrid.getItem(rowIndex);
							document.getElementById("userContainer.rowClickResult").innerHTML = "You have clicked on " + rowData.firstName + ", " + rowData.lastName + ".";
						}, true);
				});

				// Attach the onsubmit event handler of the user insert form
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
						};

						// Post user data to the server
						request.post("user",
						{
							headers: { "Content-Type": 'application/json'},
							handleAs : "json",
							data : JSON.stringify(userDialogData)
						}).then(
						function(data)
						{
							userDataStore.newItem(data);
							userDataStore.save();
						},
						function(error)
						{
							alert("Your dialog data cannot be sent, try again.");
						},
						function(evt)
						{})
					}
				);

				// Attach the onsubmit event handler of the user edit form
				on( dom.byId("userContainer.editUserDialog.update"), "click",
					function()
					{
						var userDialogData = editUserDialog.getValues();
						// checkboxes need special handling
						userDialogData.isAdmin = userDialogData.isAdmin[0];
						userDialogData.isEnabled = userDialogData.isEnabled[0];
						userDialogData.canLogin = userDialogData.canLogin[0];

						// Post user data to the server
						var target = "user/" + userDialogData["primaryKey"];
						request.put(target,
						{
							headers: { "Content-Type": 'application/json'},
							handleAs : "json",
							data : JSON.stringify(userDialogData)
						}).then(
						function(data)
						{
							if (!userDataStore.isItem(data))
							{
								// TODO: throw exception
								alert("something wrong happend")
							}

							var objStore = userGrid.store.objectStore;

							if (objStore.getIdentity(data) == data.primaryKey)
								objStore.put(data);
							else
								objStore.add(data)

							userGrid.store.save();
							userGrid.store.close();
							userGrid.render();
						},
						function(error)
						{
							alert("Your dialog data cannot be sent, try again.");
						},
						function(evt)
						{})
					}
				);

				createEditDialog = function()
				{
					var selectedRecord;

					if (userGrid.selection.getSelectedCount() > 0)
						selectedRecord = userGrid.selection.getSelected()[0];
					else
						return;

					editUserDialog = dijit.byId('userContainer.editUserDialog');

					// populate the form with selected values
					var selectedTextboxValues =
					{
						primaryKey: selectedRecord.primaryKey,
						firstName: selectedRecord.firstName,
						lastName: selectedRecord.lastName,
						userName: selectedRecord.userName,
						email: selectedRecord.email
					};

					editUserDialog.setValues(selectedTextboxValues);

					// populating checkboxes requires special handling
					dijit.byId('editUserDialog.isAdmin').set("checked", selectedRecord.isAdmin);
					dijit.byId('editUserDialog.isEnabled').set("checked", selectedRecord.isEnabled);
					dijit.byId('editUserDialog.canLogin').set("checked", selectedRecord.canLogin);

					// open the dialog
					editUserDialog.show();
				};

				createDeleteDialog = function()
				{
					deleteUserDialog = new dijit.Dialog(
					{
						title: "Delete user(s)",
						style: "width: 300px;",
						content: "Confirm user removal." + "<br/><br/><button onclick='deleteUser();'>Delete</button><button onclick='closeDeleteDialog();'>Cancel</button>"
					});

					deleteUserDialog.show();
				};

				deleteUser = function()
				{
					var selectedRecord, primaryKey;
					if (userGrid.selection.getSelectedCount() > 0)
					{
						selectedRecord = userGrid.selection.getSelected()[0];
						primaryKey = selectedRecord.primaryKey;
					}

					if (primaryKey != null)
					{
						var target = "user/" + primaryKey;
						// Post user data to the server
						request.del(target,
						{
							headers: { "Content-Type": 'application/json'},
							handleAs : "json"
						}).then(
						function(data)
						{
							this.closeDeleteDialog();
							var selection = userGrid.selection.getSelected()[0];
							userDataStore.deleteItem(selection);
						},
						function(error)
						{
							alert("User cannot be deleted, please try again.");
						},
						function(evt)
						{})
					}
				};

				closeDeleteDialog = function()
				{
					deleteUserDialog.hide();
				};
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

							<!-- Edit user dialog portion -->
							<div
								id="userContainer.editUserDialog"
								data-dojo-type="dijit.Dialog"
								title="Edit User"
								execute="alert('submitted w/args:\n' + dojo.toJson(arguments[0], true));">

								<table>
									<tr><td><input id="editUserDialog.primaryKey" data-dojo-type="dijit.form.TextBox" type="hidden" name="primaryKey"></td></tr>
									<tr>
										<td><label for="firstName">First name: </label></td>
										<td><input id="editUserDialog.firstName" data-dojo-type="dijit.form.TextBox" type="text" data-dojo-props="required:true" name="firstName"></td>
									</tr>
									<tr>
										<td><label for="lastName">Last name: </label></td>
										<td><input id="editUserDialog.lastName" data-dojo-type="dijit.form.TextBox" type="text" data-dojo-props="required:true" name="lastName"></td>
									</tr>
									<tr>
										<td><label for="userName">Username: </label></td>
										<td><input id="editUserDialog.userName" data-dojo-type="dijit.form.TextBox" type="text" data-dojo-props="required:true" name="userName"></td>
									</tr>
									<tr>
										<td><label for="email">Email: </label></td>
										<td><input id="editUserDialog.email" data-dojo-type="dijit.form.TextBox" type="text" data-dojo-props="required:true" name="email"></td>
									</tr>
									<tr>
										<td><label for="isEnabled">Is enabled: </label></td>
										<td><input id="editUserDialog.isEnabled" data-dojo-type="dijit.form.CheckBox" type="checkbox" name="isEnabled" value="true"></td>
									</tr>
									<tr>
										<td><label for="canLogin">Can login: </label></td>
										<td><input id="editUserDialog.canLogin" data-dojo-type="dijit.form.CheckBox" type="checkbox" name="canLogin" value="true"></td>
									</tr>
									<tr>
										<td><label for="isAdmin">Is admin: </label></td>
										<td><input id="editUserDialog.isAdmin" data-dojo-type="dijit.form.CheckBox" type="checkbox" name="isAdmin" value="true"></td>
									</tr>
									<tr>
										<td align="center" colspan="2">
											<button
												id="userContainer.editUserDialog.update"
												data-dojo-type="dijit.form.Button"
												type="submit"
												data-dojo-props="onClick:function(){ return dijit.byId('userContainer.editUserDialog').isValid(); }">Update
											</button>
											<button
												id="userContainer.editUserDialog.cancel"
												data-dojo-type="dijit.form.Button"
												type="button"
												data-dojo-props="onClick:function(){ dijit.byId('userContainer.editUserDialog').hide(); }">Cancel
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
							<span id="userContainer.userListCount">Users List</span>
							<img
								id="userContainer.deleteUserImg"
								src="/rest-sample/images/delete_Disabled.png"
								width="16" height="16" hspace="2" title="Delete"
								style="float:right;"
								onclick="createDeleteDialog();"
							/>
							<img
								id="userContainer.editUserImg"
								src="/rest-sample/images/edit_Disabled.png"
								width="16" height="16" hspace="2" title="Edit"
								style="float:right;"
								onclick="createEditDialog();"
							/>
							<img
								id="userContainer.insertUserImg"
								src="/rest-sample/images/insert.png"
								width="16" height="16" hspace="2" title="Insert"
								style="float:right;"
								onclick=dijit.byId("userContainer.insertUserDialog").show();
							/>
						</div>
						<!-- User list (grid) -->
						<div data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region:'center',layoutPriority:2,style:'border:1px solid #99CDFF;padding:5px;'">
							<div id="userContainer.userGrid"></div>
						</div>
						<!-- User selection result -->
						<div data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region:'bottom',layoutPriority:2,style:'border:1px solid #99CDFF;padding:5px;'">
							<div id="userContainer.rowClickResult" class="results"></div>
						</div>
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