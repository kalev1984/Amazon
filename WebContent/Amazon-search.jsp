<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>
			Welcome to the Amazon search page!
		</title>
		<link type="text/css" rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h2>Amazon Search Page</h2>
			</div>
		</div>
		<form action="UptimeAmazon" method="GET">
			Search for: <input type="text" name="amazonQuery" />
	
			<input type="submit" value="Submit" />
		<hr>
		<select name=selectPage>
  			<option value=1>Page 1</option>
  			<option value=2>Page 2</option>
 			<option value=3>Page 3</option>
  			<option value=4>Page 4</option>
		</select>
		</form>
		<hr>
		<div id="container">
		<div id="content">
	</div>
	</div>
	<table>
			<tr>
				<th>Title</th>
				<th>Price</th>
			</tr>
			<c:forEach var="response" items="${content_list }">
			<tr>
				<td> ${response.title } </td>
				<td> ${response.price } </td>
			</tr>
			</c:forEach>
		</table>
		<hr>
	</body>
</html>