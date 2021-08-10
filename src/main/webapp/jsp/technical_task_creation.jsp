<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.technical-task.creation"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/base.css">
    <link rel="stylesheet" href="/css/validation.css">
    <link rel="stylesheet" href="http://cdn.webix.com/edge/webix.css" type="text/css">
    <script src="http://cdn.webix.com/edge/webix.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="/js/validation.js"></script>
    <script type="text/javascript" src="/js/technical_task_creation.js"></script>
</head>
<body>
<div class="container-xxl">
    <div class="title h1 text-center mt-3"><fmt:message bundle="${loc}" key="local.technical-task.create-header"/></div>
    <div class="row justify-content-center mt-3">
        <div class="col-4">
            <form novalidate>
                <input type="hidden" class="command" name="command" value="create-project"/>
                <div class="form-group">
                    <label for="name" class="col-form-label"><fmt:message bundle="${loc}" key="local.technical-task.create-name"/></label>
                    <input type="text" class="form-control" name="name" id="name"/>
                    <small id="name-help" class="form-text">
                        <fmt:message bundle="${loc}" key="local.form.technical-task.name-help"/>
                    </small>
                    <div id="regex-name" class="hidden-regex">${regexTechnicalTaskName}</div>
                </div>
                <div class="form-group">
                    <label for="overview" class="col-form-label"><fmt:message bundle="${loc}" key="local.technical-task.create-overview"/></label>
                    <textarea class="form-control" name="description" id="overview"></textarea>
                    <small id="overview-help" class="form-text">
                        <fmt:message bundle="${loc}" key="local.form.technical-task.description-help"/>
                    </small>
                    <div id="regex-overview" class="hidden-regex">${regexTechnicalTaskOverview}</div>
                </div>
                <label for="date" class="col-form-label"><fmt:message bundle="${loc}" key="local.technical-task.create-deadline"/></label>
                <div id="date" class="justify-content-center mt-1"></div>
                <input type="text" class="form-control-hidden is-invalid" style="display: none;">
                <div id="deadlineHelp" class="invalid-feedback" style="display: none" >
                    <fmt:message bundle="${loc}" key="local.form.technical-task.deadline-help"/>
                </div>
                <div id="regex-deadline-help" class="hidden-regex">${regexTechnicalTaskDeadline}</div>
                <div class="d-flex justify-content-center">
                    <button onclick="submitCreationForm()" type="button" class="btn btn-primary mt-3"><fmt:message bundle="${loc}" key="local.technical-task.create-button"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
</body>
</html>

