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
</head>
<body>
    <div class="wrapper">
        <h2>Your product cart</h2>
        <a href="/app/">Back</a>
        <div class="cards-wrapper">
            <h4>Total amount: ${totalAmount}</h4>
            <c:forEach var="product" items="${productCart}">
                <div class="card">
                    <form method="post" action="/app/favorite?remove-from-cart=${product.getUId()}">
                        <button type="submit" class="remove-from-cart">remove</button>
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