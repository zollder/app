<!doctype html>
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
	<link href="/toolkit/js/dojo/dojo/resources/dojo.css" rel="stylesheet" type="text/css" />
	<link href="/toolkit/js/dojo/dijit/themes/claro/claro.css" rel="stylesheet" type="text/css" />
	<link href="/toolkit/js/dojo/dojox/grid/resources/Grid.css" rel="stylesheet" type="text/css" />
	<link href="/toolkit/js/dojo/dojox/grid/resources/claroGrid.css" rel="stylesheet" type="text/css" />
	<link href="/rest-sample/css/dashboard.css" rel="stylesheet" type="text/css" />
</head>
<body class="claro">
	<div id="introFlash" style="position:absolute; top:0; left:0; width:100%; height:100%;">
		<div style="position:absolute; top:40%; text-align:center; width:100%;">
			<span style="background-color:#bbdfFF; border:1px solid #99CDFF; border-radius:5px; padding:4px;">
				Please, wait while the Console is being built...
			</span>
		</div>
	</div>

<!-- 	<div id="notificationArea">
		<div style="float:right;">
			<a	href="javascript:mei.Common.hideNotificationArea()"
				style="text-decoration:none; color:gray; border:1px solid lightgrey; padding:2px 3px;">X</a>
		</div>
		<img id="notificationLevelIcon" style="width:16px; height:16px; vertical-align:middle; margin-right:10px;"/>
		<span id="notificationMessage"></span>
	</div> -->

	<!-- Loader configuration -->
	<script>
		dojoConfig =
		{
			async : true,
			baseUrl : '/toolkit/js/dojo/dojo/',
			dojoBlankHtmlUrl: '/toolkit/dojo_blank.html',
			locale: '<%= localeId %>',
			parseOnLoad : false,
			has : {	"dojo-firebug":true, "dojo-firebug-messages":true },
			packages:
			[{
				name: 'gateway',
				location: '/rest-sample/js/gateway'
			}]
		};
	</script>

	<!-- Load Dojo, Dijit, and DojoX resources -->
	<script>
		src = "/toolkit/js/dojo/dojo/dojo.js";
	</script>

	<div data-dojo-type="dijit.layout.BorderContainer" data-dojo-props="gutters:false; style:'height:100%; padding:5px 10px;'">

		<!-- Top Bar with icons and menu bar -->
		<div data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region:'top',layoutPriority:1" style="margin-right:40px;">

			<!-- Login button, Support button, and Help link -->
			<div style="float:right;padding-right:10px;">
				<div data-dojo-type="dijit.form.DropDownButton" data-dojo-props="">
					<span>Erika Polinski</span>
					<div data-dojo-type="dijit.Menu" style="display:none;">
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Preferences</div>
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="onClick:function() { location = '/rest-sample/j_spring_security_logout'; }">
							Logout
						</div>
					</div>
				</div>
				<div data-dojo-type="dijit.form.DropDownButton" data-dojo-props="">
					<span>Support</span>
					<div data-dojo-type="dijit.Menu" style="display:none;">
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Community</div>
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Contact</div>
					</div>
				</div>
				<a href="javascript:alert('Not yet implemented!')" style="text-decoration:none;color:black;">
					<img src="/rest-sample/images/mei/help.png" style="vertical-align:middle;width:16px;height:16px;"/> Help
				</a>
			</div>

			<!--
				Menu bar
			-->
			<div
				id="header"
				data-dojo-type="dijit.MenuBar"
				data-dojo-props=""
				style="
					float:right;
					clear:right;
					border:1px solid #99CDFF;
					border-bottom:1px solid transparent;border-top-left-radius:5px;
					border-top-right-radius:5px;
					background:url(/rest-sample/images/mei/app_title_bar_stretch.png) repeat-x left -1px;">
				<div data-dojo-type="dijit.MenuBarItem" data-dojo-props="disabled:true">
					<span>Dashboard</span>
				</div>
				<div data-dojo-type="dijit.PopupMenuBarItem">
					<span>Planning &amp; Analysis</span>
					<div data-dojo-type="dijit.Menu">
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">New...</div>
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Open...</div>
						<div data-dojo-type="dijit.PopupMenuItem">
							<span>Open Recent</span>
							<div data-dojo-type="dijit.Menu">
								<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">A &amp; P</div>
								<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Heinz</div>
							</div>
						</div>
					</div>
				</div>
				<div data-dojo-type="dijit.PopupMenuBarItem">
					<span>Promotions</span>
					<div data-dojo-type="dijit.Menu">
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">New...</div>
						<div data-dojo-type="dijit.MenuItem" id="menuPromotionOpenButton">Open...</div>
						<div data-dojo-type="dijit.PopupMenuItem" data-dojo-props="disabled:true">
							<span>Open Recent</span>
							<div data-dojo-type="dijit.Menu"></div>
						</div>
					</div>
				</div>
				<div data-dojo-type="dijit.PopupMenuBarItem">
					<span>Settlements</span>
					<div data-dojo-type="dijit.Menu">
						<div data-dojo-type="dijit.MenuItem" id="menuInvoiceOpenButton">Invoices</div>
						<div data-dojo-type="dijit.PopupMenuItem" data-dojo-props="disabled:true">
							<span>Deductions</span>
							<div data-dojo-type="dijit.Menu">
								<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">New...</div>
								<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Open...</div>
								<div data-dojo-type="dijit.PopupMenuItem">
									<span>Open Recent</span>
									<div data-dojo-type="dijit.Menu">
										<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">MEI-984343</div>
										<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">MEI-453453</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div data-dojo-type="dijit.PopupMenuBarItem">
					<span>Administration</span>
					<div data-dojo-type="dijit.Menu">
						<div data-dojo-type="dijit.PopupMenuItem">
							<span>Manage Dimensions</span>
								<div data-dojo-type="dijit.Menu">
									<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Accounts</div>
									<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Products</div>
									<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Funds</div>
									<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Performance Types</div>
								</div>
						</div>
						<div data-dojo-type="dijit.PopupMenuItem">
							<span>Manage Prices</span>
								<div data-dojo-type="dijit.Menu">
									<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Direct</div>
									<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Indirect</div>
									<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Scan</div>
								</div>
						</div>
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Manage Bills of Materials</div>
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Manage Indirect Sales Ratio</div>
						<div data-dojo-type="dijit.MenuSeparator"></div>
						<div data-dojo-type="dijit.MenuItem" id="menuManageUsersButton">Manage Users</div>
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Manage Sales Adjustments</div>
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Search System Logs</div>
						<div data-dojo-type="dijit.MenuSeparator"></div>
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Manage Integration</div>
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Integration Documentation</div>
						<div data-dojo-type="dijit.MenuSeparator"></div>
						<div data-dojo-type="dijit.MenuItem" id="menuGettingStartedButton">Getting Started</div>
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">About TradeInsight...</div>
					</div>
				</div>
				<div data-dojo-type="dijit.PopupMenuBarItem">
					<span>Configuration</span>
					<div data-dojo-type="dijit.Menu">
						<div data-dojo-type="dijit.PopupMenuItem">
							<span>Configure Dimensions</span>
								<div data-dojo-type="dijit.Menu">
									<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Accounts</div>
									<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Products</div>
									<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Funds</div>
									<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Performance Types</div>
								</div>
						</div>
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Configure Proces</div>
						<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Setup Package Types</div>
						<div data-dojo-type="dijit.PopupMenuItem">
							<span>Generate Periods</span>
								<div data-dojo-type="dijit.Menu">
									<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Fiscal Calendars</div>
									<div data-dojo-type="dijit.MenuItem" data-dojo-props="disabled:true">Natural Calendars</div>
								</div>
						</div>
						<div data-dojo-type="dijit.MenuItem" id="menuSystemParametersButton">Manager System Parameters</div>
					</div>
				</div>
			</div>
			<img src="/rest-sample/images/mei/trade-insight-logo-small.png" width="121" height="44" />
		</div>

		<!-- Page title -->
		<div
			id="pageTitle"
			data-dojo-type="dijit.layout.ContentPane"
			data-dojo-props="
				region:'top',
				layoutPriority:2,
				style:'
					height:18px;
					padding:6px;
					font-size:larger;
					font-weight:bold;
					border:1px solid #99CDFF;
					border-top-left-radius:5px;
					border-top-right-radius:5px;
					background:url(/rest-sample/images/mei/app_title_bar_stretch.png) left -1px;'">
		</div>

		<!-- Central Page -->
		<!--span data-dojo-type="dijit.layout.StackController" data-dojo-props="containerId:'pageContent'"></span-->
		<div id="pageContent" data-dojo-type="dijit.layout.StackContainer" data-dojo-props="region:'center',doLayout:true,style:'border-left:1px solid #99CDFF;border-right:1px solid #99CDFF;'">

			<!-- Getting Started page -->
			<div id="gettingStartedPage" data-dojo-type="dijit.layout.BorderContainer" data-dojo-props="selected:true,gutters:false,style:'padding:10px;overflow:auto;'">
				<div style="font-weight:bold;padding:10px;text-align:center;">
					<img src="/rest-sample/images/mei/Getting_Started.png" style="margin:10px 0;" usemap="gettingStartedMap"/>
					<div>For a complete overview of TradeInsight, click <a href="#">here</a>.</div>
					<map name="gettingStartedMap">
						<area id="imagePromotionOpenArea" shape="rect" coords="0,155,160,320" alt="Manage Promotion" title="Manage Promotion" />
						<area id="imageInvoiceOpenArea" shape="rect" coords="550,170,840,320" alt="Manage Invoices" title="Manage Invoices" />
						<area id="imageManageUsersArea" shape="rect" coords="840,155,990,320" alt="Manage Users" title="Manage Users" />
					</map>
				</div>
			</div>

			<!-- Promotions > Promotion Search page -->
			<div id="promotionSearchPage" data-dojo-type="dijit.layout.BorderContainer" data-dojo-props="gutters:false,style:'background-color:#EFF3FC;padding:10px;overflow:auto;'">
				<div data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region:'top',layoutPriority:1,splitter:true">
					<form data-dojo-type="dijit.form.Form" id="promotionSearchPage.promotionSearchForm">
						<table style="width:100%">
							<tr>
								<td width="40%">
									<fieldset style="border:1px inset #bbb;border-radius:5px;padding:0 5px;margin-right:10px;">
										<legend style="margin-left:10px;font-weight:bold;">Promotion Criteria</legend>
										<table style="width:100%">
											<tr>
												<td style="text-align:right;padding-right:5px;width:110px;height:22px;"><label for="promotionSearchPage.promotionSearchForm.name">Promotion Name:</label></td>
												<td colspan="2"><div id="promotionSearchPage.promotionSearchForm.name" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:8em;width:100%;'"></div></td>
												<td style="text-align:right;padding-right:5px;width:50px;"><label for="promotionSearchPage.promotionSearchForm.status">Status:</label></td>
												<td>
													<select id="promotionSearchPage.promotionSearchForm.status" data-dojo-type="dijit.form.Select" data-dojo-props="style:'min-width:8em;width:100%'">
														<option value="">&nbsp;</option>
														<option value="PENDING">Pending</option>
														<option value="SUBMITTED">Submitted</option>
														<option value="APPROVED">Approved</option>
														<option value="REJECTED">Rejected</option>
														<option value="RUNNING">Running</option>
														<option value="COMPLETED">Completed</option>
														<option value="CLOSED">Closed</option>
														<option value="CANCELLED">Cancelled</option>
													</select>
												</td>
											</tr>
											<tr>
												<td style="text-align:right;padding-right:5px;height:22px;"><label for="promotionSearchPage.promotionSearchForm.code">Promotion Code:</label></td>
												<td colspan="2"><div id="promotionSearchPage.promotionSearchForm.code" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:8em;width:100%;'"></div></td>
												<td style="text-align:right;padding-right:5px;"></td>
												<td><div id="promotionSearchPage.promotionSearchForm.toApprove" data-dojo-type="dijit.form.CheckBox" data-dojo-props=""></div> <label for="promotionSearchPage.promotionSearchForm.toApprove">To Approve</label></td>
											</tr>
											<tr>
												<td style="text-align:right;padding-right:5px;height:22px;"><label for="promotionSearchPage.promotionSearchForm.negociateWith">Negociate Account:</label></td>
												<td><div id="promotionSearchPage.promotionSearchForm.negociateWith" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:7em;width:100%;'"></div></td>
												<td style="width:18px;text-align:right;"><img src="/rest-sample/images/mei/cleared.png" height="16" width="16"></td>
											</tr>
											<tr>
												<td style="text-align:right;padding-right:5px;height:22px;"><label for="promotionSearchPage.promotionSearchForm.owner">Owner:</label></td>
												<td><div id="promotionSearchPage.promotionSearchForm.owner" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:7em;width:100%;'"></div></td>
												<td style="width:18px;text-align:right;"><img src="/rest-sample/images/mei/cleared.png" height="16" width="16"></td>
											</tr>
										</table>
									</fieldset>
								</td>
								<td>
									<fieldset style="border:1px inset #bbb;padding:0 5px;">
										<legend style="margin-left:10px;font-weight:bold;">Allowance Criteria</legend>
										<table style="width:100%">
											<tr>
												<td style="text-align:right;padding-right:5px;width:90px;height:22px;"><label for="promotionSearchPage.promotionSearchForm.product">Product:</label></td>
												<td><div id="promotionSearchPage.promotionSearchForm.product" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:7em;width:100%;'"></div></td>
												<td style="width:18px;text-align:right;"><img src="/rest-sample/images/mei/cleared.png" height="16" width="16"></td>
												<td style="text-align:right;padding-right:5px;width:120px;"><label for="promotionSearchPage.promotionSearchForm.performanceType">Performance Type:</label></td>
												<td><div id="promotionSearchPage.promotionSearchForm.performanceType" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:7em;width:100%;'"></div></td>
												<td style="width:18px;text-align:right;"><img src="/rest-sample/images/mei/cleared.png" height="16" width="16"></td>
												<td style="text-align:right;padding-right:5px;width:80px;"><label for="promotionSearchPage.promotionSearchForm.startDate">Start Date:</label></td>
												<td><div id="promotionSearchPage.promotionSearchForm.startDate" data-dojo-type="dijit.form.DateTextBox" data-dojo-props="style:'min-width:8em;max-width:13em;width:100%;'"></div></td>
											</tr>
											<tr>
												<td style="text-align:right;padding-right:5px;height:22px;"><label for="promotionSearchPage.promotionSearchForm.seller">From Account:</label></td>
												<td><div id="promotionSearchPage.promotionSearchForm.seller" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:7em;width:100%;'"></div></td>
												<td style="width:18px;text-align:right;"><img src="/rest-sample/images/mei/cleared.png" height="16" width="16"></td>
												<td style="text-align:right;padding-right:5px;"><label for="promotionSearchPage.promotionSearchForm.salesType">Sales Type:</label></td>
												<td colspan="2">
													<select id="promotionSearchPage.promotionSearchForm.salesType" data-dojo-type="dijit.form.Select" data-dojo-props="style:'min-width:8em;width:100%'">
														<option value="">&nbsp;</option>
														<option value="DIRECT">Direct</option>
														<option value="INDIRECT_IN">Indirect Receive</option>
														<option value="INDIRECT_OUT">Indirect Ship</option>
														<option value="SCAN">Scan</option>
													</select>
												</td>
												<td style="text-align:right;padding-right:5px;"><label for="promotionSearchPage.promotionSearchForm.endDate">End Date:</label></td>
												<td><div id="promotionSearchPage.promotionSearchForm.endDate" data-dojo-type="dijit.form.DateTextBox" data-dojo-props="style:'min-width:8em;max-width:13em;width:100%;'"></div></td>
											</tr>
											<tr>
												<td style="text-align:right;padding-right:5px;height:22px;"><label for="promotionSearchPage.promotionSearchForm.buyer">To Account:</label></td>
												<td><div id="promotionSearchPage.promotionSearchForm.buyer" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:7em;width:100%;'"></div></td>
												<td style="width:18px;text-align:right;"><img src="/rest-sample/images/mei/cleared.png" height="16" width="16"></td>
												<td style="text-align:right;padding-right:5px;"><label for="promotionSearchPage.promotionSearchForm.spendType">Spend Type:</label></td>
												<td colspan="2">
													<select id="promotionSearchPage.promotionSearchForm.spendType" data-dojo-type="dijit.form.Select" data-dojo-props="style:'min-width:8em;width:100%'">
														<option value="">&nbsp;</option>
														<option value="OI">O.I.</option>
														<option value="DEDUCTION">Deduction</option>
														<option value="CHECK">Check Request</option>
													</select>
												</td>
											</tr>
											<tr>
												<td style="text-align:right;padding-right:5px;height:22px;"><label for="promotionSearchPage.promotionSearchForm.fund">Fund:</label></td>
												<td><div id="promotionSearchPage.promotionSearchForm.fund" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:7em;width:100%;'"></div></td>
												<td style="width:18px;text-align:right;"><img src="/rest-sample/images/mei/cleared.png" height="16" width="16"></td>
												<td style="text-align:right;padding-right:5px;"><label for="promotionSearchPage.promotionSearchForm.rateType">Rate Type:</label></td>
												<td colspan="2">
													<select id="promotionSearchPage.promotionSearchForm.rateType" data-dojo-type="dijit.form.Select" data-dojo-props="style:'min-width:8em;width:100%'">
														<option value="">&nbsp;</option>
														<option value="LUMPSUM">Lump Sum ($)</option>
														<option value="PERUNIT">Per Qty ($)</option>
														<option value="PERCENT">Gross (%)</option>
													</select>
												</td>
											</tr>
										</table>
									</fieldset>
								</td>
								<td rowspan="4" style="width:60px;text-align:center;">
									<button id="promotionSearchPage.searchButton" data-dojo-type="dijit.form.Button" type="button">
										<img src="/rest-sample/images/mei/search.png" width="16" height="16" />
									</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region:'top',layoutPriority:2,style:'border:1px solid #99CDFF;padding:5px;'">
					Promotions List (0)
					<img src="/rest-sample/images/mei/edit_Disabled.png" width="16" height="16" title="Edit" style="float:right;" />
				</div>
				<table id="promotionSearchPage.promotionList" data-dojo-type="dojox.grid.DataGrid" data-dojo-props="region:'center',selectionMode:'single',style:'min-height:120px;'">
					<thead>
						<tr>
							<th field="name" width="10%">Promotion Name</th>
							<th field="status" width="6%">Status</th>
							<th field="negotiateWith.name" width="10%">Negotiate Account</th>
							<th field="startDate" formatter="mei.Common.formatDate" width="6em">Start Date</th>
							<th field="endDate" formatter="mei.Common.formatDate" width="6em">End Date</th>
							<th field="spendForecast" formatter="mei.Common.formatMoney" styles="text-align:right;" width="10%">Spend Fcst*</th>
							<th field="spendDue" formatter="mei.Common.formatMoney" width="10%">Spend Due*</th>
							<th field="spendAccount" formatter="mei.Common.formatMoney" width="10%">Spend Act*</th>
							<th field="insertWhen" formatter="mei.Common.formatDate" width="6em">Inserted Date Time</th>
							<th field="owner.fullName" width="10%">Owner</th>
							<th field="code" width="10%">Promotion Code</th>
						</tr>
					</thead>
				</table>
				<div data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region:'bottom',layoutPriority:2,splitter:true,style:'border:1px solid #99CDFF;padding:5px;'">
					Allowances List (0)
					<img src="/rest-sample/images/mei/edit_Disabled.png" width="16" height="16" title="Edit" style="float:right;" />
				</div>
				<table id="promotionSearchPage.allowanceList" data-dojo-type="dojox.grid.DataGrid" data-dojo-props="region:'bottom',layoutPriority:1,style:'min-height:120px;'">
					<thead>
						<tr>
							<th field="name" width="6%">Promotion Name</th>
							<th field="salesType" formatter="mei.Console.formatSalesType" width="6%">Sales Type</th>
							<th field="seller.name" width="6%">From Account</th>
							<th field="buyer.name" width="6%">To Account</th>
							<th field="product.name" width="10%">Product</th>
							<th field="startDate" formatter="mei.Common.formatDate" width="6%">Start Date</th>
							<th field="endDate" formatter="mei.Common.formatDate" width="6%">End Date</th>
							<th field="spendForecast" formatter="mei.Common.formatMoney" width="6%">Spend Fcst*</th>
							<th field="spendDue" formatter="mei.Common.formatMoney" width="6%">Spend Due*</th>
							<th field="spendAccount" formatter="mei.Common.formatMoney" width="6%">Spend Act*</th>
							<th field="fund.name" width="6%">Fund</th>
							<th field="spendType" formatter="mei.Console.formatSpendType" width="6%">Spend Type</th>
							<th field="rateType" formatter="mei.Console.formatRateType" width="6%">Rate Type</th>
							<th field="rate" width="6%">Rate</th>
							<th field="performanceType.name" width="6%">Performance Type</th>
						</tr>
					</thead>
				</table>
			</div>

			<!--
				Promotions > Promotion Details
			-->
			<div id="promotionDetailPage" data-dojo-type="dijit.layout.ContentPane" data-dojo-props="gutters:false,style:'background-color:#EFF3FC;padding:10px;overflow:auto;'">
				<div class="blueBox" style="margin-bottom: 10px;">
					<div class="titleZone" style="padding:5px;font-size:13px;font-weight:bold;border:1px solid #99CDFF;border-top-right-radius:5px;border-top-left-radius:5px;background:url(/rest-sample/images/mei/app_title_bar_stretch.png) repeat-x left -1px;">Promotion Attributes</div>
					<div class="dataZone" style="padding:10px;border-left:1px solid #99CDFF;border-right:1px solid #99CDFF;border-bottom:1px solid #99CDFF;border-bottom-right-radius:5px;border-bottom-left-radius:5px;">
						<div style="text-align:center;font-size:14px;">
							<span style="border-radius:15px;padding:3px 5px;border:2px solid lightblue;text-align:center;" title="Pending">P</span> ———
							<span style="border-radius:15px;padding:3px 5px;border:2px solid lightblue;text-align:center;" title="Submitted">S</span> ———
							<span style="border-radius:15px;padding:3px 5px;border:2px solid lightblue;text-align:center;" title="Approved">A</span> ———
							<span style="border-radius:15px;padding:3px 5px;border:2px solid lightblue;text-align:center;background-color:lightblue;" title="Running">R</span> ———
							<span style="border-radius:15px;padding:3px 5px;border:2px solid lightblue;text-align:center;" title="Completed">C</span> ———
							<span style="border-radius:15px;padding:3px 5px;border:2px solid lightblue;text-align:center;" title="Closed">C</span>
						</div>
						<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
							<tbody>
								<tr>
									<th style="vertical-align:middle;height:26px;color:grey;padding-right:10px;">Code</th>
									<td style="width:40%;"><span id="promotionDetails.code" data-dojo-type="dijit/InlineEditBox" style="margin-right:10px;">MEI-12345</span></td>
									<th style="vertical-align:middle;height:26px;color:grey;padding-right:10px;width:50%;">Comment</th>
								</tr>
								<tr>
									<th style="vertical-align:middle;height:26px;color:grey;padding-right:10px;">Name</th>
									<td><span id="promotionDetails.name" data-dojo-type="dijit/InlineEditBox" style="margin-right:10px;">My promotion name</span></td>
									<td rowspan="3" style="vertical-align:top;">
										<span id="promotionDetails.comment" data-dojo-type="dijit/InlineEditBox" style="margin-right:10px;" data-dojo-props="editor: dijit.form.Textarea, autoSave:true">
											<b>Lorem ipsum</b> dolor sit amet, consectetur adipiscing elit. Donec vitae lectus urna. Integer iaculis consequat orci id accumsan. Aenean tincidunt felis adipiscing odio tempor eget dapibus dolor egestas. Nullam facilisis orci sit amet massa sodales quis mattis ligula gravida. Nulla mattis risus nec sem imperdiet et vulputate ipsum interdum. Pellentesque faucibus iaculis leo ut lacinia. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;
										</span>
									</td>
								</tr>
								<tr>
									<th style="vertical-align:middle;height:26px;color:grey;padding-right:10px;">Status</th>
									<td><span id="promotionDetails.status" style="color:grey;">Pending</span></td>
								</tr>
								<tr>
									<th style="vertical-align:middle;height:26px;color:grey;padding-right:10px;">Negotiate With</th>
									<td><select id="promotionDetails.negotiateWith" data-dojo-type="dijit/form/Select"><option>Korger DC</option></select></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

				<div class="blueBox" style="margin-bottom: 10px;">
					<div class="titleZone" style="padding:5px;font-size:13px;font-weight:bold;border:1px solid #99CDFF;border-top-right-radius:5px;border-top-left-radius:5px;background:url(/rest-sample/images/mei/app_title_bar_stretch.png) repeat-x left -1px;">
						Allowances
						<img src="/rest-sample/images/delete_Disabled.png" style="float:right;margin-right:10px;" />
						<img src="/rest-sample/images/copy_Disabled.png" style="float:right;margin-right:10px;" />
						<img src="/rest-sample/images/insert.png" style="float:right;margin-right:10px;" />
						<img src="/rest-sample/images/edit_Disabled.png" style="float:right;margin-right:10px;" />
					</div>
					<table id="promotionDetailPage.allowanceList" data-dojo-type="dojox.grid.DataGrid" data-dojo-props="autoHeight:true" style="min-height:100px;border-left:1px solid #99CDFF;border-right:1px solid #99CDFF;border-bottom:1px solid #99CDFF;">
						<thead>
							<tr>
								<th field="salesType" formatter="mei.Console.formatSalesType" width="6%">Sales Type</th>
								<th field="seller.name" width="6%">From Account</th>
								<th field="buyer.name" width="6%">To Account</th>
								<th field="product.name" width="10%">Product</th>
								<th field="startDate" formatter="mei.Common.formatDate" width="6%">Start Date</th>
								<th field="endDate" formatter="mei.Common.formatDate" width="6%">End Date</th>
								<th field="spendForecast" formatter="mei.Common.formatMoney" width="6%">Spend Fcst*</th>
								<th field="spendDue" formatter="mei.Common.formatMoney" width="6%">Spend Due*</th>
								<th field="spendAccount" formatter="mei.Common.formatMoney" width="6%">Spend Act*</th>
								<th field="fund.name" width="6%">Fund</th>
								<th field="spendType" formatter="mei.Console.formatSpendType" width="6%">Spend Type</th>
								<th field="rateType" formatter="mei.Console.formatRateType" width="6%">Rate Type</th>
								<th field="rate" width="6%">Rate</th>
								<th field="performanceType.name" width="6%">Performance Type</th>
							</tr>
						</thead>
					</table>
				</div>

				<div class="blueBox" style="margin-bottom: 10px;">
					<div class="titleZone" style="padding:5px;font-size:13px;font-weight:bold;border:1px solid #99CDFF;border-top-right-radius:5px;border-top-left-radius:5px;background:url(/rest-sample/images/mei/app_title_bar_stretch.png) repeat-x left -1px;">
						Reconciliation
						<img src="/rest-sample/images/delete_Disabled.png" style="float:right;margin-right:10px;" />
						<img src="/rest-sample/images/copy_Disabled.png" style="float:right;margin-right:10px;" />
						<img src="/rest-sample/images/insert.png" style="float:right;margin-right:10px;" />
						<img src="/rest-sample/images/edit_Disabled.png" style="float:right;margin-right:10px;" />
					</div>
					<div class="dataZone" style="padding:10px;border-left:1px solid #99CDFF;border-right:1px solid #99CDFF;border-bottom:1px solid #99CDFF;border-bottom-right-radius:5px;border-bottom-left-radius:5px;">
						No reconciliation created at this time.
					</div>
				</div>

				<div class="blueBox" style="margin-bottom: 10px;">
					<div class="titleZone" style="padding:5px;font-size:13px;font-weight:bold;border:1px solid #99CDFF;border-top-right-radius:5px;border-top-left-radius:5px;background:url(/rest-sample/images/mei/app_title_bar_stretch.png) repeat-x left -1px;">
						Attachments
						<img src="/rest-sample/images/delete_Disabled.png" style="float:right;margin-right:10px;" />
						<img src="/rest-sample/images/copy_Disabled.png" style="float:right;margin-right:10px;" />
						<img src="/rest-sample/images/insert.png" title="Upload a new document" style="float:right;margin-right:10px;" />
						<img src="/rest-sample/images/edit_Disabled.png" style="float:right;margin-right:10px;" />
					</div>
					<table id="promotionDetailPage.attachmentList" data-dojo-type="dojox.grid.DataGrid" data-dojo-props="autoHeight:true" style="min-height:50px;border-left:1px solid #99CDFF;border-right:1px solid #99CDFF;border-bottom:1px solid #99CDFF;">
						<thead>
							<tr>
								<th field="filename">Filename</th>
								<th field="size" width="10%">Size (MB)</th>
								<th field="owner.name" width="6%">Inserted By</th>
								<th field="insertWhen" width="10%">Insert On</th>
							</tr>
						</thead>
					</table>
				</div>

				<div class="blueBox" style="margin-bottom: 10px;">
					<div class="titleZone" style="padding:5px;font-size:13px;font-weight:bold;border:1px solid #99CDFF;border-top-right-radius:5px;border-top-left-radius:5px;background:url(/rest-sample/images/mei/app_title_bar_stretch.png) repeat-x left -1px;">
						Approvals
						<img src="/rest-sample/images/edit_Disabled.png" style="float:right;margin-right:10px;" />
					</div>
					<div class="dataZone" style="padding:10px;border-left:1px solid #99CDFF;border-right:1px solid #99CDFF;border-bottom:1px solid #99CDFF;border-bottom-right-radius:5px;border-bottom-left-radius:5px;">
						Not part of an approval process yet.
					</div>
				</div>
			</div>

			<!-- Settlements > Invoice Search page -->
			<div id="invoiceSearchPage" data-dojo-type="dijit.layout.BorderContainer" data-dojo-props="gutters:false,style:'background-color:#EFF3FC;padding:10px;overflow:auto;'">
				<div data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region:'top',layoutPriority:1,splitter:true">
					<form data-dojo-type="dijit.form.Form" id="invoiceSearchPage.invoiceSearchForm">
						<table style="width:100%">
							<tr>
								<td style="text-align:right;padding-right:5px;width:140px;height:22px;"><label for="invoiceSearchPage.invoiceSearchForm.number">Invoice No / Adj No:</label></td>
								<td><div id="invoiceSearchPage.invoiceSearchForm.number" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:8em;width:100%;'"></div></td>
								<td style="text-align:right;padding-right:5px;width:140px;"><label for="invoiceSearchPage.invoiceSearchForm.reference">Reference To:</label></td>
								<td><div id="invoiceSearchPage.invoiceSearchForm.reference" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:8em;width:100%;'"></div></td>
								<td style="text-align:right;padding-right:5px;width:140px;"><label for="invoiceSearchPage.invoiceSearchForm.purchaseOrder">Purchage Order No:</label></td>
								<td><div id="invoiceSearchPage.invoiceSearchForm.purchaseOrder" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:8em;width:100%;'"></div></td>
								<td rowspan="4" style="width:60px;text-align:center;">
									<button id="invoiceSearchPage.searchButton" data-dojo-type="dijit.form.Button" type="button">
										<img src="/rest-sample/images/mei/search.png" width="16" height="16" />
									</button>
								</td>
							</tr>
							<tr>
								<td style="text-align:right;padding-right:5px;height:22px;"><label for="invoiceSearchPage.invoiceSearchForm.type">Type:</label></td>
								<td>
									<select id="invoiceSearchPage.invoiceSearchForm.type" data-dojo-type="dijit.form.Select" data-dojo-props="style:'width:8em;'">
										<option value="">&nbsp;</option>
										<option value="CREDIT">Credit</option>
										<option value="DEBIT">Debit</option>
									</select>
								</td>
								<td style="text-align:right;padding-right:5px;"><label for="invoiceSearchPage.invoiceSearchForm.billToCode">Bill To Code:</label></td>
								<td><div id="invoiceSearchPage.invoiceSearchForm.billToCode" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'width:100%;'"></div></td>
								<td style="text-align:right;padding-right:5px;"><label for="invoiceSearchPage.invoiceSearchForm.billToName">Bill-To Name:</label></td>
								<td><div id="invoiceSearchPage.invoiceSearchForm.billToName" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'width:100%;'"></div></td>
							</tr>
							<tr>
								<td style="text-align:right;padding-right:5px;height:22px;"><label for="invoiceSearchPage.invoiceSearchForm.shipToCode">Ship To Code:</label></td>
								<td><div id="invoiceSearchPage.invoiceSearchForm.shipToCode" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'width:100%;'"></div></td>
								<td style="text-align:right;padding-right:5px;"><label for="invoiceSearchPage.invoiceSearchForm.shipToName">Ship-To Name:</label></td>
								<td><div id="invoiceSearchPage.invoiceSearchForm.shipToName" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'width:100%;'"></div></td>
								<td style="text-align:right;padding-right:5px;"><label for="invoiceSearchPage.invoiceSearchForm.issueDate">Issue Date:</label></td>
								<td><div id="invoiceSearchPage.invoiceSearchForm.issueDate" data-dojo-type="dijit.form.DateTextBox" data-dojo-props="style:'min-width:8em;max-width:13em;width:100%;'"></div></td>
							</tr>
							<tr>
								<td style="text-align:right;padding-right:5px;height:22px;"><label for="invoiceSearchPage.invoiceSearchForm.allowanceDate">Allowance Date:</label></td>
								<td><div id="invoiceSearchPage.invoiceSearchForm.allowanceDate" data-dojo-type="dijit.form.DateTextBox" data-dojo-props="style:'min-width:8em;max-width:13em;width:100%;'"></div></td>
							</tr>
						</table>
					</form>
				</div>
				<div data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region:'top',layoutPriority:2,style:'border:1px solid #99CDFF;padding:5px;'">
					Invoices List (0)
					<img src="/rest-sample/images/mei/edit_Disabled.png" width="16" height="16" title="Edit" style="float:right;" />
				</div>
				<table id="invoiceSearchPage.invoiceList" data-dojo-type="dojox.grid.DataGrid" data-dojo-props="region:'center',style:'min-height:120px;'">
					<thead>
						<tr>
							<th field="invoiceNo" width="10%">Invoice No / Adj No</th>
							<th field="invoiceNo" width="10%">Reference To</th>
							<th field="invoiceNo" width="10%">Purchase Order No</th>
							<th field="invoiceNo" width="6%">Type</th>
							<th field="invoiceNo" width="20%">Bill-To</th>
							<th field="invoiceNo" width="20%">Ship-To</th>
							<th field="invoiceNo" width="12%">Issue Date</th>
							<th field="invoiceNo" width="12%">Allowance Date</th>
						</tr>
					</thead>
				</table>
			</div>

			<!--
				Administration > Manage Users
			-->
			<div id="userSearchPage" data-dojo-type="dijit.layout.BorderContainer" data-dojo-props="gutters:false,style:'background-color:#EFF3FC;padding:10px;overflow:auto;'">
				<div data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region:'top',layoutPriority:1,splitter:true">
					<form data-dojo-type="dijit.form.Form" id="userSearchPage.userSearchForm">
						<table style="width:100%">
							<tr>
								<td style="text-align:right;padding-right:5px;height:22px;">User Code:</td>
								<td><div id="userSearchPage.userSearchForm.userCode" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:8em;width:100%;'"></div></td>
								<td style="text-align:right;padding-right:5px;height:22px;">User Name:</td>
								<td><div id="userSearchPage.userSearchForm.userName" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:8em;width:100%;'"></div></td>
								<td style="text-align:right;padding-right:5px;height:22px;">Last Name:</td>
								<td><div id="userSearchPage.userSearchForm.lastName" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:8em;width:100%;'"></div></td>
								<td style="text-align:right;padding-right:5px;height:22px;">First Name:</td>
								<td><div id="userSearchPage.userSearchForm.firstName" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:8em;width:100%;'"></div></td>
								<td style="text-align:right;padding-right:5px;height:22px;">Email:</td>
								<td><div id="userSearchPage.userSearchForm.email" data-dojo-type="dijit.form.TextBox" data-dojo-props="style:'min-width:8em;width:100%;'"></div></td>
								<td style="width:60px;text-align:center;">
									<button id="userSearchPage.searchButton" data-dojo-type="dijit.form.Button" type="button">
										<img src="/rest-sample/images/mei/search.png" width="16" height="16" />
									</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region:'top',layoutPriority:2,style:'border:1px solid #99CDFF;padding:5px;'">
					<span id="userSearchPage.userListCount">Users List (0)</span>
					<img src="/rest-sample/images/mei/edit_Disabled.png" width="16" height="16" title="Edit" style="float:right;" />
				</div>
				<table id="userSearchPage.userList" data-dojo-type="dojox.grid.DataGrid" data-dojo-props="region:'center',style:'min-height:120px;'">
					<thead>
						<tr>
							<th field="lastName" width="20%">Last Name</th>
							<th field="firstName" width="20%">First Name</th>
							<th field="userName" width="20%">User Name</th>
							<th field="email" width="20%">Email</th>
							<th field="isAdmin" width="10%">Is Administrator</th>
							<th field="insertWhen" formatter="mei.Common.formatDate" width="10%">Inserted Date Time</th>
						</tr>
					</thead>
				</table>
			</div>

			<!--
				Administration > Manage Users > User Details
			-->
			<div id="userDetailPage" data-dojo-type="dijit.layout.TabContainer" style="width: 100%; height: 100%;" data-dojo-props="gutters:false,style:'background-color:#EFF3FC;padding:10px;overflow:auto;'">
				<div data-dojo-type="dijit.layout.ContentPane" title="Profile" data-dojo-props="style:'padding:10px;'">
					<form data-dojo-type="dijit.form.Form" id="userDetailPage.userFormDetail">
						<div class="formField"><label class="formLabel">Code:</label><input type="text" id="userDetailPage.userFormDetail.code" name="code" data-dojo-type="dijit.form.TextBox" /></div>
						<div class="formField"><label class="formLabel">First name:</label><input type="text" id="userDetailPage.userFormDetail.firstName" name="firstName" data-dojo-type="dijit.form.TextBox" /></div>
						<div class="formField"><label class="formLabel">Last name:</label><input type="text" id="userDetailPage.userFormDetail.lastName" name="lastName" data-dojo-type="dijit.form.TextBox" /></div>
						<div class="formField"><label class="formLabel">Username:</label><input type="text" id="userDetailPage.userFormDetail.userName" name="userName" data-dojo-type="dijit.form.TextBox" /></div>
						<div class="formField"><label class="formLabel">Email:</label><input type="text" id="userDetailPage.userFormDetail.email"  name="email"data-dojo-type="dijit.form.TextBox" /></div>
						<div class="formField"><label class="formLabel">Can login:</label><input type="checkbox" id="userDetailPage.userFormDetail.canLogin" name="canLogin" data-dojo-type="dijit.form.CheckBox" /></div>
						<div class="formField"><label class="formLabel">Is admin:</label><input type="checkbox" id="userDetailPage.userFormDetail.isAdmin" name="isAdmin" data-dojo-type="dijit.form.CheckBox" /></div>
						<div class="formField"><label class="formLabel">Is read-only:</label><input type="checkbox" id="userDetailPage.userFormDetail.isReadOnly" name="isReadOnly" data-dojo-type="dijit.form.CheckBox" /></div>
						<div class="formField"><label class="formLabel">Supervisor:</label><input type="text" id="userDetailPage.userFormDetail.supervisor.code" name="supervisor.code" data-dojo-type="dijit.form.TextBox" /></div>
						<div class="formField"><label class="formLabel">Insert user:</label><input type="text" id="userDetailPage.userFormDetail.insertUser.code" data-dojo-type="dijit.form.TextBox" readonly="readonly" /></div>
						<div class="formField"><label class="formLabel">Insert when:</label><input type="text" id="userDetailPage.userFormDetail.insertWhen" data-dojo-type="dijit.form.TextBox" readonly="readonly" /></div>
						<div class="formField"><label class="formLabel">Update user:</label><input type="text" id="userDetailPage.userFormDetail.updateUser.code" data-dojo-type="dijit.form.TextBox" readonly="readonly" /></div>
						<div class="formField"><label class="formLabel">Update when:</label><input type="text" id="userDetailPage.userFormDetail.updateWhen" data-dojo-type="dijit.form.TextBox" readonly="readonly" /></div>
						<div class="formField"><label class="formLabel">Version:</label><input type="text" id="userDetailPage.userFormDetail.version" data-dojo-type="dijit.form.TextBox" readonly="readonly" /></div>
					</form>
				</div>
				<div data-dojo-type="dijit.layout.ContentPane" title="Promotion Approvers" data-dojo-props="style:'padding:10px;'">
					<table id="userDetailPage.userApproverList" data-dojo-type="dojox.grid.DataGrid" data-dojo-props="">
						<thead>
							<tr>
								<th field="userApprover.code" formatter="mei.Console.getUserName" width="80%">Approver User Name</th>
								<th field="approvalThreshold" formatter="mei.Common.formatMoney" styles="text-align:right;" width="20%">Approval Threshold</th>
							</tr>
						</thead>
					</table>
				</div>
				<div data-dojo-type="dijit.layout.ContentPane" title="Assigned Groups">
				</div>
				<div data-dojo-type="dijit.layout.ContentPane" title="Assigned Accounts">
				</div>
			</div>

			<!--
				System Parameters page
			-->
			<div id="systemParametersPage" data-dojo-type="dijit.layout.ContentPane" data-dojo-props="gutters:false,style:'padding:10px;overflow:auto;'">
				<p><b>Administrator Email:</b> <input data-dojo-type="dijit.form.TextBox" data-dojo-props="" type="text" value="epolinsky@acme.com" /></p>
				<p><b>Manufacturer Name:</b> <input data-dojo-type="dijit.form.TextBox" data-dojo-props="" type="text" value="Acme Inc." /></p>
			</div>

		</div>

		<!--
			Status bar
		-->

		<div data-dojo-type="dijit.layout.ContentPane" data-dojo-props="region:'bottom'" style="padding:10px;background-image:url(/rest-sample/images/mei/app_footer_stretch.png);height:42px;padding:0;overflow:hidden;border:1px solid #99CDFF;border-bottom-left-radius:5px;border-bottom-right-radius:5px;">
			<button data-dojo-type="dijit.form.Button" data-dojo-props="style:'margin:10px;float:left;'">Close</button>
			<div id="gettingStartedReminderBox" style="display:none;margin:14px;float:left;vertical-align:middle;">
				<div data-dojo-type="dijit.form.CheckBox"></div>
				Don't show this information on startup.
			</div>
		</div>
	</div>

	<script
		type="text/javascript">
		window.onerror = function(message, file, lineNumber)
		{
			if (mei && mei.Common && mei.Common.showNotification)
				mei.Common.showNotification(message + '\n(file: ' + file + ' -- line: ' + lineNumber + ')');
			try
			{
				new Image().src = '/rest-sample/error?message=' + encodeURIComponent(message) + '&file=' + encodeURIComponent(file) + '&lineNumber=' + encodeURIComponent(lineNumber);
			}
			catch(ex)
			{}
		};
		require(
		[
			'dojo',
			'dojo/parser',
			'mei/Common',
			'mei/Console',
			'dojo/cookie',
			'dojo/fx',
			'dijit/Dialog',
			'dijit/form/Button',
			'dijit/form/CheckBox',
			'dijit/form/DateTextBox',
			'dijit/form/DropDownButton',
			'dijit/form/Form',
			'dijit/form/Select',
			'dijit/form/Textarea',
			'dijit/form/TextBox',
			'dijit/InlineEditBox',
			'dijit/layout/BorderContainer',
			'dijit/layout/ContentPane',
			'dijit/layout/StackContainer',
			'dijit/layout/StackController',
			'dijit/layout/TabContainer',
			'dijit/Menu',
			'dijit/MenuBar',
			'dijit/MenuItem',
			'dijit/MenuSeparator',
			'dijit/PopupMenuBarItem',
			'dijit/PopupMenuItem',
			'dojox/grid/DataGrid'
		],
		function(dojo, parser, common, app)
		{
			dojo.ready(function()
			{
				try
				{
					var tenant = dojo.cookie('tenant'),
					baseUrl = '/rest-sample/console/' + tenant;
					if (location.pathname.indexOf(baseUrl) != 0)
					{
						location = baseUrl;
						return;
					}

					parser.parse();

					common.init(
					{
						baseUrl: baseUrl,
						tenant: tenant
					});
					app.init();
				}
				catch(ex)
				{
					console.log(ex);
					common.showNotification(ex.toString(), 'error');
				}
			});
			}
		);
	</script>
</body></html>
