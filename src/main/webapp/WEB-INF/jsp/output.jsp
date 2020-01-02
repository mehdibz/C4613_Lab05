<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<HTML>
<HEAD>
<TITLE>Lab05 Select Query Results</TITLE>
<LINK REL=STYLESHEET HREF="mainstyle.css" TYPE="text/css">
</HEAD>

<BODY>

	<div id="mainDoc">
		<h2>
			<a href="user.jsp">Query Again</a>
		</h2>
		<br>

		<TABLE>
			<c:forEach items="${columns}" var="col">
				<!-- columns is a vector. toString method of vector returns comma separated items -->
				<c:forEach var="colName" items="${col}">
					<!--  So you can use forEach or forTokens jstl loop to loop through the comma delimited strings -->
					<th><c:out value="${colName}" /></th>
				</c:forEach>
			</c:forEach>

			<c:forEach items="${results}" var="res">
				<tr>
					<c:forEach items="${res}" var="row">
						<td><c:out value="${row}" /></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</TABLE>
	</div>
</BODY>
</HTML>