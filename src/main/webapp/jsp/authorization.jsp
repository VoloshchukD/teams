<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <fmt:setLocale value="${local}" scope="session"/>
    <fmt:setBundle basename="local" var="loc"/>
    <title><fmt:message bundle="${loc}" key="local.auth"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script type="text/javascript" src="/js/validation.js"></script>
    <script type="text/javascript" src="/js/authorization.js"></script>
    <link rel="stylesheet" href="/css/validation.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #D3D3D3;">
<div class="container-xxl">
    <div class="row h-100 justify-content-center">
        <div class="col-4 my-auto">
            <div class="py-4 text-center">
                <p class="frame">
                    <img class="d-block mx-auto mb-3" src="/images/logo.png"
                         alt="" width="100" height="100">
                </p>
                <h4>
                    <fmt:message bundle="${loc}" key="local.hello"/>
                </h4>
                <h2>
                    <fmt:message bundle="${loc}" key="local.signin.welcome"/>
                </h2>
            </div>
            <c:if test="${error}">
                <div class="alert alert-danger alert-dismissible" role="alert" id="error">
                    <strong>
                        <fmt:message bundle="${loc}" key="local.message.error.header"/>
                    </strong>
                    <fmt:message bundle="${loc}" key="local.message.authorization.error"/>
                    <button type="button" class="close" data-dismiss="alert">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <form method="POST" action="controller" onSubmit="return validateSignInForm()" novalidate>
                <input type="hidden" name="command" value="authorize" id="command"/>
                <div class="mb-3">
                    <label for="email">
                        <fmt:message bundle="${loc}" key="local.form.email"/>
                    </label>
                    <input type="email" class="form-control" name="email" id="email">
                    <small id="email-help" class="form-text">
                        <fmt:message bundle="${loc}" key="local.form.email-help"/>
                    </small>
                    <div id="regex-email" class="hidden-regex">${regexEmail}</div>
                </div>
                <div class="mb-3">
                    <label for="password">
                        <fmt:message bundle="${loc}" key="local.form.password"/>
                    </label>
                    <input type="password" class="form-control" name="password" id="password">
                    <small id="password-help" class="form-text">
                        <fmt:message bundle="${loc}" key="local.form.password-help"/>
                    </small>
                    <div id="regex-password" class="hidden-regex">${regexPassword}</div>
                </div>
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-lg btn-primary">
                        <fmt:message bundle="${loc}" key="local.signin"/>
                    </button>
                </div>
                <div class="mt-3 text-center">
                    <fmt:message bundle="${loc}" key="local.signin.new"/>
                    <a href="?command=registration">
                        <fmt:message bundle="${loc}" key="local.create"/>
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
        integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
        crossorigin="anonymous"></script>
</body>
</html>
