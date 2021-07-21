<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.main"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
<div class="container-xxl">
    <div class="text-white my-5"
         style="height: 330px; padding: 30px; background-color: #020230;">
        <img class="d-block mx-auto" src="/images/logo.png" alt="" width="100" height="100">
        <div class="text-center">
            <h1 class="fw-bolder pt-2"><fmt:message bundle="${loc}" key="local.welcome"/></h1>
            <p class="lead"><fmt:message bundle="${loc}" key="local.slogan"/></p>
            <a class="btn btn-lg btn-light " href="?command=about"><fmt:message bundle="${loc}"
                                                                                key="local.call-to-about"/></a>
        </div>
    </div>

    <section>
        <hr>
        <div class="row my-5">
            <div class="col-md-3 col-sm-6 bottom-margin text-center counter-section sm-margin-bottom-ten"><i
                    class="fa fa-birthday-cake medium-icon"></i> <span class="counter-number"></span> <span
                    class="timer counter">${yearsOnMarket}</span>
                <p class="counter-title"><fmt:message bundle="${loc}" key="local.years"/></p>
            </div>
            <div class="col-md-3 col-sm-6 bottom-margin text-center counter-section sm-margin-bottom-ten"><i
                    class="fa fa-calendar medium-icon"></i> <span class="counter-number"></span> <span
                    class="timer counter">${projectsProductivity}</span>
                <p class="counter-title"><fmt:message bundle="${loc}" key="local.avg-project-duration"/></p>
            </div>
            <div class="col-md-3 col-sm-6 bottom-margin text-center counter-section sm-margin-bottom-ten"><i
                    class="fa fa-anchor medium-icon"></i> <span class="counter-number"></span> <span
                    class="timer counter">${projectsAmount}</span>
                <p class="counter-title"><fmt:message bundle="${loc}" key="local.completed"/></p>
            </div>
            <div class="col-md-3 col-sm-6 bottom-margin text-center counter-section sm-margin-bottom-ten"><i
                    class="fa fa-user medium-icon"></i> <span class="counter-number"></span> <span
                    class="timer counter">${customersAmount}</span>
                <p class="counter-title"><fmt:message bundle="${loc}" key="local.clients"/></p>
            </div>
        </div>
    </section>
    <hr>
    <div class="row gx-4 gx-lg-5 align-items-center my-5">
        <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0"
                                   src="/images/customer_main.jpg"
                                   alt="..."/></div>
        <div class="col-lg-5">
            <h1 class="font-weight-light"><fmt:message bundle="${loc}" key="local.for-customer"/></h1>
            <p><fmt:message bundle="${loc}" key="local.customer-text"/></p>
            <a class="btn btn-primary" href="?command=registration"><fmt:message bundle="${loc}"
                                                                                 key="local.call-to-reg1"/></a>
        </div>
    </div>
    <hr>
    <div class="row gx-4 gx-lg-5 align-items-center my-5">
        <div class="col-lg-5">
            <h1 class="font-weight-light"><fmt:message bundle="${loc}" key="local.for-manager"/></h1>
            <p><fmt:message bundle="${loc}" key="local.manager-text"/></p>
            <a class="btn btn-primary" href="?command=registration"><fmt:message bundle="${loc}"
                                                                                 key="local.call-to-reg2"/></a>
        </div>
        <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0"
                                   src="/images/manager_main.jpg"
                                   alt="..."/></div>
    </div>
    <hr>
    <div class="row gx-4 gx-lg-5 align-items-center my-5">
        <div class="col-lg-7"><img class="img-fluid rounded mb-4 mb-lg-0"
                                   src="/images/developer_main.jpg"
                                   alt="..."/></div>
        <div class="col-lg-5">
            <h1 class="font-weight-light"><fmt:message bundle="${loc}" key="local.for-developer"/></h1>
            <p><fmt:message bundle="${loc}" key="local.developer-text"/></p>
            <a class="btn btn-primary" href="?command=registration"><fmt:message bundle="${loc}"
                                                                                 key="local.call-to-reg3"/></a>
        </div>
    </div>
    <hr>

</div>

<%@ include file="../WEB-INF/jspf/footer.jspf" %>

</body>
</html>
