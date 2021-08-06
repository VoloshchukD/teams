

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.seek-employees"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/base.css">
</head>
<body>
<div class="container-xxl">
    <div class="title h1 text-center mt-3"><fmt:message bundle="${loc}" key="local.employee.seek-header"/></div>
    <div class="row justify-content-center">
        <div class="col-6">
            <select class="form-select" name="project-id" id="projects">
                <option value="null" selected><fmt:message bundle="${loc}" key="local.bills.create-project"/></option>
                <c:forEach items="${projects}" var="project">
                    <option value="${project.id}">${project.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="row h-100 justify-content-center mt-2">
        <div class="col-6" id="form2">
            <div>
                <table class="table my-2" id="requirements" >
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
<script src="https://use.fontawesome.com/6d201ab77c.js"></script>
<script src="http://cdn.webix.com/edge/webix.js" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/js/seek_employees.js"></script>
</body>
</html>