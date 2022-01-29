<!--
<%@ page language="java" contentType="text\html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
-->
<html>
<body>
<div>
    <h2>Secure Main!</h2>
    <c:if test="${sessionScope.role != null}">
        <p>Authorized=<c:out value="${sessionScope.role}" /></p>
        <form method="get" action="/main" >
            <input type="hidden" name="command" value="logout"/>
            <button type="submit">Log out</button>
        </form>
    </c:if>

    <p><c:out value="${message}" /></p>
    <hr/>
    <!-- NAGATION -->
    <a href="/">[GET] Go to Home.jsp</a>
    <hr/>
    <br/>
</div>

<c:if test="${sessionScope.role != null}">
    <div>
        <form id="show_products" method="get" action="/main" >
            <input type="hidden" name="command" value="show_products"/>
            <button form="show_products" type="submit">Show products</button>
        </form>
    </div>
    <div>
        <table>
            <thead>
            <tr>
                <td><h4><c:out value="info"/></h4></td>
                <td><h4><c:out value="id"/></h4></td>
                <td><h4><c:out value="brand_id"/></h4></td>
                <td><h4><c:out value="model"/></h4></td>
                <td><h4><c:out value="price"/></h4></td>
                <td><h4><c:out value="quantity"/></h4></td>
                <td><h4><c:out value="buy"/></h4></td>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${requestScope.pageable.elements}" var="product">
                <tr>
                    <td>
                        <button type="button">Product Page Button</button><br/>
                    </td>
                    <td>${product.id}</td>
                    <td>${product.brand_id}</td>
                    <td>${product.model}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>
                    <td>
                        <button type="button">Add To Basket Button</button><br/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div style="margin-left: center">
            <c:forEach begin="1" end="${Math.ceil(pageable.totalElements / pageable.limit)}" var="i">
                <c:if test="${i == pageable.pageNumber}">
                            <span>
                                <button style="color:red" form="show_products" type="submit" name="currentPage" value="${i}">${i}</button>
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
</c:if>
</body>
</html>
<!--<html>-->
<!--<head>-->
<!--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>-->
<!--    <fmt:setLocale value="${sessionScope.locale}"/>-->
<!--    <fmt:setBundle basename="i18n.locale" var="loc"/>-->
<!--    <fmt:message bundle="${loc}" key="secure.main" var="secure_main"/>-->
<!--    <fmt:message bundle="${loc}" key="log.out" var="log_out"/>-->
<!--    <fmt:message bundle="${loc}" key="to.home" var="to_home"/>-->
<!--    <fmt:message bundle="${loc}" key="filter" var="filter"/>-->
<!--    <fmt:message bundle="${loc}" key="show.products" var="show_products"/>-->
<!--    <fmt:message bundle="${loc}" key="info" var="info"/>-->
<!--    <fmt:message bundle="${loc}" key="id" var="id"/>-->
<!--    <fmt:message bundle="${loc}" key="brand_id" var="brand_id"/>-->
<!--    <fmt:message bundle="${loc}" key="model" var="model"/>-->
<!--    <fmt:message bundle="${loc}" key="price" var="price"/>-->
<!--    <fmt:message bundle="${loc}" key="quantity" var="quantity"/>-->
<!--    <fmt:message bundle="${loc}" key="action" var="action"/>-->
<!--    <fmt:message bundle="${loc}" key="to.basket" var="to_basket"/>-->
<!--    <fmt:message bundle="${loc}" key="see.details" var="see_details"/>-->
<!--    <fmt:message bundle="${loc}" key="add.to.basket" var="add_to_basket"/>-->
<!--    <fmt:message bundle="${loc}" key="title.main" var="title_main"/>-->
<!--    <title><c:out value="${title_main}"/></title>-->
<!--</head>-->
<!--<body>-->
<!--<div>-->
<!--    <h2 style="text-transform: capitalize;">-->
<!--        <c:out value="${secure_main}"/>-->
<!--    </h2>-->
<!--    <c:if test="${sessionScope.role != null}">-->
<!--        <p>Authorized=-->
<!--            <c:out value="${sessionScope.role}"/>-->
<!--        </p>-->
<!--        <form method="get" action="/main">-->
<!--            <input type="hidden" name="command" value="logout"/>-->
<!--            <button type="submit" style="text-transform: capitalize;">-->
<!--                <c:out value="${log_out}"/>-->
<!--            </button>-->
<!--        </form>-->
<!--    </c:if>-->

