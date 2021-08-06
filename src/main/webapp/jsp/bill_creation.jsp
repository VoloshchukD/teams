<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.bill.creation"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/base.css">
</head>
<body>
<div class="container-xxl">
    <div class="title h1 text-center mt-3"><fmt:message bundle="${loc}" key="local.bills.create-header"/></div>
    <div class="row justify-content-center mt-3">
        <div class="col-5">
            <form action="controller" method="post" >
                <input type="hidden" class="command" name="command" value="create-bill"/>
                <select class="form-select" name="project-id" id="project">
                    <option value="null" selected><fmt:message bundle="${loc}" key="local.bills.create-project"/></option>
                    <c:forEach items="${projects}" var="project">
                    <option value="${project.id}">${project.name}</option>
                    </c:forEach>
                </select>
                <label for="amount" class="col-form-label" ><fmt:message bundle="${loc}" key="local.bills.create-amount"/></label>
                <input type="text" class="form-control" style="width: 120px;" name="amount" id="amount"/>
                <div class="form-group">
                    <label for="information" class="col-form-label"><fmt:message bundle="${loc}" key="local.bills.create-information"/></label>
                    <textarea class="form-control" name="information" id="information" style="height: 200px;"></textarea>
                </div>
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary mt-3"><fmt:message bundle="${loc}" key="local.bills.create-button"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
<script src="http://cdn.webix.com/edge/webix.js" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/js/bill_creation.js"></script>
</body>
</html>


