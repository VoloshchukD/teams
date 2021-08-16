<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <fmt:setLocale value="${local}" scope="session"/>
    <fmt:setBundle basename="local" var="loc"/>
    <title><fmt:message bundle="${loc}" key="local.reg"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="/js/validation.js"></script>
    <script type="text/javascript" src="/js/registration.js"></script>
    <link rel="stylesheet" href="/css/validation.css">
</head>
<body style="background-color: #D3D3D3;">
<div class="container-xxl">
    <div class="row h-100 justify-content-center">
        <div class="pt-4 text-center">
            <p class="frame">
                <img class="d-block mx-auto mb-3" src="/images/logo.png" alt="" width="100" height="100">
            </p>
            <h4>
                <fmt:message bundle="${loc}" key="local.join"/>
            </h4>
            <h2>
                <fmt:message bundle="${loc}" key="local.create"/>
            </h2>
        </div>
        <form method="post" action="controller" onSubmit="return validateSignUpForm()" novalidate>
            <input type="hidden" class="command" name="command" value="registrate" id="command"/>
            <div class="row">
                <div class="col-md-4">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="forename">
                                <fmt:message bundle="${loc}" key="local.form.forename"/>
                            </label>
                            <input type="text" class="form-control" name="forename" id="forename">
                            <small id="forename-help" class="form-text">
                                <fmt:message bundle="${loc}" key="local.form.forename-help"/>
                            </small>
                            <div id="regex-forename" class="hidden-regex">${regexForename}</div>
                        </div>
                        <div class="col-md-6">
                            <label for="surname">
                                <fmt:message bundle="${loc}" key="local.form.surname"/>
                            </label>
                            <input type="text" class="form-control" name="surname" id="surname">
                            <small id="surname-help" class="form-text">
                                <fmt:message bundle="${loc}" key="local.form.surname-help"/>
                            </small>
                            <div id="regex-surname" class="hidden-regex">${regexSurname}</div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="position">
                            <fmt:message bundle="${loc}" key="local.form.position"/>
                        </label>
                        <input type="text" class="form-control" name="position" id="position">
                        <small id="position-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.form.position-help"/>
                        </small>
                        <div id="regex-position" class="hidden-regex">${regexPosition}</div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <label for="experience">
                                <fmt:message bundle="${loc}" key="local.form.experience"/>
                            </label>
                            <input type="text" class="form-control" name="experience" id="experience">
                            <small id="experience-help" class="form-text">
                                <fmt:message bundle="${loc}" key="local.form.experience-help"/>
                            </small>
                            <div id="regex-experience" class="hidden-regex">${regexExperience}</div>
                        </div>
                        <div class="col-md-4">
                            <label for="salary">
                                <fmt:message bundle="${loc}" key="local.form.salary"/>
                            </label>
                            <input type="text" class="form-control" name="salary" id="salary">
                            <small id="salary-help" class="form-text">
                                <fmt:message bundle="${loc}" key="local.form.salary-help"/>
                            </small>
                            <div id="regex-salary" class="hidden-regex">${regexSalary}</div>
                        </div>
                        <div class="col-md-5 mb-3">
                            <label for="primary">
                                <fmt:message bundle="${loc}" key="local.form.primary"/>
                            </label>
                            <input type="text" class="form-control" name="primary" id="primary">
                            <small id="primary-help" class="form-text">
                                <fmt:message bundle="${loc}" key="local.form.primary-help"/>
                            </small>
                            <div id="regex-primary" class="hidden-regex">${regexPrimary}</div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
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
                    <hr class="mb-4">
                    <div class="d-flex justify-content-center">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">
                            <fmt:message bundle="${loc}" key="local.signup"/>
                        </button>
                    </div>
                    <div class="mt-3 text-center">
                        <fmt:message bundle="${loc}" key="local.form.have-account"/>
                        <a href="?command=authorization">
                            <fmt:message bundle="${loc}" key="local.signin"/>
                        </a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="mb-3">
                        <label for="company">
                            <fmt:message bundle="${loc}" key="local.form.company"/>
                        </label>
                        <input type="text" class="form-control" name="company" id="company">
                        <small id="company-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.form.company-help"/>
                        </small>
                        <div id="regex-company" class="hidden-regex">${regexCompany}</div>
                    </div>
                    <div class="mb-3">
                        <label for="role">
                            <fmt:message bundle="${loc}" key="local.form.role"/>
                        </label>
                        <select class="form-select" aria-label="Default select example"
                                name="role" id="role">
                            <option value="MANAGER">
                                <fmt:message bundle="${loc}" key="local.form.manager"/>
                            </option>
                            <option value="DEVELOPER">
                                <fmt:message bundle="${loc}" key="local.form.developer"/>
                            </option>
                            <option value="CUSTOMER">
                                <fmt:message bundle="${loc}" key="local.form.customer"/>
                            </option>
                        </select>
                    </div>
                    <div class="mt-3">
                        <label for="skills">
                            <fmt:message bundle="${loc}" key="local.form.skills"/>
                        </label>
                        <textarea class="form-control" aria-label="With textarea" name="skills"
                                  id="skills" style="height: 100px;"></textarea>
                        <small id="skills-description-help" class="form-text">
                            <fmt:message bundle="${loc}" key="local.form.skills-help"/>
                        </small>
                        <div id="regex-skills" class="hidden-regex">${regexSkills}</div>
                    </div>
                </div>
            </div>
        </form>
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
