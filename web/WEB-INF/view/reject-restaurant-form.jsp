<%-- 
    Document   : reject-restaurant-form
    Created on : Oct 19, 2024, 12:28:42 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reject Form</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .form-container {
                background-color: #fff;
                padding: 20px;
                padding-right: 40px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                width: 400px;
                text-align: center;
            }

            h5 {
                font-size: 18px;
                color: #333;
                margin-bottom: 20px;
            }

            textarea {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 14px;
                margin-bottom: 20px;
                margin-right: 20px;
                resize: none;
            }

            input[type="submit"] {
                background-color: #28a745;
                color: #fff;
                border: none;
                padding: 10px 20px;
                font-size: 16px;
                border-radius: 5px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #218838;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h5>The Reason to Reject</h5>
            <form action="admin-register-restaurant" method="GET">
                <input type="hidden" name="action" value="reject-register" />
                <input type="hidden" name="shopId" value="${shopId}" readonly/><br>
                <textarea placeholder="Why is the shop rejected?" class="form-control" id="reason" name="reason" rows="4" cols="50" required></textarea>
                <input type="submit" value="Submit"/>
            </form>
        </div>
    </body>
</html>
