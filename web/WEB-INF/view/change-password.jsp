<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Change User Password</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="./assets/css/verifyOtp.css">
    </head>
    <body>



        <section class="vh-100 bg-image" style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
            <div class="mask d-flex align-items-center h-100 gradient-custom-3">
                <div class="container h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                            <div class="card" style="border-radius: 15px;">
                                <div class="card-body p-5">
                                    <h2 class="text-uppercase text-center mb-5">Change Password</h2>


                                    <form action="changePassword" method="get">
                                        <input type="hidden" name="action" value="updatePass">

                                        <!-- Current -->
                                        <div class="form-group mb-3">
                                            <label for="currentPassword">Current password:</label>
                                            <input
                                                type="password"
                                                id="currentPassword"
                                                name="currentPassword"
                                                class="form-control"

                                                required
                                                />
                                        </div>

                                        <!-- New -->
                                        <div class="form-group mb-3">
                                            <label for="newPassword">New password:</label>
                                            <input
                                                type="password"
                                                id="newPassword"
                                                name="newPassword"
                                                class="form-control"

                                                />
                                        </div>

                                        <!-- Confirm -->
                                        <div class="form-group mb-3">
                                            <label for="confirmPassword">Confirm password:</label>
                                            <input
                                                type="password"
                                                id="confirmPassword"
                                                name="confirmPassword"
                                                class="form-control"
                                                required
                                                />
                                        </div>




                                        <div style="display: flex; justify-content: space-between">
                                            <button type="submit" class="btn btn-primary">Update</button>
                                            <a
                                                href="/OrderingSystem/account"
                                                style="
                                                margin: 0 0 0 10px;
                                                padding: 10px;
                                                border: 1px solid #ffff;
                                                background-color: #f58686;
                                                color: white;
                                                border-radius: 5px;
                                                justify-content: center;
                                                display: flex;
                                                align-items: center;
                                                cursor: pointer;
                                                text-decoration: none;
                                                "
                                                >
                                                Back to Profile
                                            </a>
                                        </div>
                                        <c:if test="${not empty error}">
                                            <p style="color:red">${error}</p>
                                        </c:if>
                                        <c:if test="${not empty success}">
                                            <p style="color:green">${success}</p>
                                        </c:if>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>



    </div>
</div>






</body>
</html>