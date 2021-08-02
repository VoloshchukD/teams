<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.project.creation"/></title>
    <%@ taglib prefix="ctg" uri="custom-tags" %>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/base.css">
</head>
<body>
<div class="container-xxl">
    <div class="title h1 text-center mt-3"><fmt:message bundle="${loc}" key="local.project.create-header"/></div>
    <div class="row justify-content-center">
        <div class="col-4" >
            <jsp:useBean id="technicalTask" class="by.voloshchuk.entity.TechnicalTask" scope="request" />
            <form action="controller" >
                <input type="hidden" class="command" name="command" value="create-project"/>
                <input type="hidden" class="technical-task-id" name="technical-task-id" value="<jsp:getProperty name="technicalTask" property="id" />"/>
                <div class="form-group">
                    <label for="name" class="col-form-label"><fmt:message bundle="${loc}" key="local.project.create-name"/></label>
                    <input type="text" class="form-control" name="name" value="<jsp:getProperty name="technicalTask" property="name" />" id="name">
                </div>
                <div class="form-group">
                    <label for="description" class="col-form-label"><fmt:message bundle="${loc}" key="local.project.create-description"/></label>
                    <textarea class="form-control" name="description" id="description"><jsp:getProperty name="technicalTask" property="overview" />
                    </textarea>
                </div>
                <button type="submit" class="btn btn-primary mt-3" id="create"><fmt:message bundle="${loc}" key="local.project.create-button"/></button>
            </form>
        </div>

    </div>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="http://cdn.webix.com/edge/webix.js" type="text/javascript"></script>
</body>
</html>
