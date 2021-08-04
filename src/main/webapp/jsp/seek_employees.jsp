<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.seek-employees"/></title>
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
    <div class="title h1 text-center mt-3"><fmt:message bundle="${loc}" key="local.employee.seek-header"/></div>
    <div class="row h-100 justify-content-center">
        <div class="col-6" id="form2">
            <div>
                <table class="table my-2">
                    <thead>
                    <tr>
                        <th scope="col">№
                            <input type="hidden" class="identifier" value="1"/>
                        </th>
                        <th scope="col"><fmt:message bundle="${loc}" key="local.employee.experience"/></th>
                        <th scope="col"><fmt:message bundle="${loc}" key="local.employee.salary"/></th>
                        <th scope="col"><fmt:message bundle="${loc}" key="local.employee.qualification"/></th>
                        <th scope="col"><fmt:message bundle="${loc}" key="local.employee.primary"/></th>
                        <th scope="col"><fmt:message bundle="${loc}" key="local.employee.comment"/></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requirements}" var="requirement" varStatus="theCount">
                        <tr class="requirement">
                            <th scope="row">${theCount.count}
                                <input type="hidden" class="identifier" value="${requirement.id}"/>
                            </th>
                            <td class="experience" >${requirement.experience}</td>
                            <td class="salary" >${requirement.salary}</td>
                            <td>${requirement.qualification}</td>
                            <td class="primary" >${requirement.primarySkill}</td>
                            <td>${requirement.comment}</td>
                            <td>
                                <button type="button" class="seek btn btn-primary"><i class="fa fa-search"></i></button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-6" id="form3" style="display:none;">
            <div class="employees row"></div>
        </div>
    </div>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="http://cdn.webix.com/edge/webix.js" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/js/seek_employees.js"></script>
</body>
</html>
