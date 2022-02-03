<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored = "false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Main</title>
</head>
<body>
<div>
    <h2>Secure Main!</h2>
    <c:if test="${sessionScope.role != null}">
        <p>Authorized=
            <c:out value="${sessionScope.role}"/>
        </p>
        <form method="get" action="/main">
            <input type="hidden" name="command" value="logout"/>
            <button type="submit">Log out</button>
        </form>
    </c:if>
    <p>
        <c:out value="${message}"/>
    </p>
    <p>
        <c:out value="${error_message}"/>
    </p>
    <hr/>
    <!-- NAGATION -->
    <a href="/">Go to Home</a>
    <hr/>
</div>

<c:if test="${sessionScope.role != null}">
    <hr/>
    <a href="/new_product">Create Product</a>
    <hr/>
    <br>
    <div>
        <form id="show_products" method="get" action="/main">
            <input type="hidden" name="command" value="show_products"/>
            <button form="show_products" type="submit">Show products</button>
        </form>
    </div>
    <div>
        <table>
            <thead>
            <tr>
                <td>
                    <h4>
                        <c:out value="info"/>
                    </h4>
                </td>
                <td>
                    <h4>
                        <c:out value="id"/>
                    </h4>
                </td>
                <td>
                    <h4>
                        <c:out value="brand_id"/>
                    </h4>
                </td>
                <td>
                    <h4>
                        <c:out value="model"/>
                    </h4>
                </td>
                <td>
                    <h4>
                        <c:out value="price"/>
                    </h4>
                </td>
                <td>
                    <h4>
                        <c:out value="quantity"/>
                    </h4>
                </td>
                <td>
                    <h4>
                        <c:out value="buy"/>
                    </h4>
                </td>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${requestScope.pageable.elements}" var="product">
                <tr>
                    <td>
                        <!--<div class="main-block">
                            <form action="/main" class="form-group" id="update_product" method="POST">
                                <input name="command" type="hidden" value="update_product"/>
                                <input name="productId" type="hidden" value="${product.id}"/>
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
                        </div>-->
                    </td>
                    <td>${product.id}</td>
                    <td>${product.brand_id}</td>
                    <td>${product.model}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>
                        <form action="/main" id="delete_product" method="POST">
                            <input name="command" type="hidden" value="delete_product"/>
                            <input name="productId" type="hidden" value="${requestScope.product.id}"/>
                            <button class="btn btn-primary" form="delete_product" type="submit">Delete product</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div style="margin-left: center">
            <c:forEach begin="1" end="${Math.ceil(pageable.totalElements / pageable.limit)}" var="i">
                <c:if test="${i == pageable.pageNumber}">
                            <span>
                                <button style="color:red" form="show_products" type="submit" name="currentPage"
                                        value="${i}">${i}</button>
                            </span>
                </c:if>
                <c:if test="${i != pageable.pageNumber}">
                            <span>
                                <button form="show_products" type="submit" name="currentPage" value="${i}">${i}</button>
                            </span>
                </c:if>
            </c:forEach>
        </div>
    </div>
    <br>
    <br>
</c:if>
</body>
</html>