<!--    <p>-->
<!--        <c:out value="${message}"/>-->
<!--    </p>-->
<!--    <hr/>-->
<!--    &lt;!&ndash; NAGATION &ndash;&gt;-->
<!--    <a href="/home" style="text-transform: capitalize;">[GET]-->
<!--        <c:out value="${to_home}"/>-->
<!--    </a>-->
<!--    <hr/>-->
<!--    <br/>-->
<!--</div>-->
<!--<c:if test="${sessionScope.role != null}">-->
<!--    <c:if test="${pageable.totalElements != 0 && Math.round(Math.ceil(pageable.totalElements / pageable.limit)) < pageable.pageNumber}">-->
<!--        <p style="color:red">-->
<!--            <c:out value="Trying to open page ${pageable.pageNumber},-->
<!--            when only ${Math.round(Math.ceil(pageable.totalElements / pageable.limit))} can be accessible!"/>-->
<!--        </p>-->
<!--    </c:if>-->
<!--    <c:if test="${pageable.totalElements == 0}">-->
<!--        <p style="color:red">-->
<!--            <c:out value="Nothing found!"/>-->
<!--        </p>-->
<!--    </c:if>-->
<!--    <div>-->
<!--        <p style="text-transform: capitalize;">-->
<!--            <c:out value="${filter}"/>-->
<!--            :-->
<!--        </p>-->
<!--        <form id="show_products" method="get" action="/main">-->
<!--            <input type="hidden" name="command" value="show_products"/>-->
<!--            <label>-->
<!--                <c:out value="${brand_id}"/>-->
<!--            </label>-->
<!--            <input type="text" name="brand_id" value="${requestScope.pageable.filter.brand_id}"/>-->
<!--            <label>-->
<!--                <c:out value="${model}"/>-->
<!--            </label>-->
<!--            <input type="text" name="model" value="${requestScope.pageable.filter.model}"/>-->
<!--            <label>-->
<!--                <c:out value="${price}"/>-->
<!--            </label>-->
<!--            <input type="text" name="price" value="${requestScope.pageable.filter.price}"/>-->
<!--            <label>-->
<!--                <c:out value="${quantity}"/>-->
<!--            </label>-->
<!--            <input type="text" name="quantity" value="${requestScope.pageable.filter.quantity}"/>-->
<!--            <button form="show_products" type="submit">Show products</button>-->
<!--        </form>-->
<!--    </div>-->
<!--    <div>-->
<!--        <table>-->
<!--            <thead>-->
<!--            <tr>-->
<!--                <td>-->
<!--                    <h4>-->
<!--                        <c:out value="${info}"/>-->
<!--                    </h4>-->
<!--                </td>-->
<!--                <td>-->
<!--                    <h4>-->
<!--                        <c:out value="${id}"/>-->
<!--                    </h4>-->
<!--                </td>-->
<!--                <td>-->
<!--                    <h4>-->
<!--                        <c:out value="${brand_id}"/>-->
<!--                    </h4>-->
<!--                </td>-->
<!--                <td>-->
<!--                    <h4>-->
<!--                        <c:out value="${model}"/>-->
<!--                    </h4>-->
<!--                </td>-->
<!--                <td>-->
<!--                    <h4>-->
<!--                        <c:out value="${price}"/>-->
<!--                    </h4>-->
<!--                </td>-->
<!--                <td>-->
<!--                    <h4>-->
<!--                        <c:out value="${quantity}"/>-->
<!--                    </h4>-->
<!--                </td>-->
<!--                <td>-->
<!--                    <h4>-->
<!--                        <c:out value="${action}"/>-->
<!--                    </h4>-->
<!--                </td>-->
<!--            </tr>-->
<!--            </thead>-->

<!--            <tbody>-->
<!--            <c:forEach items="${requestScope.pageable.elements}" var="product">-->
<!--                <tr>-->
<!--                    <td>-->
<!--                        <button type="button">-->
<!--                            <c:out value="${see_details}"/>-->
<!--                        </button>-->
<!--                        <br/>-->
<!--                    </td>-->
<!--                    <td>${product.id}</td>-->
<!--                    <td>${product.brand_id}</td>-->
<!--                    <td>${product.model}</td>-->
<!--                    <td>${product.price}</td>-->
<!--                    <td>${product.quantity}</td>-->
<!--                    <td>-->
<!--                    <td>-->
<!--                        <button type="button">-->
<!--                            <c:out value="${add_to_basket}"/>-->
<!--                        </button>-->
<!--                        <br/>-->
<!--                    </td>-->
<!--                </tr>-->
<!--            </c:forEach>-->
<!--            </tbody>-->
<!--        </table>-->
<!--        <div style="margin-left: center">-->
<!--            <c:forEach begin="1" end="${Math.ceil(pageable.totalElements / pageable.limit)}" var="i">-->
<!--                <c:if test="${i == pageable.pageNumber}">-->
<!--                    <span>-->
<!--                        <button style="color:red" form="show_products" type="submit" name="currentPage" value="${i}">${i}</button>-->
<!--                    </span>-->
<!--                </c:if>-->
<!--                <c:if test="${i != pageable.pageNumber}">-->
<!--                    <span>-->
<!--                        <button form="show_products" type="submit" name="currentPage" value="${i}">${i}</button>-->
<!--                    </span>-->
<!--                </c:if>-->
<!--            </c:forEach>-->
<!--        </div>-->
<!--    </div>-->
<!--</c:if>-->
<!--</body>-->
<!--</html>-->