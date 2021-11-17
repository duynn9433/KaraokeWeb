<%-- 
    Document   : EditInforRoomView
    Created on : Nov 17, 2021, 1:57:24 PM
    Author     : Truong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table, th, td {
                border:1px solid black;
            }
    </style>
    </head>
    <body>
        <h1>Edit Infor Room</h1>
        <form action="" method="POST">
            <input type="text" name="roomname" value="101">
            <input type="submit" name="searchroom" value="Search">
        </form><br>
        <table id="tblresult">
            <tr>
                <th>STT</th>
                <th>Name</th>
                <th>Size</th>
                <th>Type</th>
                <th>Price</th>
                <th>Des</th>
                <th></th>
            </tr>
            <tr>
                <td>1</td>
                <td>101</td>
                <td>Small</td>
                <td>Normal</td>
                <td>300000</td>
                <td></td>
                <td><button name="edit" onclick="">Edit</button></td>
            </tr>
        </table>
    </body>
</html>
