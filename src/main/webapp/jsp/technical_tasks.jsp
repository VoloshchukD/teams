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
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="/js/technical_task.js"></script>
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
    <div id="tt" class="row mt-3">
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

    <ctg:hello total="${allPagesNumber}" current="${currentPage}"/>

    <c:if test="${allPagesNumber != 1}">
        <div class="row justify-content-center mt-1">
            <div class="col-md-8">
                <ul class="pagination justify-content-center">
                    <c:if test="${currentPage != 1}">
                        <li class="page-item"><a class="page-link"
                                                 href="?command=projects&projectsPerPage=${projectsPerPage}&currentPage=1&state=${state}"><<</a>
                        </li>
                    </c:if>
                    <c:if test="${currentPage != 1}">
                        <li class="page-item"><a class="page-link"
                                                 href="?command=projects&projectsPerPage=${projectsPerPage}&currentPage=${currentPage-1}&state=${state}"><</a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${allPagesNumber}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="page-item active"><a class="page-link">
                                        ${i} <span class="sr-only">(current)</span></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${(currentPage eq i - 1) || (currentPage eq i + 1)}">
                                    <li class="page-item"><a class="page-link"
                                                             href="?command=projects&projectsPerPage=${projectsPerPage}&currentPage=${i}&state=${state}">${i}</a>
                                    </li>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage < allPagesNumber}">
                        <li class="page-item"><a class="page-link"
                                                 href="?command=projects&projectsPerPage=${projectsPerPage}&currentPage=${currentPage+1}&state=${state}">></a>
                        </li>
                    </c:if>
                    <c:if test="${currentPage != allPagesNumber}">
                        <li class="page-item"><a class="page-link"
                                                 href="?command=projects&projectsPerPage=${projectsPerPage}&currentPage=${allPagesNumber}&state=${state}">>></a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </c:if>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
</body>
</html>
