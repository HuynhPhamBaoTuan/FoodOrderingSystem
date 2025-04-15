<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Edit User</title>
        <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
    <link
        <link rel="stylesheet" href="./assets/css/updateUser.css">
  </head>

  <body>
    <div class="container mt-4 mb-4 p-3 d-flex justify-content-center">
      <div style="width: 50%" class="card p-4">
        <h3 class="mb-4">Edit User Information</h3>

        <c:if test="${not empty user}">
          <form action="editUser" method="post">
            <!-- UserID (Hidden Field) -->
            <input type="hidden" name="userId" value="${user.userID}" />

            <!-- Username -->
            <div class="form-group mb-3">
              <label for="username">Username:</label>
              <input
                type="text"
                id="username"
                name="username"
                class="form-control"
                value="${user.userName}"
                required
              />
            </div>

            <!-- Full Name -->
            <div class="form-group mb-3">
              <label for="fullName">Full Name:</label>
              <input
                type="text"
                id="fullName"
                name="fullName"
                class="form-control"
                value="${user.fullName}"
              />
            </div>

            <!-- Phone Number -->
            <div class="form-group mb-3">
              <label for="phoneNumber">Phone Number:</label>
              <input
                type="text"
                id="phoneNumber"
                name="phoneNumber"
                class="form-control"
                value="${user.phoneNumber}"
                pattern="0[0-9]{9}"
                required
              />
            </div>

            <!-- Email -->
            <div class="form-group mb-3">
              <label for="email">Email:</label>
              <div style="display: flex">
                <input
                  type="email"
                  id="email"
                  name="email"
                  class="form-control"
                  value="${user.email}"
                  required
                  style="width: 70%"
                />
<!--                <a
                  style="
                    width: 30%;
                    margin: 0 0 0 10px;
                    border: 1px solid #ffff;
                    background-color: #007bff;
                    color: white;
                    border-radius: 5px;
                    justify-content: center;
                    display: flex;
                    align-items: center;
                    cursor: pointer;
                  "
                >
                  Verify Email
                </a>-->
              </div>
            </div>

            <!-- Address -->
            <div class="form-group mb-3">
              <label for="address">Address:</label>
              <input
                type="text"
                id="address"
                name="address"
                class="form-control"
                value="${user.address}"
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
          </form>
        </c:if>

        <c:if test="${empty user}">
          <p>User not found!</p>
        </c:if>
      </div>
    </div>
  </body>
</html>
