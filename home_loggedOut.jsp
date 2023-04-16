<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  
  <style>
  .sidenav {
    height: 100%;
    width: 0;
    position: fixed;
    z-index: 1;
    top: 0;
    right: 0;
    background-color: #FFFFFF;
    overflow-x: hidden;
    transition: 0.5s;
    padding-top: 60px;
}

.sidenav a {
    padding: 8px 8px 8px 32px;
    text-decoration: none;
    font-size: 20px;
    color: #818181;
    display: block;
    transition: 0.3s;
}

.sidenav a:hover {
    color: #f1f1f1;
}

.sidenav .closebtn {
    position: absolute;
    top: 0;
    right: 25px;
    font-size: 36px;
    margin-left: 50px;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}

  .centered {
    position: absolute;
    top: 200px;
    left: 225px;
   }

 .bg-img {
  /* The image used */
  background-image: url("9.jpg");
  
  /* Control the height of the image */
  min-height: 1000px;

  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}

input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 15px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
    font-size: 15px; 
}

/* Set a style for all buttons */
button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

button:hover {
    opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
    width: auto;
    padding: 10px 18px;
    background-color: #f44336;
}

/* Center the image and position the close button */

.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
    padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
    border: 1px solid #888;
    width: 450px;
    height: 550px;/* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
    position: absolute;
    right: 25px;
    top: 0;
    color: #000;
    font-size: 35px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: red;
    cursor: pointer;
}

/* Add Zoom Animation */
.animate {
    -webkit-animation: animatezoom 0.6s;
    animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
    from {-webkit-transform: scale(0)} 
    to {-webkit-transform: scale(1)}
}
    
@keyframes animatezoom {
    from {transform: scale(0)} 
    to {transform: scale(1)}
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
    span.psw {
       display: block;
       float: none;
    }
    .cancelbtn {
       width: 100%;
    }
}
  </style>
  
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">CMS</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation" style="">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarColor02">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="home_loggedOut.jsp"><i class="fa fa-home" aria-hidden="true"></i>&nbsp;Home<span class="sr-only">(current)</span></a>
      </li>
     
      </ul> 
      
        <a class="nav-link" href="#">About us</a>
        <a class="nav-link" href="#">Help</a>
     
     <button class="btn btn-outline-primary" onclick="openNav()" style="width:150px">Sign In/Sign Up</button>
    
    
  </div>
</nav>

<div id="id01" class="modal">
  
  <form class="modal-content animate" action="signIn" method="POST">
    
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>

    <div class="container">
    <br><br>
      <label for="email"><b>EMAIL</b></label>
      <input type="text" placeholder="Enter email" name="email1" required><br><br>

      <label for="pwd"><b>PASSWORD</b></label>
      <input type="password" placeholder="Enter Password" name="password1" required><br><br>
      <input type="hidden" name="userType" value="student">
      
      <button type="submit" style="font-size: 20px; class="btn btn-primary"">Log in</button><br><br>

    </div>

    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
      <span class="psw">Forgot <a href="#">password?</a></span>
    </div>
  </form>
</div>

<div id="id02" class="modal">
  
  <form class="modal-content animate" action="signIn" method="POST">
    
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>

    <div class="container">
    <br><br>
      <label for="email"><b>EMAIL</b></label>
      <input type="text" placeholder="Enter email" name="email1" required><br><br>

      <label for="pwd"><b>PASSWORD</b></label>
      <input type="password" placeholder="Enter Password" name="password1" required><br><br>
      <input type="hidden" name="userType" value="admin">
      
      <button type="submit" style="font-size: 20px; class=btn btn-primary">Log in</button><br><br>

    </div>

    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancel</button>
      <span class="psw">Forgot <a href="#">password?</a></span>
    </div>
  </form>
</div>


<div id="id03" class="modal">
  
  <form class="modal-content animate" action="signIn" method="POST">
    
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>

    <div class="container">
    <br><br>
      <label for="email"><b>EMAIL</b></label>
      <input type="text" placeholder="Enter email" name="email1" required><br><br>

      <label for="pwd"><b>PASSWORD</b></label>
      <input type="password" placeholder="Enter Password" name="password1" required><br><br>
      <input type="hidden" name="userType" value="instructor">
      
      <button type="submit" style="font-size: 20px; class="btn btn-primary"">Log in</button><br><br>

    </div>

    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id03').style.display='none'" class="cancelbtn">Cancel</button>
      <span class="psw">Forgot <a href="#">password?</a></span>
    </div>
  </form>
</div>

<script>
// Get the modal
var modal1 = document.getElementById('id01');
var modal2 = document.getElementById('id02');
var modal3 = document.getElementById('id03');
System.out.println("IM HEREEE");
if (modal1 != null) {  
	window.onclick = function(event) {
    	if (event.target == modal1) {
        	modal1.style.display = "none";
    	}
	}
}

else if (modal2 != null) {  	
	window.onclick = function(event) {
    	if (event.target == modal2) {
        	modal2.style.display = "none";
    	}
    }
}
	
else if (modal3 != null) {  
	window.onclick = function(event) {
		if (event.target == modal3) {
		modal3.style.display = "none";
		}
	}
}

</script>		
</div>
 
<div class="bg-img">
<div class="centered">
<center>
<font size = "8" color="white" style="font-family:Avantgarde, TeX Gyre Adventor, URW Gothic L, sans-serif;">Be in the classroom from anywhere anytime</font><br><br>
<button type="submit" class="btn btn-primary btn-lg" onclick="openNav()" style="width:200px">Sign In/Sign Up</button>
</center>
</div>
</div>

	
<div class="container">
<div class="row">
	<div class="col-sm-4 col-md-6 col-lg-6">
		<img src="anim1.jpg" height="500" width="500">	
	</div>


	<div class="col-sm-8 col-md-6 col-lg-6">    
	    <font size = "8" style="font-family:Avantgarde, TeX Gyre Adventor, URW Gothic L, sans-serif;">All your classroom material in one place</font><br><br>
	</div>
	
	</div>

<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="#" onclick="document.getElementById('id02').style.display='block'" style="width:auto; id=admin">Admin</a>
  <a href="#" onclick="document.getElementById('id01').style.display='block'" style="width:auto; id=student">Student</a>
  <a href="#" onclick="document.getElementById('id03').style.display='block'" style="width:auto; id=instructor">Instructor</a>
</div>

<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
</script>


</body>
</html>