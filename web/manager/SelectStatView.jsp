<%-- 
    Document   : IncomeStatView
    Created on : Nov 5, 2021, 8:44:39 AM
    Author     : duynn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
     <%
            String msg = (String) request.getSession().getAttribute("msg");
            System.out.println("View" + msg);
            //msg="luu that bai";
            if (msg == null) {

            } else {
        %>
        <script type="text/javascript">
            var msg = "${msg}";
            alert(msg);
        </script>
        <%
                request.getSession().removeAttribute("msg");
            }
        %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Income Statistic View</title>
    </head>
    <body>
        <h1>Thong tin thong ke</h1>
        <form action="<c:url value="/SelectStatServlet"/>" method="post">
            <table>
                <tr></tr>
                <tr>
                    <td>Doi tuong thong ke: </td>
                    <td>
                        <select name="object" id="obj">
                            <option value="doanhThu">Doanh Thu</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                        </select>
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td>Loai thong ke: </td>
                    <td>
                        <select name="type" id="obj">
                            <option value="thoiGian">Thoi gian</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                        </select>
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td>Che do thong ke: </td>
                    <td>
                        <select name="mode" id="obj">
                            <option value="thang">Thang</option>
                            <option value="quy">Quy</option>
                            <option value="nam">Nam</option>
                        </select>
                    </td>
                </tr>
                <tr></tr>
            </table>
            <input type="hidden" name="action" value="thongKe">
            <input type="submit" value="Thong ke">
        </form>
    </body>
</html>
