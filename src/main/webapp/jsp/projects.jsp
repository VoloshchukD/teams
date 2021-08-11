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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://use.fontawesome.com/6d201ab77c.js"></script>
    <script src="http://cdn.webix.com/edge/webix.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container-xxl">
    <div class="title h1 text-center mt-3"><fmt:message bundle="${loc}" key="local.projects.header"/></div>
    <c:if test="${ not empty recentlyCreatedProjectName}">
        <div class="row justify-content-center">
            <div class="col-6">
                <div class="alert alert-success alert-dismissible" role="alert" id="success">
                    <strong><fmt:message bundle="${loc}" key="local.message.success.header"/></strong>
                    <fmt:message bundle="${loc}" key="local.message.project.success"/>
                    <fmt:message bundle="${loc}"
                                 key="local.message.project.created-name"/> <c:out
                        value="${recentlyCreatedProjectName}"/>
                    <button type="button" class="close" data-dismiss="alert">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
        </div>
    </c:if>
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
                                <h2 class="mb-0 name"><c:out value="${project.name}"/></h2>
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
                            </c:choose>
                        </div>
                        <hr>
                        <div class="mt-5">
                            <h6 class="heading description"><c:out value="${project.description}"/></h6>
                            <div class="mt-5">
                                <div class="d-flex justify-content-between mr-3">
                                    <c:if test="${ project.state != 'FINISHED' }">
                                        <div class="d-flex flex-row align-items-center">
                                            <a href="?command=to-tasks&project-id=${project.id}">
                                                <button type="submit" class="btn btn-primary"><fmt:message
                                                        bundle="${loc}"
                                                        key="local.projects.open"/></button>
                                            </a>
                                        </div>
                                        <c:if test="${ role == 'MANAGER' }">
                                            <a href="?command=to-bill-creation&project-id=${project.id}">
                                                <button type="submit" class="btn btn-primary"><fmt:message
                                                        bundle="${loc}"
                                                        key="local.projects.add-bill"/></button>
                                            </a>
                                            <a href="?command=to-seek-employees&project-id=${project.id}">
                                                <button type="submit" class="btn btn-primary"><fmt:message
                                                        bundle="${loc}"
                                                        key="local.projects.add-employees"/></button>
                                            </a>
                                        </c:if>
                                        <c:if test="${ (role == 'MANAGER') || (role == 'CUSTOMER') }">
                                            <a href="?command=to-project-bills&project-id=${project.id}">
                                                <button type="submit" class="btn btn-primary"><fmt:message
                                                        bundle="${loc}"
                                                        key="local.projects.to-bills"/></button>
                                            </a>
                                        </c:if>
                                    </c:if>
                                </div>
                                <div class="mt-3 d-flex justify-content-between mr-3">
                                    <div class="d-flex flex-row align-items-center">
                                        <span class="text1">
                                            <fmt:message bundle="${loc}" key="local.projects.start-date"/>
                                             <span class="text2"><c:out value="${project.startDate}"/></span></span>
                                    </div>
                                    <c:if test="${ role == 'MANAGER' }">
                                        <c:if test="${ project.state == 'IN_PROGRESS'}">
                                            <input type="hidden" class="identifier" name="project-id"
                                                   value="${project.id}"/>
                                            <button type="button" class="edit btn btn-secondary"
                                                    data-toggle="modal" data-target="#modal">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                            </button>
                                        </c:if>
                                        <c:if test="${ project.state == 'FINISHED' }">
                                            <form action="controller" method="post">
                                                <input type="hidden" name="command" value="delete-project"/>
                                                <input type="hidden" name="project-id" value="${project.id}"/>
                                                <button type="submit" class="btn btn-danger">
                                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                                </button>
                                            </form>
                                        </c:if>
                                    </c:if>
                                </div>

                                <div class="modal fade" id="modal" tabindex="-1" role="dialog"
                                     aria-labelledby="exampleModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel"><fmt:message
                                                        bundle="${loc}"
                                                        key="local.projects.update-header"/></h5>
                                            </div>
                                            <form action="controller" method="post">
                                                <input type="hidden" name="command"
                                                       value="update-project"/>
                                                <input type="hidden" name="project-id" id="updateProjectId"/>
                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <label for="name" class="col-form-label"><fmt:message
                                                                bundle="${loc}"
                                                                key="local.projects.update-name"/></label>
                                                        <input type="text" class="form-control" name="name" id="name">
                                                        <label for="description" class="col-form-label"><fmt:message
                                                                bundle="${loc}"
                                                                key="local.projects.update-description"/></label>
                                                        <small id="name-help" class="form-text">
                                                            <fmt:message bundle="${loc}"
                                                                         key="local.form.project.name-help"/>
                                                        </small>
                                                    </div>
                                                    <div class="form-group">
                                                        <div id="regex-name"
                                                             class="hidden-regex">${regexProjectName}</div>
                                                        <textarea class="form-control" name="description"
                                                                  id="description"></textarea>
                                                        <small id="description-help" class="form-text">
                                                            <fmt:message bundle="${loc}"
                                                                         key="local.form.project.description-help"/>
                                                        </small>
                                                        <div id="regex-description"
                                                             class="hidden-regex">${regexProjectDescription}</div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                            data-dismiss="modal">
                                                        <fmt:message bundle="${loc}"
                                                                     key="local.projects.update-cancel"/></button>
                                                    <button type="submit" class="btn btn-primary"
                                                            id="create-task-button">
                                                        <fmt:message bundle="${loc}"
                                                                     key="local.projects.update-submit"/>
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="pagging row" aria-label="...">
            <a class="first col" style="text-align: center"><fmt:message bundle="${loc}"
                                                                         key="local.pagination.first"/></a>
            <a class="prev col" style="text-align: center"><fmt:message bundle="${loc}"
                                                                        key="local.pagination.previous"/></a> <a
                id="num1"
                class="num1 col"
                style="text-align: center"> </a>
            <a id="num2" class="num2 col" style="color:gray; text-align: center"> </a> <a id="num3" class="num3 col"
                                                                                          style="text-align: center"> </a>
            <a class="next col" style="text-align: center"><fmt:message bundle="${loc}"
                                                                        key="local.pagination.next"/></a> <a
                class="last col" style="text-align: center"><fmt:message bundle="${loc}"
                                                                         key="local.pagination.last"/></a>
        </div>
    </div>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
<script type="text/javascript" src="/js/validation.js"></script>
<script type="text/javascript" src="/js/projects.js"></script>
<link rel="stylesheet" href="/css/validation.css">
<script type="text/javascript" src="/js/pagging.js"></script>
</body>
</html>
