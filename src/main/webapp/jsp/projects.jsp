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
    <link rel="stylesheet" href="/css/base.css">
</head>
<body>
<div class="container-xxl">
    <div class="title h1 text-center mt-3"><fmt:message bundle="${loc}" key="local.projects.header"/></div>
    <div class="row justify-content-center mt-3">
        <div class="col-md-4">
            <ul class="nav nav-tabs justify-content-center">
                <c:choose>
                    <c:when test="${state == 'IN_PROGRESS'}">
                        <li class="nav-item">
                            <a class="nav-link active"
                               href="?command=projects&state=IN_PROGRESS"><fmt:message
                                    bundle="${loc}" key="local.projects.active"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="?command=projects&state=FINISHED"><fmt:message
                                    bundle="${loc}" key="local.projects.finished"/></a>
                        </li>
                    </c:when>
                    <c:when test="${state == 'FINISHED'}">
                        <li class="nav-item">
                            <a class="nav-link"
                               href="?command=projects&state=IN_PROGRESS"><fmt:message
                                    bundle="${loc}" key="local.projects.active"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active"
                               href="?command=projects1&state=FINISHED"><fmt:message
                                    bundle="${loc}" key="local.projects.finished"/></a>
                        </li>
                    </c:when>
                </c:choose>
            </ul>
        </div>
    </div>
    <div class="d-flex justify-content-center mt-3">
        <form id="radio">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="unitPerPage" id="option1" value="4"
                       checked>
                <label class="form-check-label" for="option1">4</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="unitPerPage" id="option2" value="8">
                <label class="form-check-label" for="option2">8</label>
            </div>
        </form>
    </div>
    <div class="frame row justify-content-center">
        <div class="elements row mt-3">
            <c:forEach items="${projects}" var="project">
                <div class="element col-6">
                    <div class="card p-3 mb-2">
                        <div class="d-flex justify-content-between">
                            <div class="d-flex flex-row align-items-center">
                                <h2 class="mb-0">${project.name}</h2>
                            </div>
                            <c:choose>
                                <c:when test="${project.state == 'IN_PROGRESS'}">
                                    <div class="badge"><span class="green"><fmt:message bundle="${loc}"
                                                                                        key="local.projects.status-in-progress"/></span>
                                    </div>
                                </c:when>
                                <c:when test="${project.state == 'FINISHED'}">
                                    <div class="badge"><span class="blue"><fmt:message bundle="${loc}"
                                                                                       key="local.projects.status-finished"/></span>
                                    </div>
                                </c:when>
                                <c:when test="${project.state == 'STARTING'}">
                                    <div class="badge"><span class="yellow"><fmt:message bundle="${loc}"
                                                                                         key="local.projects.status-starting"/></span>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                        <hr>
                        <div class="mt-5">
                            <h6 class="heading">${project.description}</h6>
                            <div class="mt-5">
                                <div class="d-flex justify-content-between mr-3">
                                    <div class="d-flex flex-row align-items-center">
                                        <a href="?command=to-tasks&project-id=${project.id}">
                                            <button type="submit" class="btn btn-primary"><fmt:message bundle="${loc}"
                                                                                                       key="local.projects.open"/></button>
                                        </a>
                                    </div>
                                    <c:if test="${ role == 'MANAGER' }">
                                        <a href="?command=to-bill-creation&project-id=${project.id}">
                                            <button type="submit" class="btn btn-primary"><fmt:message bundle="${loc}"
                                                                                                       key="local.projects.add-bill"/></button>
                                        </a>
                                        <a href="?command=to-seek-employees&project-id=${project.id}">
                                            <button type="submit" class="btn btn-primary"><fmt:message bundle="${loc}"
                                                                                                       key="local.projects.add-employees"/></button>
                                        </a>
                                    </c:if>
                                    <c:if test="${ (role == 'MANAGER') || (role == 'CUSTOMER') }">
                                    <a href="?command=to-project-bills&project-id=${project.id}">
                                        <button type="submit" class="btn btn-primary"><fmt:message bundle="${loc}"
                                                                                                   key="local.projects.to-bills"/></button>
                                    </a>
                                    </c:if>
                                </div>
                                <div class="mt-3"><span class="text1"><fmt:message bundle="${loc}"
                                                                                   key="local.projects.start-date"/> <span
                                        class="text2">${project.startDate}</span></span></div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="pagging row" aria-label="...">
                <a class="first col" style="text-align: center"><fmt:message bundle="${loc}" key="local.pagination.first"/></a>
                <a class="prev col" style="text-align: center"><fmt:message bundle="${loc}"
                                                                            key="local.pagination.previous"/></a> <a id="num1"
                                                                                                                     class="num1 col"
                                                                                                                     style="text-align: center"> </a>
                <a id="num2" class="num2 col" style="color:gray; text-align: center"> </a> <a id="num3" class="num3 col"
                                                                                              style="text-align: center"> </a>
                <a class="next col" style="text-align: center"><fmt:message bundle="${loc}" key="local.pagination.next"/></a> <a
                    class="last col" style="text-align: center"><fmt:message bundle="${loc}" key="local.pagination.last"/></a>
        </div>
    </div>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="/js/pagging.js"></script>
</body>
</html>
