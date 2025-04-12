<%-- 
    Document   : require-password
    Created on : Sep 18, 2024, 11:29:42 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Set Password</title>
    <script>
        
        function validateForm() {
            var password = document.getElementById("password").value;
            if (password == "") {
                alert("Please enter your password.");
                return false; 
                
            }
            return true; // Cho phép submit nếu form đã được điền đầy đủ
        }


        window.onbeforeunload = function() {
            if (!document.getElementById("formSubmitted").value) {
                return "You have not submitted the form yet. Are you sure you want to leave?";
            }
        }


        function markFormSubmitted() {
            document.getElementById("formSubmitted").value = true;
        }
    </script>
    
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
                                <h2 class="text-uppercase text-center mb-5">Set Password</h2>
    <form action="login-google" method="POST" onsubmit="return validateForm(); markFormSubmitted();">
        <div class="form-group">
        <input type="hidden" id="formSubmitted" value="false" /><label for="password">UserName</label>
        <input type="text" id="username" name="username" required defaut = ""${username}"" placeholder="${username}"/><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required /><br><br>
        <input type="hidden" id="1" name="name" value ="${name}" />
        <input type="hidden" id="2" name="email" value ="${email}" />
        <input type="hidden" id="3" name="picture" value ="${picture}" />
        </div>
                                    <button type="submit" class="btn btn-primary mt-4">Submit</button>
                                </form>
                                <p class="text-danger text-center mt-3">${message}</p> <!-- Hiển thị thông báo lỗi nếu có -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>

