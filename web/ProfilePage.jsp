<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
<link rel="stylesheet" href="test.css">

<body>
<div class="main-content">
    <!-- Top navbar -->
    <nav class="navbar navbar-top navbar-expand-md navbar-dark" id="navbar-main">
        <div class="container-fluid">
            <!-- Brand -->
            <div>
                <a class="h4 mb-0 mr-2 text-white text-uppercase d-none d-lg-inline-block" href=HomepageLoggedIn.jsp
                   target="_blank">Homepage</a>
                <a class="h4 mb-0 text-white text-uppercase d-none d-lg-inline-block" href=ProfilePage.jsp
                   target="_blank">User profile</a>
            </div>
            <!-- User -->
            <ul class="navbar-nav align-items-center d-none d-md-flex">
                <li class="nav-item dropdown">
                    <a class="nav-link pr-0" href="#" role="button" data-toggle="dropdown" aria-haspopup="true"
                       aria-expanded="false">
                        <div class="media align-items-center">
                <span class="avatar avatar-sm rounded-circle">
                  <img alt="Img"
                       src='<c:out value="${userImageUrl}"> </c:out>'>
                </span>
                            <div class="media-body ml-2 d-none d-lg-block">
                                <span class="mb-0 text-sm  font-weight-bold"><c:out value="${userFirstName} ${userLastName}"></c:out></span>
                            </div>
                        </div>
                    </a>

                </li>
            </ul>
        </div>
    </nav>
    <!-- Header -->
    <div class="header pb-8 pt-5 pt-lg-8 d-flex align-items-center"
         style="min-height: 600px; background-size: cover; background-position: center top;">
        <!-- Mask -->
        <span class="mask bg-gradient-default opacity-8"></span>
        <!-- Header container -->
        <div class="container-fluid d-flex align-items-center">
            <div class="row">
                <div class="col-lg-7 col-md-10">
                    <h1 class="display-2 text-white">Hello <c:out value="${username}"> </c:out></h1>
                </div>
            </div>
        </div>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
        <div class="row">
            <div class="col-xl-4 order-xl-2 mb-5 mb-xl-0">
                <div class="card card-profile shadow">
                    <div class="row justify-content-center">
                        <div class="col-lg-3 order-lg-2">
                            <div class="card-profile-image">
                                <a href="#">
                                    <img src='<c:out value="${userImageUrl}"> </c:out>'
                                         class="rounded-circle">
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4"></div>
                    <div class="card-body pt-0 pt-md-4">
                        <div class="row">
                            <div class="col">
                                <div class="card-profile-stats d-flex justify-content-center mt-md-5">
                                    <div>
                                        <span class="heading">0</span>
                                        <a class="description"
                                           href="https://www.creative-tim.com/product/argon-dashboard">Friends</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="text-center">
                            <h3>
                                <c:out value="${userFirstName} ${userLastName}"></c:out> <span
                                    class="font-weight-light"></span>
                            </h3>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-8 order-xl-1">
                <div class="card bg-secondary shadow">
                    <div class="card-header bg-white border-0">
                        <div class="row align-items-center">
                            <div class="col-8">
                                <h3 class="mb-0">My Account</h3>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <form action="ProfilePageUpdateServlet" method="post">
                            <h6 class="heading-small text-muted mb-4">User information</h6>
                            <div class="pl-lg-4">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="form-group focused">
                                            <label class="form-control-label" for="input-username">Username</label>
                                            <input type="text" id="input-username" name="username"
                                                   class="form-control form-control-alternative" readonly
                                                   placeholder="Username" value='<c:out value="${username}"> </c:out>'>
                                           Hehehe:  <c:out value="${userImageUrl}"> </c:out>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label class="form-control-label" for="input-email">Email address</label>
                                            <input type="email" id="input-email" name="email"
                                                   class="form-control form-control-alternative" placeholder="email"
                                                   value='<c:out value="${userEmail}"> </c:out>'>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="form-group focused">
                                            <label class="form-control-label" for="input-first-name">First name</label>
                                            <input type="text" id="input-first-name" name="firstName"
                                                   class="form-control form-control-alternative"
                                                   placeholder="First name"
                                                   value='<c:out value="${userFirstName}"> </c:out>'>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group focused">
                                            <label class="form-control-label" for="input-last-name">Last name</label>
                                            <input type="text" id="input-last-name" name="lastName"
                                                   class="form-control form-control-alternative" placeholder="Last name"
                                                   value='<c:out value="${userLastName}"> </c:out>'>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="form-group focused">
                                            <label class="form-control-label" for="input-first-name">Image URL</label>
                                            <input type="text" id="input-image-url" name="imageUrl"
                                                   class="form-control form-control-alternative" placeholder="Image URL"
                                                   value='<c:out value="${imageURL}"> </c:out>'>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="col-4 text-left" style="margin-left:20px">
                                <button class="btn btn-sm btn-primary">Update</button>
                            </div>
                            <h6 class="registration-error h4 mt-3 ml-3" style="color:darkred; margin-left:22px"><c:out value="${ErrorMessage}"/></h6>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>