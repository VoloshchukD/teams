<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <title><fmt:message bundle="${loc}" key="local.profile"/></title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
          crossorigin="anonymous">
    <link rel="stylesheet" href="http://cdn.webix.com/edge/webix.css" type="text/css">
    <script src="http://cdn.webix.com/edge/webix.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="/js/validation.js"></script>
    <script type="text/javascript" src="/js/profile.js"></script>
    <link rel="stylesheet" href="/css/validation.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-xxl">
    <div class="row h-auto justify-content-center mx-n5 my-3">
        <div class="col-5 my-auto px-5">
            <div class="row w-auto h-auto justify-content-center">
                <input type="hidden" name="uploadButtonName"
                       value="<fmt:message bundle="${loc}" key="local.profile.change-photo"/>"
                       id="uploadButtonName"/>
                <input type="hidden" name="userImageUrl" value="<c:out value="${avatar}"/>"
                       id="userImageUrl"/>
                <div id="box" class="justify-content-center"></div>
            </div>
            <div class="row d-flex justify-content-center mt-3">
                <div class="form-check form-switch col-3 mr-2">
                    <c:choose>
                        <c:when test="${userData.status == 'BUSY'}">
                            <input class="form-check-input" type="checkbox" id="status-checker">
                        </c:when>
                        <c:when test="${userData.status == 'NOT_BUSY'}">
                            <input class="form-check-input" type="checkbox" id="status-checker" checked>
                        </c:when>
                    </c:choose>
                    <label class="form-check-label" for="status-checker">
                        <fmt:message bundle="${loc}" key="local.profile.ready"/>
                    </label>
                </div>
                <div class="col-1 mb-1">
                    <button type="button" class="btn btn-danger mt-1 delete"
                            data-toggle="modal" data-target="#modal"
                            id="show-modal-button">
                        <fmt:message bundle="${loc}" key="local.profile.delete"/>
                    </button>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modal" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            <fmt:message bundle="${loc}" key="local.profile.confirmation"/>
                        </h5>
                    </div>
                    <div class="modal-body">
                        <h4 class="modal-title" id="myModalLabel">
                            <fmt:message bundle="${loc}" key="local.profile.confirmation-message"/>
                        </h4>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            <fmt:message bundle="${loc}" key="local.profile.delete-cancel"/>
                        </button>
                        <a href="?command=delete-account">
                            <button type="button" class="btn btn-primary" id="delete-account-button">
                                <fmt:message bundle="${loc}" key="local.profile.delete-confirm"/>
                            </button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-5 my-auto px-5">
            <form method="post" action="async-controller" novalidate>
                <input type="hidden" name="command" value="update-user-detail"/>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="forename">
                            <fmt:message bundle="${loc}" key="local.form.forename"/>
                        </label>
                        <input type="text" class="form-control" name="forename" id="forename"
                               value="<c:out value="${userData.firstName}"/>">
                        <small id="forename-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.form.forename-help"/>
                        </small>
                        <div id="regex-forename" class="hidden-regex">${regexForename}</div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="surname">
                            <fmt:message bundle="${loc}" key="local.form.surname"/>
                        </label>
                        <input type="text" class="form-control" name="surname" id="surname"
                               value="<c:out value="${userData.lastName}"/>">
                        <small id="surname-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.form.surname-help"/>
                        </small>
                        <div id="regex-surname" class="hidden-regex">${regexSurname}</div>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="company">
                        <fmt:message bundle="${loc}" key="local.form.company"/>
                    </label>
                    <input type="text" class="form-control" name="company" id="company"
                           value="<c:out value="${userData.company}"/>">
                    <small id="company-help" class="form-text">
                        <fmt:message bundle="${loc}" key="local.form.company-help"/>
                    </small>
                    <div id="regex-company" class="hidden-regex">${regexCompany}</div>
                </div>

                <div class="mb-3">
                    <label for="position">
                        <fmt:message bundle="${loc}" key="local.form.position"/>
                    </label>
                    <input type="text" class="form-control" name="position" id="position"
                           value="<c:out value="${userData.position}"/>">
                    <small id="position-help" class="form-text">
                        <fmt:message bundle="${loc}" key="local.form.position-help"/>
                    </small>
                    <div id="regex-position" class="hidden-regex">${regexPosition}</div>
                </div>
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="experience">
                            <fmt:message bundle="${loc}" key="local.form.experience"/>
                        </label>
                        <input type="text" class="form-control" name="experience" id="experience"
                               value="<c:out value="${userData.experience}"/>">
                        <small id="experience-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.form.experience-help"/>
                        </small>
                        <div id="regex-experience" class="hidden-regex">${regexExperience}</div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="salary">
                            <fmt:message bundle="${loc}" key="local.form.salary"/>
                        </label>
                        <input type="text" class="form-control" name="salary" id="salary"
                               value="<c:out value="${userData.salary}"/>">
                        <small id="salary-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.form.salary-help"/>
                        </small>
                        <div id="regex-salary" class="hidden-regex">${regexSalary}</div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="primary">
                            <fmt:message bundle="${loc}" key="local.form.primary"/>
                        </label>
                        <input type="text" class="form-control" name="primary" id="primary"
                               value="<c:out value="${userData.primarySkill}"/>">
                        <small id="primary-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.form.primary-help"/>
                        </small>
                        <div id="regex-primary" class="hidden-regex">${regexPrimary}</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col mb-3">
                        <label for="skills">
                            <fmt:message bundle="${loc}" key="local.form.skills"/>
                        </label>
                        <textarea class="form-control" aria-label="With textarea"
                                  name="skills"
                                  id="skills"><c:out value="${userData.skillsDescription}"/></textarea>
                        <small id="skills-description-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.form.skills-help"/>
                        </small>
                        <div id="regex-skills" class="hidden-regex">${regexSkills}</div>
                    </div>
                </div>
                <div class="d-flex justify-content-center">
                    <button class="btn btn btn-primary btn-block"
                            type="button" onclick="submitEditForm()">
                        <fmt:message bundle="${loc}" key="local.profile.update"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
</body>
</html>
