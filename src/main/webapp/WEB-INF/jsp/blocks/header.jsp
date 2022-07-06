<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="css/header.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content">
        <fmt:message key="header.log_in" var="log_in"/>
        <fmt:message key="header.sign_up" var="sign_up"/>
        <fmt:message key="header.log_out" var="log_out"/>
        <fmt:message key="header.news" var="news"/>
        <fmt:message key="header.cars" var="cars"/>
        <fmt:message key="header.about" var="about"/>
        <fmt:message key="locale.change_lang" var="change_lang"/>
        <fmt:message key="locale.ru" var="ru"/>
        <fmt:message key="locale.en" var="en"/>
        <fmt:message key="header.orders" var="orders"/>
    </fmt:bundle>

    <title>${news}</title>
</head>
<body>
<div class="header">
    <a href=href="${context_path}/controller?command=go_to_main_page class="header__logo">CarRental</a>
    <div class="header__links">
        <a href="${context_path}/controller?command=go_to_main_page">${news}</a>
        <a href="${context_path}/controller?command=go_to_cars_page">${cars}</a>
        <c:if test="${sessionScope.user != null}">
            <a href="Controller?command=go_to_orders_page">${orders}</a>
        </c:if>
        <c:choose>
            <c:when test="${sessionScope.user eq null}">
                <a href="${context_path}/controller?command=go_to_login_page">${log_in}</a>
                <a href="${context_path}/controller?command=go_to_register_page">${sign_up}</a>
            </c:when>
            <c:otherwise>
                <a href="${context_path}/controller?command=sign_out">${log_out}</a>
            </c:otherwise>
        </c:choose>

        <div id="modal" class="modal">
            <div class="modal__content">
                <h1>${change_lang}</h1>

                <a href="#" class="modal__close">&times;</a>
                <div class="languages">
                    <a id="en" href="${context_path}/controller?command=change_lang&locale=en">${en}</a>
                    <a id="ru" href="${context_path}/controller?command=change_lang&locale=ru">${ru}</a>

                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>