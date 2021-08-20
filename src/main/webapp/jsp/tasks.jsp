<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.tasks"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
          crossorigin="anonymous">
    <link rel="stylesheet" href="/css/base.css">
    <link rel="stylesheet" href="http://cdn.webix.com/edge/webix.css" type="text/css">
    <link rel="stylesheet" href="/css/validation.css">
</head>
<body>
<div class="container-xxl">

    <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">
                        <fmt:message bundle="${loc}" key="local.tasks.create-header"/>
                    </h5>
                </div>
                <div class="modal-body">
                    <form novalidate>
                        <label for="createName" class="col-form-label">
                            <fmt:message bundle="${loc}" key="local.tasks.create-name"/>
                        </label>
                        <input type="text" class="form-control create-form" name="name" id="createName">
                        <small id="createName-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.tasks.name-help"/>
                        </small>
                        <label for="createDetails" class="col-form-label">
                            <fmt:message bundle="${loc}" key="local.tasks.create-description"/>
                        </label>
                        <textarea class="form-control create-form"
                                  name="details" id="createDetails"></textarea>
                        <small id="createDetails-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.tasks.details-help"/>
                        </small>
                        <label for="createHours" class="col-form-label">
                            <fmt:message bundle="${loc}" key="local.tasks.create-hours"/>
                        </label>
                        <input type="text" class="form-control create-form" name="hours" id="createHours">
                        <small id="createHours-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.tasks.hours-help"/>
                        </small>
                        <label for="developer" class="col-form-label">
                            <fmt:message bundle="${loc}" key="local.tasks.create-executor"/>
                        </label>
                        <select class="form-select" aria-label="Default select example"
                                name="developer" id="developer">
                            <option value="null" selected>
                                <fmt:message bundle="${loc}" key="local.tasks.create-assign"/>
                            </option>
                        </select>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        <fmt:message bundle="${loc}" key="local.tasks.create-cancel"/>
                    </button>
                    <button type="button" class="btn btn-primary" id="create-task-button">
                        <fmt:message bundle="${loc}" key="local.tasks.create-add"/>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">
                        <fmt:message bundle="${loc}" key="local.tasks.update-header"/>
                    </h5>
                </div>
                <form action="controller" method="post"
                      onSubmit="return validateUpdateTaskForm()" novalidate>
                    <input type="hidden" name="command" value="update-task"/>
                    <input type="hidden" name="task-id" id="updateTaskId"/>
                    <input type="hidden" name="project-id" id="forUpdateProjectId"/>
                    <div class="modal-body">
                        <label for="updateName" class="col-form-label">
                            <fmt:message bundle="${loc}" key="local.tasks.update-name"/>
                        </label>
                        <input type="text" class="form-control update-form" name="name" id="updateName">
                        <small id="name-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.tasks.name-help"/>
                        </small>
                        <div id="regex-name" class="hidden-regex">${regexName}</div>
                        <label for="updateDetails" class="col-form-label">
                            <fmt:message bundle="${loc}" key="local.tasks.update-description"/>
                        </label>
                        <textarea class="form-control update-form"
                                  name="details" id="updateDetails"></textarea>
                        <small id="details-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.tasks.details-help"/>
                        </small>
                        <div id="regex-details" class="hidden-regex">${regexDetails}</div>
                        <label for="updateHours" class="col-form-label">
                            <fmt:message bundle="${loc}" key="local.tasks.update-hours"/>
                        </label>
                        <input type="text" class="form-control update-form" name="hours" id="updateHours">
                        <small id="hours-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.tasks.hours-help"/>
                        </small>
                        <label for="updateDeveloper" class="col-form-label">
                            <fmt:message bundle="${loc}" key="local.tasks.update-executor"/>
                        </label>
                        <div id="regex-hours" class="hidden-regex">${regexHours}</div>
                        <select class="form-select" name="id" id="updateDeveloper">
                            <option value="null" selected>
                                <fmt:message bundle="${loc}" key="local.tasks.update-assign"/>
                            </option>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            <fmt:message bundle="${loc}" key="local.tasks.update-cancel"/>
                        </button>
                        <button type="submit" class="btn btn-primary" id="update-task-button">
                            <fmt:message bundle="${loc}" key="local.tasks.update-submit"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <c:if test="${ role == 'MANAGER' }">
        <div class="row d-flex justify-content-center m-3">
            <div class="col-4  text-center">
                <div class="card">
                    <div class="card-body">
                        <button type="button" class="btn btn-primary mt-1" data-toggle="modal"
                                data-target="#modal" id="create-task-modal-button">
                            <fmt:message bundle="${loc}" key="local.tasks.button-modal"/>
                        </button>
                    </div>
                </div>
            </div>
            <div class="col-4 text-center">
                <div class="card">
                    <div class="card-body">
                        <button type="button" class="btn btn-primary mt-1" data-toggle="modal"
                                data-target="#finishModal" id="finish-project-modal-button">
                            <fmt:message bundle="${loc}" key="local.tasks.finish-project"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <div class="row m-3">
        <div class="col-4 text-center">
            <div class="card">
                <div class="card-body">
                    <h2>
                        <fmt:message bundle="${loc}" key="local.tasks.to-do"/>
                    </h2>
                </div>
            </div>
        </div>
        <div class="col-4 text-center">
            <div class="card">
                <div class="card-body">
                    <h2>
                        <fmt:message bundle="${loc}" key="local.tasks.in-progress"/>
                    </h2>
                </div>
            </div>
        </div>
        <div class="col-4 text-center">
            <div class="card">
                <div class="card-body">
                    <h2>
                        <fmt:message bundle="${loc}" key="local.tasks.done"/>
                    </h2>
                </div>
            </div>
        </div>
    </div>
    <div class="row justify-content-center h-auto m-3">
        <div class="col-4" id="col1">
            <c:forEach items="${tasks}" var="task">
                <c:if test="${ task.status == 'TO_DO' }">
                    <%@ include file="../WEB-INF/jspf/task_card.jspf" %>
                </c:if>
            </c:forEach>
        </div>
        <div class="col-4" id="col2">
            <c:forEach items="${tasks}" var="task">
                <c:if test="${ task.status == 'IN_PROGRESS' }">
                    <%@ include file="../WEB-INF/jspf/task_card.jspf" %>
                </c:if>
            </c:forEach>
        </div>
        <div class="col-4" id="col3">
            <c:forEach items="${tasks}" var="task">
                <c:if test="${ task.status == 'DONE' }">
                    <%@ include file="../WEB-INF/jspf/task_card.jspf" %>
                </c:if>
            </c:forEach>
        </div>
    </div>

    <div class="modal fade" id="finishModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalHeader">
                        <fmt:message bundle="${loc}" key="local.project.finish-header"/></h5>
                </div>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="finish-project"/>
                    <input type="hidden" name="project-id" id="updateProjectId"/>
                    <input type="hidden" name="technical-task-id" id="updateTechnicalTaskId"/>
                    <div class="modal-body">
                        <div class="form-group">
                            <div>
                                <c:choose>
                                    <c:when test="${tasks.size() > 0}">
                                        <fmt:message bundle="${loc}" key="local.projects.fail-finish"/>
                                    </c:when>
                                    <c:when test="${tasks.size() == 0}">
                                        <fmt:message bundle="${loc}" key="local.projects.success-finish"/>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal">
                            <fmt:message bundle="${loc}" key="local.projects.finish-cancel"/>
                        </button>
                        <c:if test="${ tasks.size() == 0 }">
                            <button type="submit" class="btn btn-primary" id="finish-project-button">
                                <fmt:message bundle="${loc}" key="local.projects.finish-submit"/>
                            </button>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
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
<script type="text/javascript" src="/js/validation.js"></script>
<script type="text/javascript" src="/js/tasks.js"></script>
</body>
</html>

