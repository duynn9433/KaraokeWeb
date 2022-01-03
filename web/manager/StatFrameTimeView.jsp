<%-- 
    Document   : StatFrameTimeView
    Created on : Nov 17, 2021, 2:15:42 PM
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
        <h1>Time Frame Stat</h1>
        <form action="" method="POST">
            
            Time Start: <input type="text" name="timestart">
            Time  end :<input tpye="text" name="timeend"><br>
            <input type="submit" name="stat" value="Stat">
        </form><br>
        <table id="tblresult">
            <tr>
                <th>STT</th>
                <th>Frame</th>
                <th>Client Count</th>
                <th>Income</th>
                <th></th>
            </tr>
            <tr>
                <td>1</td>
                <td>01:00</td>
                <td>10</td>
                <td>3000000</td>
                <td><button name="detail" onclick="">Detail</button></td>
            </tr>
            <tr>
                <td>1</td>
                <td>23:00</td>
                <td>8</td>
                <td>2900000</td>
                <td><button name="detail" onclick="">Detail</button></td>
            </tr>
        </table>
    </body>
</html>
