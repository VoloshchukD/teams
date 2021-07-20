<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <fmt:setLocale value="${local}" scope="session"/>
    <fmt:setBundle basename="local" var="loc"/>
    <title><fmt:message bundle="${loc}" key="local.reg"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body style="background-color: #D3D3D3;">
<div class="container-xxl">
    <div class="row h-100 justify-content-center">
        <div class="col-4 my-auto">
            <div class="py-4 text-center">
                <p class="frame">
                    <img class="d-block mx-auto mb-3" src="/images/logo.png" alt="" width="100" height="100">
                </p>
                <h4><fmt:message bundle="${loc}" key="local.join"/></h4>
                <h2><fmt:message bundle="${loc}" key="local.create"/></h2>
            </div>
            <form method="post" action="controller">
                <input type="hidden" name="command" value="registrate" id="command"/>

                <div class="mb-3">
                    <label for="email"><fmt:message bundle="${loc}" key="local.form.email"/></label>
                    <input type="email" class="form-control" pattern="${regexEmail}" name="email" id="email"
                           placeholder="" required>
                </div>

                <div class="mb-3">
                    <label for="password"><fmt:message bundle="${loc}" key="local.form.password"/> </label>
                    <input type="password" class="form-control" pattern="${regexPassword}" name="password" id="password"
                           placeholder="" required>
                </div>

                <hr class="mb-4">

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="firstName"><fmt:message bundle="${loc}" key="local.form.first"/></label>
                        <input type="text" class="form-control" pattern="${regexFirst}" name="firstName" id="firstName"
                               placeholder="" value=""
                               required>

                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="lastName"><fmt:message bundle="${loc}" key="local.form.last"/></label>
                        <input type="text" class="form-control" pattern="${regexLast}" name="lastName" id="lastName"
                               placeholder="" value=""
                               required>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="company"><fmt:message bundle="${loc}" key="local.form.company"/></label>
                    <input type="text" class="form-control" pattern="${regexCompany}" name="company" id="company"
                           placeholder="" required>
                </div>

                <div class="mb-3">
                    <label for="position"><fmt:message bundle="${loc}" key="local.form.position"/></label>
                    <input type="text" class="form-control" pattern="${regexPosition}" name="position" id="position"
                           placeholder="" required>
                </div>

                <div class="mb-3">
                    <label for="role"><fmt:message bundle="${loc}" key="local.form.role"/></label>
                    <select class="form-select" aria-label="Default select example" name="role" id="role">
                        <option value="manager"><fmt:message bundle="${loc}" key="local.form.manager"/></option>
                        <option value="developer"><fmt:message bundle="${loc}" key="local.form.developer"/></option>
                        <option value="customer"><fmt:message bundle="${loc}" key="local.form.customer"/></option>
                    </select>

                </div>

                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="experience"><fmt:message bundle="${loc}" key="local.form.experience"/></label>
                        <input type="text" class="form-control" pattern="${regexExperience}" name="experience"
                               id="experience" placeholder=""
                               value="" required>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="salary"><fmt:message bundle="${loc}" key="local.form.salary"/></label>
                        <input type="text" class="form-control" pattern="${regexSalary}" name="salary" id="salary"
                               placeholder="" value=""
                               required>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="primary-skill"><fmt:message bundle="${loc}" key="local.form.primary"/></label>
                        <input type="text" class="form-control" pattern="${regexPrimary}" name="primary-skill"
                               id="primary-skill" placeholder=""
                               value="" required>
                        <div class="invalid-feedback">
                            Valid last name is required.
                        </div>
                    </div>
                </div>

                <div class="row">

                    <div class="col mb-3">
                        <label for="skills-description"><fmt:message bundle="${loc}"
                                                                     key="local.form.skills"/></label>
                        <textarea class="form-control" aria-label="With textarea" name="skills-description"
                                  id="skills-description"></textarea>
                    </div>

                </div>

                <div class="d-flex justify-content-center">
                    <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${loc}"
                                                                                                key="local.signup"/></button>
                </div>
                <div class="mt-3 text-center">
                    <fmt:message bundle="${loc}" key="local.form.have-account"/> <a
                        href="?command=authorization"><fmt:message bundle="${loc}" key="local.signin"/></a>
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
