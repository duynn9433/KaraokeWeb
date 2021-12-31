<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    
    <style>
    .btn{
        margin-top: 50px;
        color: black;
        background-color: #34ce57; 
    }  
    
    .btn:hover{
        color: black;
        background-color: green; 
    }
    </style>

    <body>
        <div class = "container-fluid">
            <div class ="row col-12 text-center" style = "margin-top:180px;">
                <h1>Dang nhap</h1><br>
                <form class = "form-inline" action="<c:url value="/CheckLoginServlet" />" method ="post">
                    <div class ="input-group">
                    <input type="hidden" name="action" value="checkLogin">
                    <table>
                        <tr>
                            <td>Username:</td>
                            <td><input class = "form-control" type="text" name="username" value="receptionist" required></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input class = "form-control" type="password" name="password" value="receptionist" required></td>
                        </tr>
                    </table>
                    <input class = "btn" type="submit" value ='Dang nhap'>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
