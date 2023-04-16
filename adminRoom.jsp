<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://bootswatch.com/4/cosmo/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="bootstrap.css" rel="stylesheet" media="screen">
<title>Insert title here</title>
<style>
	body {
    	background-image: url("bg3.png");
    	padding-bottom: 20px;
	}
</style>
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
        <a class="nav-link" href="adminHome.jsp"><i class="fa fa-home" aria-hidden="true"></i>&nbsp;Home<span class="sr-only">(current)</span></a>
      </li>
    </ul> 
        
        <a class="nav-link" href="#"><%= FName %></a>
        <ul class="navbar-nav">
        	<a class="nav-link"  href="home_loggedOut.jsp" ><i class="fa fa-sign-out" aria-hidden="true" style="font-size: 1.5em"></i>SIGN OUT</a>
      </ul>
      
  </div>
</nav>

<br><br><br><br><br>

<div class="container"> 
      <div class="row">     
         <div class="col-sm-4 col-md-6 col-lg-6" >
         	<form action="addNew" method="POST">			
      			<input type="text" class="form-control" name="rname" required="required" placeholder="Enter room name"><br>
      			<input type="text" class="form-control" name="iid" required="required" placeholder="Instructor Id"><br>
      			<textarea class="form-control" name="rdesc" rows="3" required="required" placeholder="Enter room description"></textarea><br>
      			<input type="hidden" value=3 name="type">
				<button type="submit" class="btn btn-primary" style="width:150px">Add</button><br><br><br>
			</form>
         </div>
          
      	<div class="col-sm-8 col-md-6 col-lg-6">      
         		<form action="delete" method="POST">
         		<input type="text" class="form-control" name="userId" required="required" placeholder="Enter Id"><br>
				<input type="hidden" value=3 name="type">
				<button type="submit" class="btn btn-primary" style="width:150px">Delete</button><br><br><br>
			</form>
         </div>

</div>      

</body>
</html>