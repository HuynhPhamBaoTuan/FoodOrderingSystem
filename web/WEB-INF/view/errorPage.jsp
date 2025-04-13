<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
    <div class="container mt-5">
        <h1>Error</h1>
        <p>${errorMessage}</p>
        <a href="discountManage" class="btn btn-primary">Go Back</a>
    </div>
</body>
</html>
