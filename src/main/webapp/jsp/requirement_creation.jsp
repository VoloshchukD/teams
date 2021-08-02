<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.requirement.creation"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/base.css">
</head>
<body>
<div class="container-xxl">
    <div class="title h1 text-center mt-3"><fmt:message bundle="${loc}" key="local.requirement.create-header"/></div>
    <div class="row justify-content-center">
        <div class="col-6">
            <select class="form-select m-2" aria-label="Default select example" id="task">
                <option value="null" selected><fmt:message bundle="${loc}" key="local.requirement.create-select"/></option>
                <c:forEach items="${technicalTasks}" var="task">
                    <option value="${task.id}">${task.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="row justify-content-center">
            <div class="col-12">

                <table class="table my-2">
                    <thead>
                    <tr>
                        <th class="align" scope="col"><fmt:message bundle="${loc}" key="local.requirement.create-experience"/></th>
                        <th class="align" scope="col"><fmt:message bundle="${loc}" key="local.requirement.create-salary"/></th>
                        <th scope="col"><fmt:message bundle="${loc}" key="local.requirement.create-qualification"/></th>
                        <th scope="col"><fmt:message bundle="${loc}" key="local.requirement.create-primary"/></th>
                        <th scope="col"><fmt:message bundle="${loc}" key="local.requirement.create-comment"/></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr></tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="d-flex justify-content-center">
            <button type="submit" class="add btn btn-secondary mt-3" id="add"><fmt:message bundle="${loc}" key="local.requirement.create-button"/></button>
        </div>
    </div>
</div>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
<script src="http://cdn.webix.com/edge/webix.js" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/js/requirement_creation.js"></script>
</body>
</html>
