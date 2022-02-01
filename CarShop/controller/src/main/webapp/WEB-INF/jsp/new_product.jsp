<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored = "false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Create Product</title>
</head>
<body>
<div>
    <h2>Secure Create Product!</h2>
    <c:if test="${sessionScope.role != null}">
        <p>Authorized=
            <c:out value="${sessionScope.role}"/>
        </p>
        <form method="get" action="/home">
            <input type="hidden" name="command" value="logout"/>
            <button type="submit">Log out</button>
        </form>
    </c:if>
    <hr/>
    <a href="/">Go to Home</a>
    <hr/>
</div>
<div class="main-block">
    <c:if test="${sessionScope.role != null}">
        <div class="main-block">
            <div>
                <form>
                    <h1 style="font-weight: bold ; font-size: 200%">
                        New Product
                    </h1>
                </form>
            </div>

            <form class="form-group" id="create_product" action="/main" method="POST">
                <input type="hidden" name="command" value="create_product"/>
                <input type="hidden" name="productId" value="${id}">
                <div class="form-group col-md-6">
                    <input class="form-control" type="text" name="brand_id" placeholder="${brand_id}"/>
                </div>
                <div class="form-group col-md-6">
                    <input class="form-control" type="text" name="model" placeholder="${model}"/>
                </div>
                <div class="form-group col-md-6">
                    <input class="form-control" type="text" name="price" placeholder="${price}"/>
                </div>
                <div class="form-group col-md-6">
                    <input class="form-control" type="text" name="quantity" placeholder="${quantity}"/>
                </div>
            </form>
        </div>
        <button class="btn btn-primary" form="create_product">Submit</button>
    </c:if>
</div>
</body>
</html>