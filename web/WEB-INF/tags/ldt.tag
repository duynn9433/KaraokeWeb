<%-- 
    Document   : Hiển thị thời gian LocalDateTime
    Created on : Nov 9, 2021, 2:53:34 PM
    Author     : xxxx9
--%>

<%@ tag body-content="empty" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="date" required="true" type="java.time.LocalDateTime" %>
<%@ attribute name="pattern" required="false" type="java.lang.String" %>

<c:if test="${empty pattern}">
    <c:set var="pattern" value="HH:mm:ss dd/MM/yyyy"/>
</c:if>

<fmt:parseDate value="${date}" pattern="yyyy-MM-dd'T'HH:mm" var="myParseDate"></fmt:parseDate> 
<fmt:formatDate value="${myParseDate}"  pattern="${pattern}"></fmt:formatDate >