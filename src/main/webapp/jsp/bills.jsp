<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.bills"/></title>
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
    <div class="title h1 text-center mt-3"><fmt:message bundle="${loc}" key="local.bills.header"/></div>
    <div class="d-flex justify-content-center mt-3">
        <form id="my_radio_box">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="3"
                       checked>
                <label class="form-check-label" for="inlineRadio1">3</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="6">
                <label class="form-check-label" for="inlineRadio2">6</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="9">
                <label class="form-check-label" for="inlineRadio2">9</label>
            </div>
        </form>
    </div>
    <div class="pagging-frame row justify-content-center">
        <div class="elements row card-deck justify-content-center mt-1">
            <c:forEach items="${bills}" var="bill">
                <div class="element col-3 card text-center m-2 ">
                    <div class="card-header d-flex justify-content-between">
                        <div class="d-flex flex-row align-items-center">
                        <h4 class="my-0 font-weight-normal"><fmt:message bundle="${loc}"
                                                                         key="local.bills.number"/>${bill.id}</h4>
                        </div>
                        <c:choose>
                            <c:when test="${bill.status == 'NOT_PAID'}">
                                <div class="badge"><span class="red"><fmt:message bundle="${loc}"
                                                                                  key="local.bills.not-paid"/></span>
                                </div>
                            </c:when>
                            <c:when test="${bill.status == 'PAID'}">
                                <div class="badge"><span class="green"><fmt:message bundle="${loc}"
                                                                                    key="local.bills.paid"/></span>
                                </div>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="card-body text-center">
                        <h1 class="card-title pricing-card-title">$${bill.amountDue}</h1>
                        <h6 class="my-0 font-weight-normal"><fmt:message bundle="${loc}" key="local.bills.project"/>:
                            lkdsmfsdf</h6>
                        <hr>
                        <ul class="list-unstyled mt-1 mb-4">
                            <li><fmt:message bundle="${loc}" key="local.bills.for"/>:</li>
                            <li>${bill.information}</li>
                        </ul>
                        <c:choose>
                            <c:when test="${bill.status == 'NOT_PAID'}">
                                <button type="button" class="btn btn-lg btn-block btn-outline-primary">
                                    <fmt:message bundle="${loc}" key="local.bills.pay"/>
                                </button>
                            </c:when>
                            <c:when test="${bill.status == 'PAID'}">
                                <button type="button" class="btn btn-lg btn-block btn-outline-primary disabled">
                                    <fmt:message bundle="${loc}" key="local.bills.pay"/>
                                </button>
                            </c:when>
                        </c:choose>
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
<script type="text/javascript" src="/js/pagging.js"></script>
</body>
</html>