<%-- importing jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>home</title>
    <style>
    body, html {
        padding: 0;
        margin: 0;
        font-size: 18px;
        font-family: sans-serif;
    }
    .cards-wrapper {
        display: flex;
        justify-content: center;
        flex-wrap: wrap;
        gap: 10px;
    }
    .card {
        width: 300px;
        padding: 10px;
        border: 1px solid grey;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }
    .product-image {
        height: 180px;
        display: block;
        margin: 0 auto;
    }
    </style>
</head>
<body>
    <h1>Home page</h1>
    <div class="cards-wrapper">
        <c:forEach var="product" items="${productList}">
           <div class="card">
                <div class="card-info">
                    <h2>${product.getName()}</h2>
                    <p>${product.getDescription()}</p>
                </div>
                <div class="card-price">
                    <p><strong>Price: </strong>${product.getPrice()}</p>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>