<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.projects"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/projects.css">
</head>
<body>
<div class="container-xxl">
    <div class="title h1 text-center mt-3"><fmt:message bundle="${loc}" key="local.projects.header"/></div>
    <div class="row justify-content-center mt-3">
        <div class="col-md-4">
            <ul class="nav nav-tabs justify-content-center">
                <c:choose>
                    <c:when test="${state == 'in progress'}">
                        <li class="nav-item">
                            <a class="nav-link active"
                               href="?command=projects&projectsPerPage=4&currentPage=1&state=in%20progress"><fmt:message bundle="${loc}" key="local.projects.active"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="?command=projects&projectsPerPage=4&currentPage=1&state=finished"><fmt:message bundle="${loc}" key="local.projects.finished"/></a>
                        </li>
                    </c:when>
                    <c:when test="${state == 'finished'}">
                        <li class="nav-item">
                            <a class="nav-link"
                               href="?command=projects&projectsPerPage=4&currentPage=1&state=in%20progress"><fmt:message bundle="${loc}" key="local.projects.active"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active"
                               href="?command=projects&projectsPerPage=4&currentPage=1&state=finished"><fmt:message bundle="${loc}" key="local.projects.finished"/></a>
                        </li>
                    </c:when>
                </c:choose>
            </ul>
        </div>
    </div>
    <div class="row mt-3">
    <c:forEach items="${projects}" var="project">
            <div class="col-6">
                <div class="card p-3 mb-2">
                    <div class="d-flex justify-content-between">
                        <div class="d-flex flex-row align-items-center">
                            <h2 class="mb-0">${project.name}</h2>
                        </div>
                        <c:choose>
                            <c:when test="${project.state == 'in progress'}">
                                <div class="badge"><span class="inprogress"><fmt:message bundle="${loc}" key="local.projects.status-in-progress"/></span></div>
                            </c:when>
                            <c:when test="${project.state == 'finished'}">
                                <div class="badge"><span class="finished"><fmt:message bundle="${loc}" key="local.projects.status-finished"/></span></div>
                            </c:when>
                            <c:when test="${project.state == 'starting'}">
                                <div class="badge"><span class="starting"><fmt:message bundle="${loc}" key="local.projects.status-starting"/></span></div>
                            </c:when>
                        </c:choose>
                    </div>
                    <hr>
                    <div class="mt-5">
                        <h6 class="heading">${project.description}</h6>
                        <div class="mt-5">
                            <div class="ss">
                                <button type="submit" class="btn btn-primary"><fmt:message bundle="${loc}" key="local.projects.open"/></button>
                            </div>
                            <div class="mt-3"><span class="text1"><fmt:message bundle="${loc}" key="local.projects.start-date"/> <span
                                    class="text2">${project.startDate}</span></span></div>
                        </div>
                    </div>
                </div>
            </div>
    </c:forEach>
    </div>

    <c:if test="${allPagesNumber != 1}">
        <div class="row justify-content-center mt-1">
            <div class="col-md-8">
                <ul class="pagination justify-content-center">
                    <c:if test="${currentPage != 1}">
                        <li class="page-item"><a class="page-link"
                                                 href="?command=projects&projectsPerPage=${projectsPerPage}&currentPage=1&state=${state}">First</a>
                        </li>
                    </c:if>
                    <c:if test="${currentPage != 1}">
                        <li class="page-item"><a class="page-link"
                                                 href="?command=projects&projectsPerPage=${projectsPerPage}&currentPage=${currentPage-1}&state=${state}">Previous</a>
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
                                                 href="?command=projects&projectsPerPage=${projectsPerPage}&currentPage=${currentPage+1}&state=${state}">Next</a>
                        </li>
                    </c:if>
                    <c:if test="${currentPage != allPagesNumber}">
                        <li class="page-item"><a class="page-link"
                                                 href="?command=projects&projectsPerPage=${projectsPerPage}&currentPage=${allPagesNumber}&state=${state}">Last</a>
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
