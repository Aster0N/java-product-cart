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
    <title>order history</title>
    <style>
        @media screen and (min-width: 1101px) {
            .wrapper {
                width: 1000px;
                padding: 0 0 40px 0;
                margin: 0 auto;
            }
        }
        @media screen and (max-width: 1100px) {
            .wrapper {
                width: 800px;
                padding: 0 0 20px 0;
                margin: 0 auto;
            }
        }
        @media screen and (max-width: 850px) {
            .wrapper {
                width: 100%;
                padding: 0 30px;
                margin: 0;
            }
        }
        * {
            box-sizing: border-box;
        }
        a {
            color: #74b9ff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        body, html {
            padding: 0;
            margin: 0;
            font-size: 18px;
            font-family: sans-serif;
            background-color: #2d3436;
            color: #ecf0f1;
        }
        .cards-wrapper {
            margin: 10px 0 0 0;
        }
        .card {
            width: 100%;
            padding: 10px;
            border: 1px solid grey;
            display: flex;
            gap: 15px;
            margin: 0 0 15px 0;
            justify-content: space-between;
            position: relative;
            transition: all .3s ease-out;
        }
        .card:hover {
            border: 1px solid #ffffff;
        }
        .card-description {
            text-overflow: ellipsis;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <h2>Order history</h2>
    <a href="/app/">Back</a>
    <div class="cards-wrapper">
        <c:forEach var="product" items="${orderHistory}">
            <div class="card">
                <div class="card-info">
                    <h2>${product.getName()}</h2>
                    <p class="card-description">${product.getDescription()}</p>
                </div>
                <div class="card-price">
                    <p><strong>Price: </strong>${product.getPrice()}</p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>