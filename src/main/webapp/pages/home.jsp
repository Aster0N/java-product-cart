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
        background-color: #2d3436;
        color: #ecf0f1;
    }
    .wrapper {
        width: 1000px;
        margin: 0 auto;
    }
    .create-product {
        margin: 0 0 25px 0;
    }
    input {
        padding: 8px;
        margin: 0 0 10px 0;
    }
    .create-btn {
        padding: 5px;
        background-color: transparent;
        border: 1px solid #ecf0f1;
        color: #ecf0f1;
        outline: none;
        transition: all .2s ease-in-out;
        cursor: pointer;
        margin: 10px 0 0 0;
    }
    .create-btn:hover {
        background-color: #ecf0f1;
        color: #2d3436;
    }
    .cards-wrapper {
        display: flex;
        flex-wrap: wrap;
        gap: 10px;
        justify-content: space-between;
        flex-direction: row-reverse;
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
    }
    .save-to-cart-btn {
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
    .save-to-cart-btn:hover {
        background-color: #ecf0f1;
        color: #2d3436;
    }
    .card:hover .save-to-cart-btn {
        opacity: 1;
        pointer-events: all;
    }
    </style>
</head>
<body>
    <div class="wrapper">
        <h1>Home page</h1>
        <form class="create-product" method="post">
            <label for="name">
                Product name:<br>
                <input type="text" placeholder="name" id="name" name="name">
            </label><br>
            <label for="description">
                Product description:<br>
                <input type="text" placeholder="description" id="description" name="description">
            </label><br>
            <label for="price">
                Product price:<br>
                <input type="number" step="0.01" placeholder="price" id="price" name="price">
            </label><br>
            <button class="create-btn">create</button>
        </form>

        <div class="cards-wrapper">
            <c:forEach var="product" items="${productList}">
                <div class="card">
                    <button class="save-to-cart-btn">save</button>
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