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
    <link rel="stylesheet" href="/css/base.css">
</head>
<body>
<div class="container-xxl">
    <div class="title h1 text-center mt-3"><fmt:message bundle="${loc}" key="local.technical-tasks.header"/></div>

    <div class="d-flex justify-content-between mt-3">
        <c:if test="${ role == 'customer' }">
            <a href="?command=create-technical-task">
                <button id="create-task" type="button" class="btn btn-primary" >New</button>
            </a>
        </c:if>
        <form id="my_radio_box">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="4"
                       checked>
                <label class="form-check-label" for="inlineRadio1">4</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="8">
                <label class="form-check-label" for="inlineRadio2">8</label>
            </div>
        </form>
        <div></div>
    </div>
    <div class="pagging-frame row justify-content-center">
        <div class="elements row mt-3">
            <c:forEach items="${technicalTasks}" var="task">
                <div class="element col-6">
                    <div class="card p-3 mb-2">
                        <div class="d-flex justify-content-between">
                            <div class="d-flex flex-row align-items-center">
                                <h2 class="mb-0">${task.name}</h2>
                            </div>
                            <c:choose>
                                <c:when test="${task.status == 'WAIT_PROJECT'}">
                                    <div class="badge"><span class="violet"><fmt:message bundle="${loc}"
                                                                                         key="local.technical-tasks.wait-project"/></span>
                                    </div>
                                </c:when>
                                <c:when test="${task.status == 'ON_PROJECT'}">
                                    <div class="badge"><span class="blue"><fmt:message bundle="${loc}"
                                                                                       key="local.technical-tasks.on-project"/></span>
                                    </div>
                                </c:when>
                                <c:when test="${task.status == 'COMPLITED'}">
                                    <div class="badge"><span class="green"><fmt:message bundle="${loc}"
                                                                                        key="local.technical-tasks.complited"/></span>
                                    </div>
                                </c:when>
                                <c:when test="${task.status == 'EDITING'}">
                                    <div class="badge"><span class="yellow"><fmt:message bundle="${loc}"
                                                                                         key="local.technical-tasks.editing"/></span>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>

                        <hr>
                        <div class="requirements mt-5 mx-3">
                            <h6 class="heading">${task.overview}</h6>

                            <div style="display:none;" class="list">
                                <table class="table my-5">
                                    <thead>
                                    <tr>
                                        <th scope="col">№</th>
                                        <th scope="col"><fmt:message bundle="${loc}"
                                                                     key="local.requirement.experience"/></th>
                                        <th scope="col"><fmt:message bundle="${loc}"
                                                                     key="local.requirement.salary"/></th>
                                        <th scope="col"><fmt:message bundle="${loc}"
                                                                     key="local.requirement.qualification"/></th>
                                        <th scope="col"><fmt:message bundle="${loc}"
                                                                     key="local.requirement.primary"/></th>
                                        <th scope="col"><fmt:message bundle="${loc}"
                                                                     key="local.requirement.comment"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>

                            <div class="mt-5">
                                <div class="d-flex justify-content-between mr-3">
                                    <div class="d-flex flex-row align-items-center">
                                        <button type="button" class="details btn btn-primary"><fmt:message
                                                bundle="${loc}"
                                                key="local.technical-tasks.details"/></button>
                                        <button style="display:none;" type="button" class="hide btn btn-primary">
                                            <fmt:message bundle="${loc}"
                                                         key="local.technical-tasks.hide"/>
                                            details
                                        </button>
                                    </div>
                                    <form action="controller">
                                        <input type="hidden" class="identifier" name="technical-task-id"
                                               value="${task.id}"/>
                                        <c:if test="${ role == 'manager' }">
                                            <input type="hidden" class="command" name="command"
                                                   value="to-create-project"/>
                                            <button type="submit" class="btn btn-primary"><fmt:message bundle="${loc}"
                                                                                                       key="local.technical-tasks.create"/></button>
                                        </c:if>
                                    </form>
                                </div>

                            </div>
                            <div class="mt-3"><span class="text1"><fmt:message bundle="${loc}"
                                                                               key="local.technical-tasks.deadline"/> <span
                                    class="text2">${task.deadline}</span></span></div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
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
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="http://cdn.webix.com/edge/webix.js" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/js/technical_task.js"></script>
<script type="text/javascript" src="/js/pagging.js"></script>
</body>
</html>
