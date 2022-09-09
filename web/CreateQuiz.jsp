<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Log in</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<body>
<form action="CreateQuizServlet" method="post">
    <section class="vh-100" style="background-color: #eee;">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-lg-12 col-xl-11">
                    <div class="card text-black" style="border-radius: 25px;">
                        <div class="card-body p-md-5">
                            <div class="row justify-content-center">
                                <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">


                                    <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Create Quiz</p>

                                    <h6 class="registration-error" style="color:darkred"><c:out
                                            value="${QuizErrorMessage}"/></h6>

                                    <form class="mx-1 mx-md-4">

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label for="quizName" class="form-label">Name of the Quiz</label>
                                                <input id="quizName" type="text" name="quizName" class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label fw-bold"> Choose whether the questions should
                                                    be
                                                    randomized or in fixed order </label>
                                                <div class="form-check">
                                                    <input id="randomQuestionsBox" class="form-check-input"
                                                           type="checkbox" value="hehe" name="randomQuestionsBox">
                                                    <label class="form-check-label" for="randomQuestionsBox">
                                                        Random Questions </label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label fw-bold"> Choose whether you want the quiz to
                                                    show correct answers immediately or at the end of the quiz </label>
                                                <div class="form-check">
                                                    <input id="immediateCorrectionsBox" class="form-check-input"
                                                           type="checkbox" value="true" name="immediateCorrectionsBox">
                                                    <label class="form-check-label" for="immediateCorrectionsBox">
                                                        Immediate Corrections </label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <%--                                            <div class="form-outline flex-fill">--%>
                                            <label class="form-label fw-bold"> Choose whether you want the questions
                                                to be displayed on a single page or multiple pages </label>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="onePageButton"
                                                       id="onePageButton">
                                                <label class="form-check-label" for="onePageButton"
                                                       style="margin-right: 10px !important"> One Page </label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio"
                                                       name="multiplePageButton"
                                                       id="multiplePageButton" checked>
                                                <label class="form-check-label" for="multiplePageButton"> Multiple
                                                    Pages </label>
                                            </div>
                                            <%--                                            </div>--%>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label fw-bold"> Choose whether the quiz should have a
                                                    practice mode </label>
                                                <div class="form-check">
                                                    <input id="practiceModeBox" class="form-check-input"
                                                           type="checkbox" value="true" name="practiceModeBox">
                                                    <label class="form-check-label" for="practiceModeBox">
                                                        Practice Mode </label>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <label class="form-label"> How many questions should the quiz to
                                                    have?</label>
                                                <input id="numberOfQuestions" type="text" name="numberOfQuestions"
                                                       class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                            <button type="submit" class="btn btn-primary btn-lg">Create</button>
                                        </div>

                                    </form>

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


