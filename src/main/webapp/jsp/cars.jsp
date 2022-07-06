<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sharing" uri="carsharing" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/cars.css" type="text/css"/>
    <link rel="stylesheet" href="css/news.css" type="text/css"/>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content">
        <fmt:message key="car.find" var="find"/>
        <fmt:message key="car.comments" var="comments"/>

        <fmt:message key="car.order" var="order"/>
        <fmt:message key="car.class" var="car_class"/>
        <fmt:message key="car.year" var="year"/>
        <fmt:message key="car.gearbox" var="gearbox"/>
        <fmt:message key="car.engine" var="engine"/>
        <fmt:message key="car.price" var="price"/>
        <fmt:message key="admin.delete" var="delete"/>
        <fmt:message key="admin.edit" var="edit"/>
        <fmt:message key="admin.add_car" var="add_car"/>
        <fmt:message key="header.cars" var="cars"/>
        <fmt:message key="delete_dialog.message" var="message"/>

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

        <fmt:message key="cars.nothing_found" var="nothing_found"/>
        <fmt:message key="cars.back" var="back"/>
    </fmt:bundle>
    <title>${cars}</title>
</head>
<body>
<jsp:include page="../jsp/header.jsp"/>
<div class="user_panel">
    <form action="${context_path}/controller?command=go_to_cars_page" method="post">
        ${find}: <input name="search" type="text" placeholder="Citroen C5">
        <button type="submit">&hookleftarrow;</button>
    </form>
</div>
<div class="nothing_found">
    <c:if test="${empty requestScope.cars}">
        <h2>${nothing_found}. <a href="${context_path}/controller?command=go_to_cars_page">${back}</a>.</h2>
    </c:if>
</div>
<div class="admin_button_panel">
    <c:if test="${sessionScope.user.role eq 'ADMIN'}">
        <a id="admin_but_add" href="Controller?command=go_to_car_edit_page">${add_car}</a>
    </c:if>
</div>
<div class="main_block">
    <c:forEach var="car" items="${requestScope.cars}">
        <div class="car_block">
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
                    ${price}: ${car.pricePerDay} BYN
            </div>
            <div class="car_block__button">
                <a href="${context_path}/controller?command=go_to_car_comments_page&data_id=${car.id}&current_page=1">${comments}</a>
                <c:choose>
                    <c:when test="${sessionScope.user.role eq 'ADMIN'}">
                        <a href="${context_path}/controller?command=go_to_car_edit_page&data_id=${car.id}">${edit}</a>
                        <a href="${context_path}/controller?command=delete_car&data_id=${car.id}"
                           onclick="return confirm('${message}')">${delete}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${context_path}/controller?command=go_to_order_page&data_id=${car.id}">${order}</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </c:forEach>
</div>
<jsp:include page="../jsp/footer.jsp"/>
</body>
</html>
