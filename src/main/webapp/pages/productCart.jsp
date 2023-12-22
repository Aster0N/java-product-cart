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
    <title>product cart</title>
    <style>
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
        .wrapper {
            width: 1000px;
            margin: 0 auto;
            padding: 0 0 40px 0;
        }
        .cards-wrapper {
            margin: 10px 0 0 0;
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            justify-content: flex-start;
        }
        .card {
            width: 300px;
            padding: 10px;
            border: 1px solid grey;
            display: flex;
            flex-direction: column;
            gap: 15px;
            justify-content: space-between;
            position: relative;
            transition: all .3s ease-out;
        }
        .card:hover {
            border: 1px solid #ffffff;
        }
        .remove-from-cart-btn {
            position: absolute;
            right: 5px;
            top: 5px;
            opacity: 0;
            pointer-events: none;
            user-select: none;
            transition: all .2s ease-out;
            cursor: pointer;
            background-color: transparent;
            border: 1px solid #ecf0f1;
            color: #ecf0f1;
            padding: 5px;
        }
        .remove-from-cart-btn:hover {
            background-color: #ecf0f1;
            color: #2d3436;
        }
        .card:hover .remove-from-cart-btn {
            opacity: 1;
            pointer-events: all;
        }
    </style>
</head>
<body>
    <div class="wrapper">
        <h2>Your product cart</h2>
        <a href="/app/">Back</a>
        <c:if test="${totalAmount > 0}">
            <h4>Total amount: ${totalAmount}$</h4>
        </c:if>
        <div class="cards-wrapper">
            <c:forEach var="product" items="${productCart}">
                <div class="card">
                    <form method="post" action="/app/product-cart?remove-from-cart=${product.getUId()}">
                        <button type="submit" class="remove-from-cart-btn">remove</button>
                    </form>
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
    </div>
</body>
</html>