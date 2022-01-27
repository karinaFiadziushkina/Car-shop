<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored = "false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>${mainPage}</title>
</head>
<body style="padding-top : 0px ; text-align : start">
<br/>
<div>

    <!----------   MAIN  ---------->
    <!----------   SHOW PRODUCTS  ---------->
    <div>
        <form action="frontController" id="showProducts" method="GET">
            <input name="command" type="hidden" value="show_Products"/>
            <button class="btn btn-primary " form="showProducts">${showProducts}</button>
        </form>
    </div>
    <div>
        <c:if test="${not empty requestScope.pageable.elements}">
            <table class="table">
                <thead class="light">
                <tr>
                    <th></th>
                    <th>${brand_id}</th>
                    <th>${model}</th>
                    <th>${price}</th>
                    <th>${quantity}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.pageable.elements}" var="car">
                    <tr>
                        <td>
                            <form action="frontController" method="GET">
                                <input name="command" type="hidden" value="car_info"/>
                                <input name="id" type="hidden" value="${car.id}"/>
                                <button class="btn btn-primary" type="submit">${productInfo}</button>
                                <br/>
                            </form>
                        </td>
                        <td>${car.brand_id}</td>
                        <td>${car.model}</td>
                        <td>${car.price}</td>
                        <td>${car.quantity}</td>
                        <td>
                        </td>
                        <td>
                            <form action="frontController" method="POST">
                                <input name="command" type="hidden" value="create_Reserve"/>
                                <input name="carId" type="hidden" value="${car.id}"/>
                                <button class="btn btn-primary">${createReserve}</button>
                                <br/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div style="text-align: center">
                <c:forEach begin="1" end="${Math.ceil(pageable.totalElements / pageable.limit)}" var="i">
                    <c:if test="${i == pageable.pageNumber}">
                        <span>
                            <button class="counter" form="showProducts" name="currentPage" style="color:red"
                                    type="submit"
                                    value="${i}">${i}</button>
                        </span>
                    </c:if>
                    <c:if test="${i != pageable.pageNumber}">
                            <span>
                                <button class="counter" form="showProducts" name="currentPage" type="submit"
                                        value="${i}">${i}</button>
                            </span>
                    </c:if>
                </c:forEach>
            </div>
        </c:if>
    </div>


    <div>
        <form action="frontController" id="showUsers" method="GET">
            <input name="command" type="hidden" value="show_Users"/>
            <button class="btn btn-primary" form="showUsers" type="submit">${showUsers}</button>
        </form>
    </div>
    <div>
        <c:if test="${not empty requestScope.pageableUsers.elements}">
            <table class="table">
                <thead class="light shown-thread-users">
                <tr>
                    <th></th>
                    <th>id</th>
                    <th>${login}</th>
                    <th>${role_id}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.pageableUsers.elements}" var="userRow">
                    <tr>
                        <td>
                        </td>
                        <td>${userRow.id}</td>
                        <td>${userRow.login}</td>
                        <td>${userRow.role_id}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div style="text-align: center">
                <c:forEach begin="1" end="${Math.ceil(pageableUsers.totalElements / pageableUsers.limit)}"
                           var="i">
                    <c:if test="${i == pageableUsers.pageNumber}">
                            <span>
                                <button class="counter" form="showUsers" name="currentPage" style="color:red"
                                        type="submit"
                                        value="${i}">${i}</button>
                            </span>
                    </c:if>
                    <c:if test="${i != pageableUsers.pageNumber}">
                            <span>
                                <button class="counter" form="showUsers" name="currentPage" type="submit"
                                        value="${i}">${i}</button>
                            </span>
                    </c:if>
                </c:forEach>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>