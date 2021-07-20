<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <fmt:setLocale value="${local}" scope="session"/>
    <fmt:setBundle basename="local" var="loc"/>
    <title><fmt:message bundle="${loc}" key="local.error"/></title>
    <link rel="stylesheet" href="/css/error.css">
</head>
<body>
<div class="container-xxl">
    <div class="row h-100 justify-content-center">
        <div class="col-8 my-auto">
            <div class="row justify-content-center text-center">
                <div class="statuscode">${pageContext.errorData.statusCode}</div>
                <div class="mb-1 lead">
                    <h3>${pageContext.exception}</h3>
                </div>
                <p>
                <div class="mb-4">Servlet name: ${pageContext.errorData.servletName}</div>
                <div class="mb-4">Request from ${pageContext.errorData.requestURI} is failed</div>
                </p>

            </div>

        </div>
    </div>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
</body>
</html>
