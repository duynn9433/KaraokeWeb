<%-- 
    Document   : EditDetailRoomView
    Created on : Nov 17, 2021, 2:05:51 PM
    Author     : Truong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit Detail Room</h1>
        <form action="" method="post">
            Name:<br><input type="text" name="name"><br>
            Size : <br><select name="selectsize">
                <option>Small</option>
            </select><br>
            Type: <br><select name="selecttype">
                <option>Narmall</option>
            </select><br>
            Price:<br><input type="text" name="price"><br>
            Description:<br><input type="text" name="des"><br>
            <input type="submit" name="save" value="save">
        </form>
    </body>
</html>
