<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored = "false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>
        Product Info
    </title>
</head>
<body>
<h2>Product Info Page!</h2>
<div>
    <hr/>
    <a href="/">Go to Home</a>
    <hr/>
    <hr/>
    <a href="/main">Go to Main</a>
    <hr/>
</div>

<!----------   SHOW INFO  ---------->
<div>
    <form>
        <a>
            ${sessionScope.message}
        </a>
    </form>
</div>
<c:if test="${not empty requestScope.product}">
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>${brand_id}</th>
                <th>${model}</th>
                <th>${price}</th>
                <th>${quantity}</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${requestScope.product.brand_id}</td>
                <td>${requestScope.product.model}</td>
                <td>${requestScope.product.price}</td>
                <td>${requestScope.product.quantity}</td>
            </tr>
            </tbody>
        </table>
    </div>
</c:if>

<!----------   DELETE (ADMIN) ---------->
<c:if test="${sessionScope.role != null}">
    <div>
        <form action="/main" id="delete_product" method="POST">
            <input name="command" type="hidden" value="delete_product"/>
            <input name="productId" type="hidden" value="${requestScope.product.id}"/>
            <button class="btn btn-primary" form="delete_product" type="submit">Delete Product</button>
        </form>
    </div>
    </br>
    <!----------   UPDATE (ADMIN)  ---------->
    <div class="main-block">
        <form action="/product_info" class="form-group" id="update_product" method="POST">
            <input name="productId" type="hidden" value="${product.id}"/>
            <input name="command" type="hidden" value="update_product"/>
            <div class="form-group col-md-6">
                <input class="form-control" name="brand_id" placeholder="${brand_id}" type="text"/>
            </div>
            <div class="form-group col-md-6">
                <input class="form-control" name="model" placeholder="${model}" type="text"/>
            </div>
            <div class="form-group col-md-6">
                <input class="form-control" name="price" placeholder="${price}" type="text"/>
            </div>
            <div class="form-group col-md-6">
                <input class="form-control" name="quantity" placeholder="${quantity}" type="text"/>
            </div>
            <div>
                <button class="btn btn-primary" form="update_product" type="submit">Update Product</button>
            </div>
        </form>
    </div>
</c:if>
</body>
</html>
