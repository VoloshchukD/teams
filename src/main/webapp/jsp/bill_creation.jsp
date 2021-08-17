<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.bill.creation"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/base.css">
    <script type="text/javascript" src="/js/validation.js"></script>
    <link rel="stylesheet" href="/css/validation.css">
    <script src="http://cdn.webix.com/edge/webix.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="/js/bill_creation.js"></script>
</head>
<body>
<div class="container-xxl">
    <div class="title h1 text-center mt-3">
        <fmt:message bundle="${loc}" key="local.bills.create-header"/>
    </div>
    <c:if test="${error}">
        <div class="row justify-content-center">
            <div class="col-6">
                <div class="alert alert-danger alert-dismissible" role="alert" id="error">
                    <strong>
                        <fmt:message bundle="${loc}" key="local.message.error.header"/>
                    </strong>
                    <fmt:message bundle="${loc}" key="local.message.bill-create.error"/>
                    <button type="button" class="close" data-dismiss="alert">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
        </div>
    </c:if>
    <div class="row justify-content-center mt-3">
        <div class="col-5">
            <form action="controller" method="post" onSubmit="return validateBillForm()" novalidate>
                <input type="hidden" class="command" name="command" value="create-bill"/>
                <select class="form-select" name="project-id" id="project">
                    <option value="null" selected>
                        <fmt:message bundle="${loc}" key="local.bills.create-project"/>
                    </option>
                    <c:forEach items="${projects}" var="project">
                    <option value="${project.id}">
                            <c:out value="${project.name}"/>
                        </c:forEach>
                </select>
                <label for="amount" class="col-form-label">
                    <fmt:message bundle="${loc}" key="local.bills.create-amount"/>
                </label>
                <input type="text" class="form-control"
                       style="width: 120px;" name="amount" id="amount"/>
                <small id="amount-help" class="form-text">
                    <fmt:message bundle="${loc}" key="local.form.bill.amount-help"/>
                </small>
                <div id="regex-amount" class="hidden-regex">${regexAmount}</div>
                <div class="form-group">
                    <label for="information" class="col-form-label">
                        <fmt:message bundle="${loc}" key="local.bills.create-information"/>
                    </label>
                    <textarea class="form-control" name="information" id="information"
                              style="height: 200px;"></textarea>
                    <small id="information-help" class="form-text">
                        <fmt:message bundle="${loc}" key="local.form.bill.information-help"/>
                    </small>
                    <div id="regex-information" class="hidden-regex">${regexInformation}</div>
                </div>
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary mt-3">
                        <fmt:message bundle="${loc}" key="local.bills.create-button"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
</body>
</html>


