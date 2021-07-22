<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <title><fmt:message bundle="${loc}" key="local.profile"/></title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/about.css">

    <link rel="stylesheet" href="http://cdn.webix.com/edge/webix.css" type="text/css">
    <script src="http://cdn.webix.com/edge/webix.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="/js/profile.js"></script>
    <link rel="stylesheet" href="/css/avatar.css">
</head>
<body>
<div class="container-xxl">
    <div class="row h-auto justify-content-center mx-n5">
        <div class="col-5 my-auto px-5">
<%--            <div class="py-4 text-center">--%>
<%--                <p><img id="userImg" class="rounded d-block mx-auto" src="${userImg}" alt="" width="200" height="200"></p>--%>
<%--            </div>--%>

<%--            <form action="upload" enctype="multipart/form-data" method="post">--%>
<%--                <div class="d-flex justify-content-center">--%>
<%--                    <input class="form-control mb-3" name="fileName" id="input" type="text"/>--%>

<%--                    <button class="btn btn-sm btn-primary btn-block mb-3 w-10" type="button">--%>
<%--                        <input type="file" id="upload" name="content" hidden/>--%>
<%--                        <label for="upload"><fmt:message bundle="${loc}" key="local.profile.attach"/></label>--%>
<%--                    </button>--%>
<%--                </div>--%>
<%--                <div class="d-flex justify-content-center">--%>
<%--                    <button class="btn btn-lg btn-primary btn-block mb-3" type="submit"><fmt:message bundle="${loc}" key="local.profile.upload"/></button>--%>
<%--                </div>--%>
<%--            </form>--%>
    ${userImg}
    <div class="row w-auto h-auto justify-content-center my-3">
        <input type="hidden" name="uploadButtonName" value="<fmt:message bundle="${loc}" key="local.profile.upload"/>" id="uploadButtonName"/>
        <input type="hidden" name="userImageUrl" value="${userImg}" id="userImageUrl"/>
        <div id="box" class="justify-content-center" ></div>
    </div>



            <hr class="mb-4">

            <form method="post" action="controller">
                <input type="hidden" name="command" value="update"/>

                <div class="mb-3">
                    <label for="email"><fmt:message bundle="${loc}" key="local.form.email"/></label>
                    <input type="email" class="form-control" pattern="${regexEmail}" name="email" id="email"
                           placeholder="" required>
                </div>

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

            </form>
        </div>

        <div class="col-5 my-auto px-5">
            <form method="post" action="controller">
                <input type="hidden" name="command" value="registrate" id="command"/>
                <div class="mb-3">
                    <label for="password"><fmt:message bundle="${loc}" key="local.form.password"/> </label>
                    <input type="password" class="form-control" pattern="${regexPassword}" name="password" id="password"
                           placeholder="" required>
                </div>
                <div class="mb-3">
                    <label for="password"><fmt:message bundle="${loc}" key="local.form.password"/> </label>
                    <input type="password" class="form-control" pattern="${regexPassword}" name="password"
                           id="password2"
                           placeholder="" required>
                </div>
                <div class="d-flex justify-content-center">
                    <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${loc}"
                                                                                                key="local.signup"/></button>
                </div>
            </form>

            <hr class="mb-4">

            <form method="post" action="controller">
                <input type="hidden" name="command" value="registrate"/>
                <select class="form-select mb-3" aria-label="Default select example">
                    <option selected>Open this select menu</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>
                <div class="d-flex justify-content-center">
                    <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message bundle="${loc}"
                                                                                                key="local.signup"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
</body>
</html>
