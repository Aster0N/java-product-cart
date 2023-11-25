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
    <title>Home</title>
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
        margin: 10px 0 0 0;
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
        transition: all .3s ease-out;
    }
    .card:hover {
        border: 1px solid #74b9ff;
    }
    .add-to-favorite-btn {
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
    .add-to-favorite-btn:hover {
        background-color: #ecf0f1;
        color: #2d3436;
    }
    .add-to-favorite-btn.add-favorite-btn-active {
        background-color: #ecf0f1;
        color: #2d3436;
        opacity: 1;
        pointer-events: all;
    }
    .card:hover .add-to-favorite-btn {
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

        <a href="/app/favorite">Favorite products</a>

        <div class="cards-wrapper">
            <c:forEach var="product" items="${productList}">
                <div class="card">
                    <form method="post" action="/app/?add-to-favorite=${product.getUId()}">
                        <button type="submit"
                                class="add-to-favorite-btn <c:if test="${product.getIsFavorite()}">add-favorite-btn-active</c:if>"
                        >
                            <c:if test="${product.getIsFavorite()}">favorite</c:if>
                            <c:if test="${not product.getIsFavorite()}">add to favorite</c:if>
                        </button>
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