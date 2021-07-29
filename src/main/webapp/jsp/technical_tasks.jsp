<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.technical-tasks"/></title>
    <%@ taglib prefix="ctg" uri="custom-tags" %>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/card.css">
    <link rel="stylesheet" href="/css/technical_tasks.css">
</head>
<body>
<div class="container-xxl">
    <div class="title h1 text-center mt-3"><fmt:message bundle="${loc}" key="local.technical-tasks.header"/></div>
    <%--    <div class="row mt-3">--%>
    <%--        <div class="col-4 justify-content-center">--%>
    <%--                <button id="create-task" type="button" class="btn btn-primary" onclick="hideLayout()" >Create</button>--%>
    <%--                <button id="view-tasks" type="button" class="btn btn-primary" onclick="showLayout()" style="display: none">Technical Tasks</button>--%>
    <%--        </div>--%>
    <%--    </div>--%>
    <div class="pagging-frame row justify-content-center">
        <div class="row mt-3">
            <c:forEach items="${technicalTasks}" var="task">
                <div class="col-6">
                    <div class="card p-3 mb-2">
                        <div class="d-flex justify-content-between">
                            <div class="d-flex flex-row align-items-center">
                                <h2 class="mb-0">${task.name}</h2>
                            </div>
                            <c:choose>
                                <c:when test="${task.status == 'WAIT_PROJECT'}">
                                    <div class="badge"><span class="wait-project"><fmt:message bundle="${loc}"
                                                                                               key="local.technical-tasks.wait-project"/></span>
                                    </div>
                                </c:when>
                                <c:when test="${task.status == 'ON_PROJECT'}">
                                    <div class="badge"><span class="on-project"><fmt:message bundle="${loc}"
                                                                                             key="local.technical-tasks.on-project"/></span>
                                    </div>
                                </c:when>
                                <c:when test="${task.status == 'COMPLITED'}">
                                    <div class="badge"><span class="complited"><fmt:message bundle="${loc}"
                                                                                            key="local.technical-tasks.complited"/></span>
                                    </div>
                                </c:when>
                                <c:when test="${task.status == 'EDITING'}">
                                    <div class="badge"><span class="editing"><fmt:message bundle="${loc}"
                                                                                          key="local.technical-tasks.editing"/></span>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                        <hr>
                        <div class="mt-5">
                            <h6 class="heading">${task.overview}</h6>
                            <div class="mt-5">
                                <div class="ss">
                                    <button type="submit" class="btn btn-primary"><fmt:message bundle="${loc}"
                                                                                               key="local.technical-tasks.details"/></button>
                                </div>
                                <div class="mt-3"><span class="text1"><fmt:message bundle="${loc}"
                                                                                   key="local.technical-tasks.deadline"/> <span
                                        class="text2">${task.deadline}</span></span></div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            </div>
        <a class="first col" style="text-align: center"><fmt:message bundle="${loc}" key="local.pagination.first"/></a> <a class="prev col" style="text-align: center"><fmt:message bundle="${loc}" key="local.pagination.previous"/></a> <a id="num1" class="num1 col" style="text-align: center"> </a> <a id="num2" class="num2 col" style="color:gray; text-align: center"> </a> <a id="num3" class="num3 col" style="text-align: center"> </a> <a class="next col" style="text-align: center"><fmt:message bundle="${loc}" key="local.pagination.next"/></a> <a class="last col" style="text-align: center"><fmt:message bundle="${loc}" key="local.pagination.last"/></a>
    </div>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="/js/pagging.js"></script>
</body>
</html>
