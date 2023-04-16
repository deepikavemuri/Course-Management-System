<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://bootswatch.com/4/cosmo/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="bootstrap.css" rel="stylesheet" media="screen">
 
</head>
<body>
<%

int userId = Integer.parseInt(session.getAttribute("loggedUserId").toString());
String FName = (String)session.getAttribute("loggedUserFname");
String email = (String)session.getAttribute("loggedUserEmail");
String password = (String)session.getAttribute("loggedUserPassword");
String table_name = (String)session.getAttribute("loggedUserType");
int rid = Integer.parseInt(request.getAttribute("rid").toString());
String rname = (String) request.getAttribute("rname");

%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">CMS</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation" style="">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarColor02">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="studentHome.jsp"><i class="fa fa-home" aria-hidden="true"></i>&nbsp;Home<span class="sr-only">(current)</span></a>
      </li>
    </ul> 
        
        <a class="nav-link" href="#"><%= FName %></a>
        <ul class="navbar-nav">
        	<a class="nav-link"  href="home_loggedOut.jsp" ><i class="fa fa-sign-out" aria-hidden="true" style="font-size: 1.5em"></i>SIGN OUT</a>
      </ul>
      
  </div>
</nav>

<h3 style="padding-left: 20px; padding-top:10px; padding-bottom:10px; background-color:#DCDCDC"><strong><%=rname %></strong></h3>
<br>

<div class="container"> 
<div class="row">
    <form action="courseDisplay" method="POST">
      <input type="hidden" name="userId" value=<%= userId %>>
      <input type="hidden" name="userType" value=<%= table_name %>>
      <button type="submit" class="btn btn-outline-secondary" style="width:200px; margin-left:200px">My Courses</button>
    </form>
    <form action="assignment" method="POST">
    	<input type="hidden" name="userId" value=<%= userId %>>
    	<input type="hidden" value=<%= table_name %> name="userType">
    	<input type="hidden" value=<%= rid %> name="rid">
    	<input type="hidden" value="<%=rname %>" name="rname">
    	<button type="submit" class="btn btn-outline-secondary" style="width:200px; margin-left:40px">Assignments</button>
    </form>
    <form action="courseSelect" method="POST">
    <input type="hidden" value=<%= rid %> name="rid">
     	<input type="hidden" value=<%= userId %> name="userId">
     	<input type="hidden" value=<%= FName %> name="FName">
     	<input type="hidden" value=<%= table_name %> name="userType">
     	<input type="hidden" value="<%=rname %>" name="rname">
    	<button type="submit" class="btn btn-outline-secondary" style="width:200px; margin-left:40px">Forum</button>
    </form>
</div>


<br>

<c:set var = "count" value = "0" scope = "page"/>
<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">SNo</th>
      <th scope="col">Assignment</th>
      <c:choose>
          <c:when test="${y == 1 }">
    			<th scope="col"></th>
    	  </c:when>
    	  <c:otherwise>
    			<th scope="col">Submit</th>
    	  </c:otherwise>
	  </c:choose>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${fileNames }" var="fileName">
  		<tr class="table-active">
      		<th scope="row">${count+1 }</th>
      		<td>
      		<form action="FileDisplay" method="POST">
      			<input type="hidden" value= ${fileName } name="fileName">		
      			<button type="submit" class="btn btn-link">${fileName }</button>
      		</form>
      		</td>
      		<c:choose>
    			<c:when test="${y == 1 }">
    				<form action="FileUpload" method="POST">
    					<input type="hidden" value=<%= rid %> name="rid">
    					<input type="hidden" name="fileId" value=${fileIds[count] }>
    					<input type="hidden" name="x" value=4>
    					<input type="hidden" value="<%=rname %>" name="rname">
    					<td><button type="submit" class="btn btn-primary btn-sm">VIEW SUBMISSIONS</button></td>
    				</form>
    			</c:when>
    			<c:otherwise>
    				<form action="FileUpload" method="POST" enctype="multipart/form-data">
    					<input type="hidden" value=<%= rid %> name="rid">
    					<input type="hidden" name="fileId" value=${fileIds[count] }>
    					<input type="hidden" name="x" value=3>
    					<input type="hidden" value="<%=rname %>" name="rname">
    					<td><input class="form-control-file" name="file" type="file"></td>
    					<td><button type="submit" class="btn btn-primary btn-sm">UPLOAD</button></td>
    				</form>
    			</c:otherwise>
			</c:choose>
    	</tr>
    	<c:set var = "count" value = "${count + 1}" scope ="page"></c:set>
  	</c:forEach>

</body>
</html>