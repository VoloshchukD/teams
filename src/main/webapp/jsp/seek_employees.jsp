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
            <div class="employees row">
                <div class="employee col-4 card rounded shadow-sm border-0">
                    <div class="card-body p-0">
                        <div class="bg-primary px-5 py-4 text-center card-img-top"><img
                                src="https://d19m59y37dris4.cloudfront.net/university/1-1-1/img/teacher-4.jpg" alt="..."
                                width="100" class="rounded-circle mb-2 img-thumbnail d-block mx-auto">
                            <h5 class="text-white mb-0">Emma Nevoresky</h5>
                            <p class="small text-white mb-0">Primary skill</p>
                        </div>
                        <div class="p-3 d-flex justify-content-center">
                            <ul class="list-inline mb-0">
                                <li class="list-inline-item text-center">
                                    <h5 class="font-weight-bold mb-0 d-block">8</h5><small class="text-muted"><i
                                        class="fa fa-picture-o mr-1 text-primary"></i>Experience</small>
                                </li>
                                <li class="list-inline-item text-center">
                                    <h5 class="font-weight-bold mb-0 d-block">84</h5><small class="text-muted"><i
                                        class="fa fa-user-circle-o mr-1 text-primary"></i>Salary</small>
                                </li>
                            </ul>
                        </div>
                        <div class="pb-1 px-2 d-flex justify-content-center">
                            <ul class="list-inline mb-0">
                                <li class="text-center">
                                    <small class="text-muted"><i class="fa fa-picture-o mr-1 text-primary"></i>esrdgdgdr
                                        dfgfdgdfg dfgdfgdf</small>
                                </li>
                            </ul>
                        </div>
                        <hr>
                        <div class="d-flex justify-content-center mb-2">
                            <button type="button" class="add btn btn-primary"><i class="fa fa-plus"></i></button>
                        </div>
                    </div>
                </div>

            </div>
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
