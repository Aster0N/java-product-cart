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
    <title>Favorite</title>
    <style>
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
    <h2>Favorite products</h2>
    <c:forEach var="product" items="${favoriteProductList}">
        <div class="card">
            <form method="post" action="/app/?remove-from-favorite=${product.getUId()}">
                <button type="submit"
                        class="add-to-favorite-btn <c:if test="${product.getIsFavorite()}">add-favorite-btn-active</c:if>">
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
</body>
</html>