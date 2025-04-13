<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link href="assets/css/register-restaurant.css" rel="stylesheet" type="text/css"/>
        <title>Restaurant Register</title>
    </head>
    <body>
        <div class="wrapper rounded bg-white">

            <div class="h3 mb-4 ">Registration Form</div>

            <form action="register-restaurant?action=register" method="POST" enctype="multipart/form-data">
                <div class="row">
                    <div class="col-md-6 mt-md-0 mt-3">
                        <label>Restaurant Name</label>
                       <input type="text" class="form-control"  id="restaurant-name" name="restaurant-name" required>
                    </div>
                    <div class="col-md-6 mt-md-0 mt-3">
                        <label>Owner Full Name</label>
                        <input type="text" class="form-control"  id="fullname" name="fullname" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mt-md-0 mt-3">
                        <label>Restaurant Image</label>
                         <input type="file" name="image" id="image"  required/>
                    </div>
                    <div class="col-md-6 mt-md-0 mt-3">
                        <label>Username</label>
                        <input type="text" class="form-control"  id="username" name="username" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mt-md-0 mt-3">
                        <label>Address</label>
                        <input type="text" class="form-control"  id="address" name="address" required>
                    </div>
                    <div class="col-md-6 mt-md-0 mt-3">
                        <label>Password</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mt-md-0 mt-3">
                        <label>Time Open</label>
                         <input type="time" id="time-open" name="time-open" class="form-control"  required>
                    </div>
                    <div class="col-md-6 mt-md-0 mt-3">
                        <label>Phone Number</label>
                        <input type="text" class="form-control"  id="phonenumber" name="phonenumber" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mt-md-0 mt-3">
                        <label>Time Close</label>
                        <input type="time" id="time-close" name="time-close" class="form-control" required>
                    </div>
                    <div class="col-md-6 mt-md-0 mt-3">
                        <label>Email</label>
                         <input type="email" class="form-control"  id="email" name="email" required>
                    </div>
                </div>
                <div class=" my-md-2 my-3 ">
                    <label>Description</label>
                     <textarea class="form-control" id="description" name="description" rows="4" cols="50" required></textarea>
                </div>
                 
                   <p class="text-danger text-center mt-3">${message}</p>
                <div class="mt-2">
                    <a class="btn btn-secondary btn-margin" href="/OrderingSystem">Back to Homepage</a>
                    <button type="submit" class="btn btn-primary">Register</button>
                </div>
                
            </form>
        </div>

    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
