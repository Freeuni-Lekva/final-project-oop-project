<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Log in</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<body>
<form action="MultipleChoiceQuestion" method="post">
    <section class="vh-100" style="background-color: #eee;">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-lg-12 col-xl-11">
                    <div class="card text-black" style="border-radius: 25px;">
                        <div class="card-body p-md-5">
                            <div class="row justify-content-center">
                                <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">


                                    <p class="text-center h3 mb-5 mx-1 mx-md-4 mt-4"> Adding Multiple Choice Question: </p>

                                    <p class=" h6 mb-3 mx-1 mx-md-4 mt-2"> You may add up to 4 Answers, you may choose to omit the answers. </p>

                                    <h6 class="registration-error h6 mb-3 mx-1 mx-md-4 mt-2" style="color:darkred"><c:out value="${ErrorMessage}"/></h6>

                                    <div class="mx-1 mx-md-4">

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label for="question" class="form-label">Question</label>
                                                <input id="question" type="text" name="question" class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label for="answer1" class="form-label">First Answer</label>
                                                <input id="answer1" type="text" name="answer1" class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label for="answer2" class="form-label">Second Answer</label>
                                                <input id="answer2" type="text" name="answer2" class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label for="answer3" class="form-label">Third Answer</label>
                                                <input id="answer3" type="text" name="answer3" class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label for="correctAnswer" class="form-label">Correct Answer</label>
                                                <input id="correctAnswer" type="text" name="correctAnswer" class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4"
                                             style="margin-top :50px!important;">
                                            <a class="btn btn-primary btn-lg" href="Questions.jsp">Go Back</a>
                                            <button type="submit" class="btn btn-primary btn-lg" style="margin-left:40px">Add Question
                                            </button>
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
</form>
</body>
</html>


