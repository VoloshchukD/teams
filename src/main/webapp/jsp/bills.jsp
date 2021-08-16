<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.bills"/></title>
    <%@ taglib prefix="ctg" uri="custom-tags" %>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous">
    <link rel="stylesheet" href="/css/base.css">
    <link rel="stylesheet" href="/css/validation.css">
</head>
<body>
<div class="container-xxl">
    <div class="title h1 text-center mt-3">
        <fmt:message bundle="${loc}" key="local.bills.header"/>
    </div>
    <div class="d-flex justify-content-center mt-3">
        <form id="radio">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="unitPerPage" id="option1" value="3"
                       checked>
                <label class="form-check-label" for="option1">3</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="unitPerPage" id="option2" value="6">
                <label class="form-check-label" for="option2">6</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="unitPerPage" id="option3" value="9">
                <label class="form-check-label" for="option2">9</label>
            </div>
        </form>
    </div>
    <div class="frame row justify-content-center">
        <div class="elements row card-deck justify-content-center mt-1">
            <c:forEach items="${bills}" var="bill">
                <div class="element col-3 card text-center m-2 ">
                    <div class="card-header d-flex justify-content-between">
                        <div class="d-flex flex-row align-items-center">
                            <h4 class="my-0 font-weight-normal">
                                <fmt:message bundle="${loc}" key="local.bills.number"/>
                                    ${bill.id}
                            </h4>
                        </div>
                        <c:choose>
                            <c:when test="${bill.status == 'NOT_PAID'}">
                                <div class="badge">
                                    <span class="red">
                                    <fmt:message bundle="${loc}" key="local.bills.not-paid"/>
                                </span>
                                </div>
                            </c:when>
                            <c:when test="${bill.status == 'PAID'}">
                                <div class="badge">
                                    <span class="green">
                                    <fmt:message bundle="${loc}" key="local.bills.paid"/>
                                    </span>
                                </div>
                            </c:when>
                            <c:when test="${bill.status == 'ACCEPTED'}">
                                <div class="badge">
                                    <span class="blue">
                                        <fmt:message bundle="${loc}" key="local.bills.accepted"/>
                                    </span>
                                </div>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="card-body text-center">
                        <h1 class="card-title pricing-card-title">
                            $<c:out value="${bill.amountDue}"/>
                        </h1>
                        <h6 class="my-0 font-weight-normal">
                            <fmt:message bundle="${loc}" key="local.bills.project"/>
                        </h6>
                        <hr>
                        <ul class="list-unstyled mt-1 mb-4">
                            <li>
                                <fmt:message bundle="${loc}" key="local.bills.for"/>:
                            </li>
                            <li>
                                <c:out value="${bill.information}"/>
                            </li>
                        </ul>
                        <c:if test="${ (bill.status == 'NOT_PAID') && (role == 'CUSTOMER') }">
                            <a href="?command=to-payment-form&bill-id=<c:out value="${bill.id}" />">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message bundle="${loc}" key="local.bills.pay"/>
                                </button>
                            </a>
                        </c:if>
                        <c:if test="${ (bill.status == 'PAID') && (role == 'MANAGER') }">
                            <div class="block">
                                <input type="hidden" class="id" value="<c:out value="${bill.id}" />"/>
                                <input type="hidden" class="update"
                                       value="<fmt:message bundle="${loc}" key="local.bills.accepted"/>"/>
                                <button type="button" class="accept btn btn-primary">
                                    <fmt:message bundle="${loc}" key="local.bills.accept"/>
                                </button>
                            </div>
                        </c:if>
                    </div>
                    <c:if test="${ role == 'MANAGER' && bill.status == 'NOT_PAID' }">
                        <hr>
                        <div class="editing mt-3 d-flex justify-content-between mx-5">
                            <div>
                                <input type="hidden" class="billId" name="bill-id"
                                       value="<c:out value="${bill.id}" />"/>
                                <button type="button" class="edit btn btn-secondary"
                                        data-toggle="modal" data-target="#modal">
                                    <i class="fa fa-wrench" aria-hidden="true"></i>
                                </button>
                            </div>
                            <div></div>
                            <div>
                                <form class="delete" action="controller" method="post">
                                    <input type="hidden" name="command" value="delete-bill"/>
                                    <input type="hidden" name="bill-id"
                                           value="<c:out value="${bill.id}" />"/>
                                    <input type="hidden" class="forDeleteProjectId"
                                           name="project-id"/>
                                    <button type="submit" class="btn btn-danger">
                                        <i class="fa fa-ban" aria-hidden="true"></i>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
        <div class="pagging row" aria-label="...">
            <a class="first col" style="text-align: center">
                <fmt:message bundle="${loc}" key="local.pagination.first"/>
            </a>
            <a class="prev col" style="text-align: center">
                <fmt:message bundle="${loc}" key="local.pagination.previous"/>
            </a>
            <a id="num1" class="num1 col" style="text-align: center"> </a>
            <a id="num2" class="num2 col" style="color:gray; text-align: center"> </a>
            <a id="num3" class="num3 col" style="text-align: center"> </a>
            <a class="next col" style="text-align: center">
                <fmt:message bundle="${loc}" key="local.pagination.next"/></a>
            <a class="last col" style="text-align: center">
                <fmt:message bundle="${loc}" key="local.pagination.last"/>
            </a>
        </div>
    </div>

    <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">
                        <fmt:message bundle="${loc}" key="local.bills.update-header"/>
                    </h5>
                </div>
                <form action="controller" method="post" onSubmit="return validateBillForm()" novalidate>
                    <input type="hidden" name="command" value="update-bill"/>
                    <input type="hidden" name="bill-id" id="updateBillId"/>
                    <input type="hidden" name="project-id" id="forUpdateProjectId"/>
                    <div class="modal-body">
                        <label for="amount" class="col-form-label">
                            <fmt:message bundle="${loc}" key="local.bills.update-amount"/>
                        </label>
                        <input type="text" class="form-control" name="amount" id="amount"
                               style="width: 120px;">
                        <small id="amount-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.form.bill.amount-help"/>
                        </small>
                        <div id="regex-amount" class="hidden-regex">${regexAmount}</div>
                        <label for="information" class="col-form-label">
                            <fmt:message bundle="${loc}" key="local.bills.update-information"/>
                        </label>
                        <textarea class="form-control" name="information" id="information"></textarea>
                        <small id="information-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.form.bill.information-help"/>
                        </small>
                        <div id="regex-information" class="hidden-regex">${regexInformation}</div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            <fmt:message bundle="${loc}" key="local.bills.update-cancel"/>
                        </button>
                        <button type="submit" class="btn btn-primary" id="create-task-button">
                            <fmt:message bundle="${loc}" key="local.bills.update-submit"/>
                        </button>
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
<script type="text/javascript" src="/js/bill_creation.js"></script>
<script type="text/javascript" src="/js/bills.js"></script>
<script type="text/javascript" src="/js/pagging.js"></script>
</body>
</html>