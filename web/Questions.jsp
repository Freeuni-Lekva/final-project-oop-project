<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Log in</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<body>
<form action="ForwardQuestionServlet" method="post">
    <section class="vh-100" style="background-color: #eee;">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-lg-12 col-xl-11">
                    <div class="card text-black" style="border-radius: 25px;">
                        <div class="card-body p-md-5">

                            <div class="row justify-content-center">
                                <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">


                                    <p class="text-center h1 fw-bold mb-1 mx-1 mx-md-4 mt-4">Generating Quiz: </p>
                                    <p class="text-center h2 mb-5 mx-1 mx-md-4 mt-2"><c:out value="${quizName}"/></p>

                                    <form class="mx-1 mx-md-4">

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label fw-bold"> Choose the type of Question you want
                                                    to create </label>
                                                <select class="form-select" aria-label="Default select example"
                                                        name="questionType">
                                                    <option selected value="standard">Question-Response</option>
                                                    <option value="blank">Fill in the Blank</option>
                                                    <option value="multiple">Multiple Choice</option>
                                                    <option value="picture">Picture-Response</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                            <button type="submit" class="btn btn-primary btn-lg">Create</button>
                                        </div>

                                    </form>

                                </div>
                            </div>
                            <div class="row justify-content-center">

                                Questions added: <c:out value="${questionCount}"/> / <c:out value="${totalQuestionCount}"></c:out>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</form>
</body>
</html>


