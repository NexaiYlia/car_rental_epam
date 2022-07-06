<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="css/cars.css" type="text/css"/>
    <link rel="stylesheet" href="css/order.css" type="text/css"/>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content">
        <fmt:message key="car.order" var="order"/>
        <fmt:message key="car.color" var="color"/>
        <fmt:message key="car.class" var="car_class"/>
        <fmt:message key="car.year" var="year"/>
        <fmt:message key="car.gearbox" var="gearbox"/>
        <fmt:message key="car.engine" var="engine"/>
        <fmt:message key="car.price" var="price"/>
        <fmt:message key="car.mileage" var="mileage"/>
        <fmt:message key="admin.submit" var="submit"/>
        <fmt:message key="order.start_date" var="start_date"/>
        <fmt:message key="order.end_date" var="end_date"/>
        <fmt:message key="order.not_available" var="not_available"/>
        <fmt:message key="order.already_present" var="already_present"/>
        <fmt:message key="order.earlier_than_today" var="earlier_than_today"/>
        <fmt:message key="order.earlier_than_end" var="earlier_than_end"/>
        <fmt:message key="orders.total_price" var="total_price"/>

        <fmt:message key="car.class_hatchback" var="hatchback_class"/>
        <fmt:message key="car.class_muscle" var="muscle_class"/>
        <fmt:message key="car.class_pickup" var="pickup_class"/>
        <fmt:message key="car.class_sedan" var="sedan_class"/>
        <fmt:message key="car.class_sport" var="sport_class"/>
        <fmt:message key="car.class_suv" var="suv_class"/>
        <fmt:message key="car.class_wagon" var="wagon_class"/>

        <fmt:message key="car.engine_diesel" var="diesel_engine"/>
        <fmt:message key="car.engine_gas" var="gas_engine"/>
        <fmt:message key="car.engine_petrol" var="petrol_engine"/>

        <fmt:message key="car.gearbox_manual" var="manual_gearbox"/>
        <fmt:message key="car.gearbox_automatic" var="automatic_gearbox"/>

        <fmt:message key="car.color_black" var="black_color"/>
        <fmt:message key="car.color_blue" var="blue_color"/>
        <fmt:message key="car.color_grey" var="grey_color"/>
        <fmt:message key="car.color_red" var="red_color"/>
        <fmt:message key="car.color_yellow" var="yellow_color"/>
        <fmt:message key="car.color_white" var="white_color"/>
        <fmt:message key="editor.error" var="error_message"/>
    </fmt:bundle>

    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<c:url value="/js/order.js"/>"></script>
    <title></title>
</head>
<body>
<jsp:include page="../jsp/blocks/header.jsp"/>
<c:set var="error" value="${requestScope.error}"/>
<c:set var="validation" value="${requestScope.validation}"/>
<c:choose>
    <c:when test="${validation eq 'Order is already made'}">
        <p class="error">${already_present}</p>
    </c:when>
    <c:when test="${validation eq 'Start date is earlier than todays date'}">
        <p class="error">${earlier_than_today}</p>
    </c:when>
    <c:when test="${validation eq 'Start date is earlier than end date'}">
        <p class="error">${earlier_than_end}</p>
    </c:when>
    <c:when test="${validation ne null}">
        <p class="error">${not_available} ${validation}</p>
    </c:when>
    <c:when test="${error ne null}">
        <p class="error">${error_message}</p>
    </c:when>
</c:choose>
<div class="order_main_block">
    <div class="car_block" style="width: 440px;">
        <div class="car_block__brand">
            ${car.brand} ${car.model}
        </div>
        <div class="car_block__img">
            <img src="${car.imagePath}"/>
        </div>
        <div class="car_block__characteristics">

            ${gearbox}: <sharing:ConstantFormatTag constant="${car.gearbox}" enumeration="GearboxType"/><br/>
            ${year}: ${car.manufacturedYear}<br/>
            ${engine}: <sharing:ConstantFormatTag constant="${car.engineType}" enumeration="EngineType"/><br/>
            ${car_class}: <sharing:ConstantFormatTag constant="${car.carClass}" enumeration="CarClass"/><br/>
        </div>
        <div class="car_block__price">
            ${price}: <span>${car.pricePerDay}</span> BYN
        </div>
    </div>
    <form name="Controller" method="get" onsubmit="return validateDate()">
        <input type="hidden" name="command" value="make_order">
        <div class="form">
            <div class="content">
                ${start_date}<br/>
                <input id="start" name="start_date" type="date" value="" required/><br/>
                ${end_date}<br/>
                <input id="end" name="end_date" type="date" value="" required/><br/>
                <div class="total_price">
                    <input id="total_price" type="hidden" name="total_price" value="" readonly="readonly" required/>
                    <input type="hidden" name="data_id" value="${car.id}">
                    <p>${total_price}: <span>0</span> BYN</p>
                </div>
            </div>
        </div>
        <div class="but">
            <button type="submit" value="">${submit}</button>
        </div>
    </form>
</div>
<jsp:include page="../jsp/blocks/footer.jsp"/>
</body>
</html>
