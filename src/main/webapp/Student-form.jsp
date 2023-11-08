<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>HostelManagement Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> HostelManagement Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Student</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${student != null}">
					<form action="update" method="post"></form>
				</c:if>
				<c:if test="${student == null}">
					<form action="insert" method="post"></form>
				</c:if>
                <table>
				<caption>
					
						<c:if test="${student != null}">
            			Edit User
            		</c:if>
						<c:if test="${student == null}">
            			Add New User
            		</c:if>
					
				</caption>
                 </table>
				<c:if test="${student != null}">
					<input type="hidden" name="id" value="<c:out value='${student.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Student Name</label> <input type="text"
						value="<c:out value='${student.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Student Age</label> <input type="number"
						value="<c:out value='${student.age}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>Student Department</label> <input type="text"
						value="<c:out value='${student.dep}' />" class="form-control"
						name="country">
				</fieldset>
				<fieldset class="form-group">
					<label>Student RoomNo</label> <input type="number"
						value="<c:out value='${student.roomno}' />" class="form-control"
						name="email">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
			</div>
		</div>
	</div>
</body>
</html>