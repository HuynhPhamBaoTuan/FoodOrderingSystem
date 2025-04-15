<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Login Page</title>

        <!-- Custom CSS -->
        <!--<link rel="stylesheet" href="./index.css">-->
        <link rel="stylesheet" href="./assets/css/login.css">
        <link rel="stylesheet" href="themify-icons-font/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
              crossorigin="anonymous"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    </head>
    <body>
        <section class="vh-100">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-4 text-black">
                        <div class="px-5 ms-xl-4">
                            <a style="text-decoration: none" href="/OrderingSystem">
                                <span  class="h1 fw-bold mb-0">FOODIE</span>
                            </a>
                        </div>

                        <div class="d-flex align-items-center h-custom-2 px-5 ms-xl-4 mt-5 pt-5 pt-xl-0 mt-xl-n5">
                            <form action="login" method="post" style="width: 23rem;">
                                <h3 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Log in</h3>

                                <div class="form-outline mb-4">
                                    <input type="text" id="form2Example18" class="form-control form-control-lg" name="user" placeholder="Username" required />
                                </div>

                                <div class="form-outline mb-4">
                                    <input type="password" id="form2Example28" class="form-control form-control-lg" name="pass" placeholder="Password" required />
                                </div>

                                <div class="pt-1 mb-4">
                                    <button class="btn btn-info btn-lg btn-block" type="submit">Login</button>
                                    <p class="small  pb-lg-2"><a class="text-mutedd" href="./forgot-password">Forgot password?</a></p>

                                </div>

                                <div class="social-container text-center mt-5">
                                    <p>or</p>
                                    <a href="https://accounts.google.com/o/oauth2/auth?scope=profile%20email&redirect_uri=http://localhost:8080/OrderingSystem/login-google&response_type=code
                               &client_id=953119864721-91spj7rint3v7cqejchkgqm3m63hgb1i.apps.googleusercontent.com&approval_prompt=force" class="social"><svg xmlns="http://www.w3.org/2000/svg"colour ="white" width="16" height="16" fill="white" class="bi bi-google" viewBox="0 0 16 16">
                                <path d="M15.545 6.558a9.4 9.4 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.7 7.7 0 0 1 5.352 2.082l-2.284 2.284A4.35 4.35 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.8 4.8 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.7 3.7 0 0 0 1.599-2.431H8v-3.08z"/>
                                </svg></i> Google</a>
                                </div>
                                <p style="margin-top: 50px; text-align: center">Don't have an account? <a href="./register" style="color:#5eae53" class="">Register here</a></p>
                                <p class="text-danger text-center mt-3">${message}</p>
                            </form>
                        </div>

                    </div>
                    <div style="background-image: url(./assets/img/sliderback.svg)" class="col-sm-8 px-0 d-none d-sm-block">
                        <!--<img src="./img/banner.jpg" alt="Login image" class="w-100 vh-100" style="object-fit: cover; object-position: left;">-->
                    </div>
                </div>
            </div>
        </section>
        <script src="index.js"></script>
    </body>
</html>
