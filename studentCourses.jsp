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

<br><br>

<div class="container"> 
<div class="row">
  <div class="col-md-3">
    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
    <form action="courseDisplay" method="POST">
      <input type="hidden" name="userId" value=<%= userId %>>
      <input type="hidden" name="userType" value=<%= table_name %>>
      <button type="submit" class="btn btn-outline-secondary" style="width:200px; margin-left:40px">My Courses</button><br>
    </form>
    <form action="settings.jsp" method="POST">
    	<button type="submit" class="btn btn-outline-secondary" style="width:200px; margin-left:40px">Settings</button><br>
    </form>
    </div>
  </div>

<c:forEach items="${rooms }" var="room">
     <div class="col-md-3">
     	<form action="courseSelect" method="POST">
     	<input type="hidden" value=${room.rid } name="rid">
     	<input type="hidden" value=<%= userId %> name="userId">
     	<input type="hidden" value=<%= FName %> name="FName">
     	<input type="hidden" value=<%= table_name %> name="userType">
     	<input type="hidden" value="${room.rname }" name="rname">
   		<button type="submit" class="btn btn-primary btn-lg" style="width:250px; margin-right:40px">${room.rname }</button><br><br>
   		</form>
   		</div>
   	</c:forEach>
</div>
    </div></form>

<form action="enroll" method="POST">
	<div class="input-group mb-3" style="width:225px; margin-left:1100px; margin-top:475px">
  		<input type="text" class="form-control" placeholder="Room Id" name="rid">
  		<input type="hidden" value=<%= userId %> name="userId">
  		<div class="input-group-append">
    		<button class="btn btn-outline-secondary" type="submit"><i class="fa fa-plus-circle" aria-hidden="true"></i>
    		Join</button>
  		</div>
	</div>
</form>


</body>
</html>