<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/register.css"/>" type="text/css"/>

    <fmt:setLocale value="${sessionScope.locale}" scope="session"/>
    <fmt:bundle basename="content">
        <fmt:message key="register.password" var="password"/>
        <fmt:message key="register.sign_up" var="register"/>
        <fmt:message key="register.error_message" var="error_message"/>
        <fmt:message key="register.have_account" var="have_account"/>
        <fmt:message key="register.first_name" var="first_name"/>
        <fmt:message key="register.second_name" var="second_name"/>
        <fmt:message key="register.middle_name" var="middle_name"/>
        <fmt:message key="register.phone_number" var="phone_number"/>
        <fmt:message key="register.passport_number" var="passport_number"/>
        <fmt:message key="register.identification_number" var="identification_number"/>
        <fmt:message key="register.issue_date" var="issue_date"/>
        <fmt:message key="register.validation_message" var="validation_message"/>
        <fmt:message key="header.log_in" var="log_in"/>
        <fmt:message key="header.sign_up" var="sign_up"/>
    </fmt:bundle>
    <title>${sign_up}</title>
</head>
<body>

<div class="bg-image"></div>
<div class="register">
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="register">
        <label>
            Email
            <input type="text" name="email" required>
        </label>
        <label>
            ${password}
            <input type="password" name="password" required>
        </label>
        <label>
            ${first_name}
            <input name="first_name" pattern="[A-ZА-Я][a-zа-я]*" required>
        </label>
        <label>
            ${second_name}
            <input name="second_name" required>
        </label>
        <label>
            ${middle_name}
            <input name="middle_name">
        </label>
        <label>
            ${phone_number}
            <input type="tel" name="phone_number" size="13" pattern="[+]([0-9]{12})" required>
        </label>
        <label>
            ${passport_number}
            <input name="passport_number" value="" pattern="([A-ZА-Я]{2})([0-9]{7})" placeholder="AA1234567" required/>
        </label>
        <label>
            ${identification_number}
            <input name="identification_number" value="" placeholder="1234567A123PB1"
                       pattern="([0-9]{7})[A-ZА-Я]([0-9]{3})[A-ZА-Я][A-ZА-Я][0-9]" required/>
        </label>
        <label>
            ${issue_date}
            <input name="issue_date" type="date" value="" placeholder="DD-MM-YYYY" min="01/01/1970" max="01/01/2100" required/>
        </label>
        <input class="button" type="submit" value="${register}">
        <label>${have_account}</label>
        <a href="login">${log_in}</a>
    </form>
    <c:set var="is_register_failed" scope="request" value="${requestScope.error}"/>
    <c:set var="is_invalid_data" scope="request" value="${requestScope.validation}"/>

    <c:choose>
        <c:when test="${is_register_failed}">
            <p>${error_message}</p>
        </c:when>
        <c:when test="${is_invalid_data}">
            <p>${validation_message}</p>
        </c:when>
    </c:choose>


</div>
<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>
