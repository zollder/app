<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
	<body onload='document.f.j_username.focus();'>
		<h3>Spring Security Login Example</h3>

		<c:if test="${not empty error}">
			<div>
				Your login attempt was not successful, try again.
				Caused : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>
	  
		<form name='loginForm' action="j_spring_security_check" method='POST'>
			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='j_username' value=''>
					</td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='j_password' />
					</td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit" value="submit" />
					</td>
				</tr>
			</table>
		 </form>
	</body>
</html>