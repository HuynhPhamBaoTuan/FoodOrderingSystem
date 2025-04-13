<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Forgot Password</title>
        <link rel="stylesheet" href="./index.css">
        <link rel="stylesheet" href="themify-icons-font\themify-icons\themify-icons.css">
    </head>
    <body>



        <div class="form-structor">
            <div class="signup">
                <h2 class="form-title" id="signup">Forgot Password</h2>
                <form action="forgot-password" method="post">
                    <div class="form-holder">
                        <!--<label for="otp" class="form-label">Enter email:</label>-->
                        <input type="email" class="input" placeholder="Enter email"id="email" name="email" required>
                    </div>
                    <button type ="submit" class="submit-btn">Next</button>
                </form>
            </div>
        </div>
        <p class="text-danger text-center mt-3">${message}</p> <!-- Hiển thị thông báo lỗi nếu có -->

    </body>
</html>
