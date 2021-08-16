<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.payment.form"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous">
    <link rel="stylesheet" href="/css/base.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="/js/validation.js"></script>
    <script type="text/javascript" src="/js/payment.js"></script>
    <link rel="stylesheet" href="/css/validation.css">
</head>
<body>
<div class="container-xxl">
    <div class="row d-flex justify-content-center mt-3">
        <div class="col-4">
            <jsp:useBean id="bill" class="by.voloshchuk.entity.Bill" scope="request"/>
            <form action="controller" method="post" onSubmit="return validatePaymentForm()" novalidate>
                <input type="hidden" name="command" value="make-payment"/>
                <input type="hidden" name="bill-id" value="<c:out value="${bill.id}"/>"/>
                <div class="products">
                    <h3 class="title">
                        <fmt:message bundle="${loc}" key="local.payment.header"/>
                        №
                        <jsp:getProperty name="bill" property="id"/>
                    </h3>
                    <hr>
                    <div>
                        <span>
                            <c:out value="${bill.amountDue}"/>
                        </span>
                        <p>
                            <c:out value="${bill.information}"/>
                        </p>
                    </div>
                    <hr>
                </div>
                <div class="card-details">
                    <h3 class="title">
                        <fmt:message bundle="${loc}" key="local.payment.details"/>
                    </h3>
                    <div class="row">
                        <div class="form-group col-sm-7">
                            <label for="holder">
                                <fmt:message bundle="${loc}" key="local.payment.holder"/>
                            </label>
                            <input id="holder" name="card-holder" type="text" class="form-control"
                                   placeholder="<fmt:message bundle="${loc}" key="local.payment.holder"/>"
                                   aria-label="Card Holder" aria-describedby="basic-addon1">
                            <small id="holder-help" class="form-text">
                                <fmt:message bundle="${loc}" key="local.payment.holder-help"/>
                            </small>
                            <div id="regex-holder" class="hidden-regex">${regexHolder}</div>
                        </div>
                        <div class="form-group col-sm-5">
                            <label>
                                <fmt:message bundle="${loc}" key="local.payment.date"/>
                            </label>
                            <div class="input-group expiration-date">
                                <input id="mm" type="text" name="mm" class="form-control"
                                       placeholder="<fmt:message bundle="${loc}" key="local.payment.mm"/>"
                                       aria-label="MM" aria-describedby="basic-addon1">
                                <small id="mm-help" class="form-text">
                                    <fmt:message bundle="${loc}" key="local.payment.mm-help"/>
                                </small>
                                <div id="regex-mm" class="hidden-regex">${regexMm}</div>
                                <input id="yy" type="text" name="yy" class="form-control"
                                       placeholder="<fmt:message bundle="${loc}" key="local.payment.yy"/>"
                                       aria-label="YY" aria-describedby="basic-addon1">
                                <small id="yy-help" class="form-text">
                                    <fmt:message bundle="${loc}" key="local.payment.yy-help"/>
                                </small>
                                <div id="regex-yy" class="hidden-regex">${regexYy}</div>
                            </div>
                        </div>
                        <div class="form-group col-sm-8">
                            <label for="number">
                                <fmt:message bundle="${loc}" key="local.payment.card-number"/>
                            </label>
                            <input id="number" name="card-number" type="text" class="form-control"
                                   placeholder="<fmt:message bundle="${loc}"
                                   key="local.payment.card-number"/>"
                                   aria-label="Card Holder" aria-describedby="basic-addon1">
                            <small id="number-help" class="form-text">
                                <fmt:message bundle="${loc}" key="local.payment.number-help"/>
                            </small>
                            <div id="regex-number" class="hidden-regex">${regexNumber}</div>
                        </div>
                        <div class="form-group col-sm-4">
                            <label for="cvc">
                                <fmt:message bundle="${loc}" key="local.payment.cvc"/>
                            </label>
                            <input id="cvc" name="cvc" type="text" class="form-control"
                                   placeholder="<fmt:message bundle="${loc}" key="local.payment.cvc"/>"
                                   aria-label="Card Holder" aria-describedby="basic-addon1">
                            <small id="cvc-help" class="form-text">
                                <fmt:message bundle="${loc}" key="local.payment.cvc-help"/>
                            </small>
                            <div id="regex-cvc" class="hidden-regex">${regexCvc}</div>
                        </div>
                        <div class="form-group col-sm-12">
                            <button type="submit" class="btn btn-primary btn-block mt-2">
                                <fmt:message bundle="${loc}" key="local.payment.submit"/>
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
</body>
</html>
