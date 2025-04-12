<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link href="assets/css/register-restaurant.css" rel="stylesheet" type="text/css"/>
        <title>Restaurant Register</title>
        <style>
            /* Custom style for full height */
            body, html {
                height: 100%;
            }
            .centered {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100%; /* Full height */
                
            }
            
            
        </style>
    </head>
    <body>
        <div class="container-fluid centered">
            <div class="card card-margin" style="width: 35rem;">
                <img src="${shop.shopImage}" class="card-img-top" alt="..." style="width: 100%; height: 250px; object-fit: cover;">


                <div class="card-body">
                    <h4 class="card-title" style="text-transform: uppercase; text-align: center"><b>${shop.name}</b></h4>
                    <p class="card-text">${shop.description}</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><b>Address:</b> ${shop.address}</li>
                    <li class="list-group-item">
                        <div class="d-flex justify-content-between">
                            <div><b>Time Open:</b> ${shop.timeOpen}</div>
                            <div><b>Time Close:</b> ${shop.timeClose}</div>
                        </div>
                    </li>
                    <li class="list-group-item"><b>Shop Owner: </b> ${owner.fullName}</li>
                    <li class="list-group-item"><b>Username: </b>${owner.userName}</li>
                    <li class="list-group-item"><b>Phone Number: </b>${owner.phoneNumber}</li>
                    <li class="list-group-item"><b>Email: </b>${owner.email}</li>
                </ul>
                <div class="card-body d-flex justify-content-center">
                    <a href="admin-register-restaurant" class="card-link btn btn-primary">Back</a>
                    <a href="admin-register-restaurant?action=approve-register&shopId=${shop.shopID}" class="card-link btn btn-success">Approve</a>
                    <a href="admin-register-restaurant?action=show-reject-form&shopId=${shop.shopID}" class="card-link btn btn-danger">Reject</a>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
