<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Log in</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<body>
<section class="vh-100" style="background-color: #eee;">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-11">
                <div class="card text-black" style="border-radius: 25px;">
                    <div class="card-body p-md-5">
                        <div class="row justify-content-center">
                            <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">


                                <p class="text-center h3 mb-4 mx-1 mx-md-4 mt-4"> Quiz: <c:out
                                        value="${quizName}"> </c:out> Created Successfully! </p>


                                <p class="text-center h4 mb-2 mx-1 mx-md-4 mt-2"> You may add another Quiz or go
                                    back to the Homepage </p>


                                <div class="mx-1 mx-md-4">


                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4"
                                         style="margin-top :50px!important;">
                                        <a class="btn btn-primary btn-lg" href="CreateQuiz.jsp">Create new Quiz</a>
                                        <a class="btn btn-primary btn-lg" style="margin-left:40px" href="Homepage.jsp">Homepage</a>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>


