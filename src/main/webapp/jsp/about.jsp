<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.about"/></title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/about.css">
</head>
<body>
<div class="container-xxl">

    <div id="carouselExampleIndicators" class="container-xxl carousel slide" data-interval="false">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <div class="d-block w-100 my-5">
                    <div class="text-white my-5"
                         style="height: 250px; padding: 30px; background-color: #020230; ">
                        <div class="text-center">
                            <h1 class="fw-bolder pt-5"><fmt:message bundle="${loc}" key="local.history"/></h1>
                            <p class="lead"><fmt:message bundle="${loc}" key="local.about.welcome"/></p>
                        </div>
                    </div>
                    <div class="main-timeline">
                        <div class="timeline">
                            <div href="" class="timeline-content">
                                <div class="timeline-year">
                                    <h3>2009</h3>
                                </div>
                                <h3 class="title"><fmt:message bundle="${loc}" key="local.about.header1"/></h3>
                                <p class="description"><fmt:message bundle="${loc}" key="local.about.description1"/></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <div class="d-block w-100 my-5">
                    <div class="main-timeline">
                        <div class="timeline">
                            <div href="" class="timeline-content">
                                <div class="timeline-year">
                                    <h3>2010</h3>
                                </div>
                                <h3 class="title"><fmt:message bundle="${loc}" key="local.about.header2"/></h3>
                                <p class="description"><fmt:message bundle="${loc}" key="local.about.description2"/></p>
                            </div>
                        </div>
                        <div class="timeline">
                            <div href="" class="timeline-content">
                                <div class="timeline-year">
                                    <h3>2011</h3>
                                </div>
                                <h3 class="title"><fmt:message bundle="${loc}" key="local.about.header3"/></h3>
                                <p class="description"><fmt:message bundle="${loc}" key="local.about.description3"/></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <div class="d-block w-100 my-5">
                    <div class="main-timeline">
                        <div class="timeline">
                            <div href="" class="timeline-content">
                                <div class="timeline-year">
                                    <h3>2013</h3>
                                </div>
                                <h3 class="title"><fmt:message bundle="${loc}" key="local.about.header4"/></h3>
                                <p class="description"><fmt:message bundle="${loc}" key="local.about.description4"/></p>
                            </div>
                        </div>
                        <div class="timeline">
                            <div href="" class="timeline-content">
                                <div class="timeline-year">
                                    <h3>2015</h3>
                                </div>
                                <h3 class="title"><fmt:message bundle="${loc}" key="local.about.header5"/></h3>
                                <p class="description"><fmt:message bundle="${loc}" key="local.about.description5"/></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <div class="d-block w-100 my-5">
                    <div class="main-timeline">
                        <div class="timeline">
                            <div href="" class="timeline-content">
                                <div class="timeline-year">
                                    <h3>2019</h3>
                                </div>
                                <h3 class="title"><fmt:message bundle="${loc}" key="local.about.header6"/></h3>
                                <p class="description"><fmt:message bundle="${loc}" key="local.about.description6"/></p>
                            </div>
                        </div>
                        <div class="timeline">
                            <div href="" class="timeline-content">
                                <div class="timeline-year">
                                    <h3>2020</h3>
                                </div>
                                <h3 class="title"><fmt:message bundle="${loc}" key="local.about.header7"/></h3>
                                <p class="description"><fmt:message bundle="${loc}" key="local.about.description7"/></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true" style="background-color: grey"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true" style="background-color: grey"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
