<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="../WEB-INF/jspf/header.jspf" %>
    <title><fmt:message bundle="${loc}" key="local.technical-task.creation"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/base.css">
    <link rel="stylesheet" href="http://cdn.webix.com/edge/webix.css" type="text/css">
    <script src="http://cdn.webix.com/edge/webix.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="/js/technical_task_creation.js"></script>
</head>
<body>
<div class="container-xxl">
    <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">New task</h5>
                </div>
                <div class="modal-body">
                    <form>

                        <label for="name" class="col-form-label">Name</label>
                        <input type="text" class="form-control" id="name">


                        <label for="description" class="col-form-label">Description</label>
                        <textarea class="form-control" id="description"></textarea>

                        <label for="employee" class="col-form-label">Executor</label>
                        <select class="form-select" aria-label="Default select example" id="employee">
                            <option value="null" selected>Assign to</option>
                            <option>name</option>
                        </select>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-primary" id="create-task-button" >Add</button>
                </div>
            </div>
        </div>
    </div>

    <div class="row d-flex justify-content-center m-3">
        <div class="col-4  text-center">
            <div class="card">
                <div class="card-body">
                    <button type="button" class="btn btn-primary mt-1 save" data-toggle="modal" data-target="#modal"
                            id="create-task-modal-button">Create task
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="row m-3">
        <div class="col-4 text-center">
            <div class="card">
                <div class="card-body">
                    <h2>To do
                    </h2>
                </div>
            </div>
        </div>
        <div class="col-4 text-center">
            <div class="card">
                <div class="card-body">
                    <h2>In progress
                    </h2>
                </div>
            </div>
        </div>
        <div class="col-4 text-center">
            <div class="card">
                <div class="card-body">
                    <h2>Done</h2>
                </div>
            </div>
        </div>
    </div>

    <div class="row justify-content-center h-100 m-3">
        <div class="col-4" id="col1">

            <div class="card border-dark    mb-3 mx-5">
                <div class="card-header bg-transparent border-dark   ">Header

                </div>
                <div class="card-body text-dark   ">
                    <h5 class="card-title">1</h5>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                        card's content.</p>

                    <div class="d-flex justify-content-between my-auto">
                        <div>
                            <button type="button" class="btn btn-primary track">Track</button>
                        </div>
                        <div>
                            <button type="button" class="btn btn-outline-dark hours" disabled><small
                                    class="val">7</small> Hours
                            </button>
                            <div class="panel" style="display:none;">
                                <input type="text" class="input align form-control mx-1"
                                       style="width: 50px; margin-left: auto; margin-right: auto;"/>
                                <button type="button" class="btn btn-outline-dark mt-1 save">Save</button>
                            </div>
                        </div>
                        <div>
                            <a class="nav-link" href="#" role="button">
                                <img src="https://images.unsplash.com/photo-1606044466411-207a9a49711f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1950&q=80"
                                     class="rounded-circle" height="22" width="22"/>
                            </a>
                        </div>
                    </div>
                    <div class=" d-flex justify-content-end">
                        <small class="form-text">Name Name</small>
                    </div>
                </div>
                <div class="card-footer bg-transparent border-dark d-flex justify-content-between">
                    <button type="button" class="btn btn-light back"><i class="fa fa-arrow-left" aria-hidden="true"></i>
                    </button>
                    <button type="button" class="btn btn-light forward"><i class="fa fa-arrow-right"
                                                                           aria-hidden="true"></i></button>
                </div>
            </div>

        </div>
        <div class="col-4" id="col2">

            <div class="card border-dark mb-3 mx-5">
                <div class="card-header bg-transparent border-dark">Header

                </div>
                <div class="card-body text-dark">
                    <h5 class="card-title">2</h5>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                        card's content.</p>

                    <div class="d-flex justify-content-between my-auto">
                        <div>
                            <button type="button" class="btn btn-primary track">Track</button>
                        </div>
                        <div>
                            <button type="button" class="btn btn-outline-dark hours" disabled><small
                                    class="val">7</small> Hours
                            </button>
                            <div class="panel" style="display:none;">
                                <input type="text" class="input align form-control mx-1"
                                       style="width: 50px; margin-left: auto; margin-right: auto;"/>
                                <button type="button" class="btn btn-outline-dark mt-1 save">Save</button>
                            </div>
                        </div>
                        <div>
                            <a class="nav-link" href="#" role="button">
                                <img src="https://images.unsplash.com/photo-1606044466411-207a9a49711f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1950&q=80"
                                     class="rounded-circle" height="22" width="22"/>
                            </a>
                        </div>
                    </div>
                    <div class=" d-flex justify-content-end">
                        <small class="form-text">Name Name</small>
                    </div>
                </div>
                <div class="card-footer bg-transparent border-dark d-flex justify-content-between">
                    <button type="button" class="btn btn-light back"><i class="fa fa-arrow-left" aria-hidden="true"></i>
                    </button>
                    <button type="button" class="btn btn-light forward"><i class="fa fa-arrow-right"
                                                                           aria-hidden="true"></i></button>
                </div>
            </div>

        </div>
        <div class="col-4" id="col3">

            <div class="card border-dark mb-3 mx-5">
                <div class="card-header bg-transparent border-dark">Header

                </div>
                <div class="card-body text-dark">
                    <h5 class="card-title">3</h5>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                        card's content.</p>

                    <div class="d-flex justify-content-between my-auto">
                        <div>
                            <button type="button" class="btn btn-primary track">Track</button>
                        </div>
                        <div>
                            <button type="button" class="btn btn-outline-dark hours" disabled><small
                                    class="val">7</small> Hours
                            </button>
                            <div class="panel" style="display:none;">
                                <input type="text" class="input align form-control mx-1"
                                       style="width: 50px; margin-left: auto; margin-right: auto;"/>
                                <button type="button" class="btn btn-outline-dark mt-1 save">Save</button>
                            </div>
                        </div>
                        <div>
                            <a class="nav-link" href="#" role="button">
                                <img src="https://images.unsplash.com/photo-1606044466411-207a9a49711f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1950&q=80"
                                     class="rounded-circle" height="22" width="22"/>
                            </a>
                        </div>
                    </div>
                    <div class=" d-flex justify-content-end">
                        <small class="form-text">Name Name</small>
                    </div>
                </div>
                <div class="card-footer bg-transparent border-success d-flex justify-content-between">
                    <button type="button" class="btn btn-light back"><i class="fa fa-arrow-left" aria-hidden="true"></i>
                    </button>
                    <button type="button" class="btn btn-light forward"><i class="fa fa-arrow-right"
                                                                           aria-hidden="true"></i></button>
                </div>
            </div>

        </div>
    </div>

</div>
<%@ include file="../WEB-INF/jspf/footer.jspf" %>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script type="text/javascript" src="/js/tasks.js"></script>
</body>
</html>

