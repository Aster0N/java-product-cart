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
    .wrapper {
        width: 1000px;
        margin: 0 auto;
    }
    .create-product {
        margin: 0 0 25px 0;
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
            <button type="submit">create</button>
        </form>

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
    </div>
</body>
</html>