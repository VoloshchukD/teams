<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.project.creation"/></title>
    <%@ taglib prefix="ctg" uri="custom-tags" %>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
          crossorigin="anonymous">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
          crossorigin="anonymous">
    <link rel="stylesheet" href="/css/base.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/validation.js"></script>
    <script type="text/javascript" src="/js/project_creation.js"></script>
    <link rel="stylesheet" href="/css/validation.css">
</head>
<body>
<div class="container-xxl">
    <div class="title h1 text-center mt-3">
        <fmt:message bundle="${loc}" key="local.project.create-header"/>
    </div>
    <c:if test="${error}">
        <div class="row justify-content-center">
            <div class="col-6">
                <div class="alert alert-danger alert-dismissible" role="alert" id="error">
                    <strong>
                        <fmt:message bundle="${loc}" key="local.message.error.header"/>
                    </strong>
                    <fmt:message bundle="${loc}" key="local.message.project.error"/>
                    <button type="button" class="close" data-dismiss="alert">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
        </div>
    </c:if>
    <div class="row justify-content-center">
        <div class="col-4">
            <jsp:useBean id="technicalTask" class="by.voloshchuk.entity.TechnicalTask" scope="request"/>
            <form action="controller" onSubmit="return validateProjectForm()" novalidate>
                <input type="hidden" class="command" name="command" value="create-project"/>
                <input type="hidden" class="technical-task-id" name="technical-task-id"
                       value="${technicalTask.id}"/>
                <input type="hidden" name="customer-id"
                       value="${technicalTask.customerId}"/>
                <div class="form-group">
                    <label for="name" class="col-form-label">
                        <fmt:message bundle="${loc}" key="local.project.create-name"/>
                    </label>
                    <input type="text" class="form-control" name="name"
                           value="<c:out value="${technicalTask.name}"/>" id="name">
                    <small id="name-help" class="form-text">
                        <fmt:message bundle="${loc}" key="local.form.project.name-help"/>
                    </small>
                    <div id="regex-name" class="hidden-regex">${regexProjectName}</div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-form-label">
                        <fmt:message bundle="${loc}" key="local.project.create-description"/>
                    </label>
                    <textarea class="form-control"
                              name="description"
                              id="description"><c:out value="${technicalTask.overview}"/></textarea>
                    <small id="description-help" class="form-text">
                        <fmt:message bundle="${loc}" key="local.form.project.description-help"/>
                    </small>
                    <div id="regex-description" class="hidden-regex">${regexProjectDescription}</div>
                </div>
                <button type="submit" class="btn btn-primary mt-3" id="create">
                    <fmt:message bundle="${loc}" key="local.project.create-button"/>
                </button>
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
