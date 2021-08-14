<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.requirement.creation"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/validation.css">
</head>
<body>
<div class="container-xxl">
    <div class="title h1 text-center mt-3"><fmt:message bundle="${loc}" key="local.requirement.create-header"/></div>
    <div class="row m-2">
        <div class="col-3 d-flex align-items-start">
        <a href="?command=technical-tasks">
            <button type="button" class="btn btn-secondary"><fmt:message bundle="${loc}" key="local.requirement.back"/></button>
        </a>
        </div>
        <div class="col-6">
            <select class="form-select" aria-label="Default select example" id="task">
                <option value="null" selected><fmt:message bundle="${loc}" key="local.requirement.create-select"/></option>
                <c:forEach items="${technicalTasks}" var="task">
                    <option value="${task.id}"><c:out value="${task.name}"/></option>
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
            <div id="regex-experience" class="hidden-regex">${regexExperience}</div>
            <div id="regex-salary" class="hidden-regex">${regexSalary}</div>
            <div id="regex-comment" class="hidden-regex">${regexComment}</div>
            <div id="regex-qualification" class="hidden-regex">${regexQualification}</div>
            <div id="regex-primary" class="hidden-regex">${regexPrimary}</div>
        </div>
        <div class="d-flex justify-content-center">
            <button type="submit" class="add btn btn-secondary mt-3" id="add"><fmt:message bundle="${loc}" key="local.requirement.create-button"/></button>
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
                    <fmt:message bundle="${loc}" key="local.requirement.update-header"/>
                </h5>
            </div>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="update-requirement"/>
                <input type="hidden" name="id" id="updateRequirementId"/>
                <input type="hidden" name="technical-task-id" id="forUpdateTechnicalTaskId"/>
                <div class="modal-body">
                    <label for="updateExperience" class="col-form-label"><fmt:message
                            bundle="${loc}" key="local.requirement.update-experience"/></label>
                    <input type="text" class="form-control" name="experience" id="updateExperience">
                    <label for="updateSalary" class="col-form-label"><fmt:message
                            bundle="${loc}" key="local.requirement.update-salary"/></label>
                    <input type="text" class="form-control" name="salary" id="updateSalary">
                    <label for="updateQualification" class="col-form-label"><fmt:message
                            bundle="${loc}" key="local.requirement.update-qualification"/></label>
                    <input type="text" class="form-control" name="qualification" id="updateQualification">
                    <label for="updatePrimary" class="col-form-label"><fmt:message
                            bundle="${loc}" key="local.requirement.update-primary"/></label>
                    <input type="text" class="form-control" name="primary" id="updatePrimary">
                    <label for="updateComment" class="col-form-label"><fmt:message
                            bundle="${loc}" key="local.requirement.update-comment"/></label>
                    <textarea class="form-control" name="comment"
                              id="updateComment"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        <fmt:message bundle="${loc}"
                                     key="local.requirement.update-cancel"/></button>
                    <button type="submit" class="btn btn-primary" id="update-task-button">
                        <fmt:message bundle="${loc}"
                                     key="local.requirement.update-submit"/></button>
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
<script type="text/javascript" src="/js/requirement_creation.js"></script>
</body>
</html>

