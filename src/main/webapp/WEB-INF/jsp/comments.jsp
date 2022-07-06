<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sharing" uri="carsharing" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="<c:url value="/css/cars.css"/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/css/comments.css"/>" type="text/css">
    <script type="text/javascript" src="./ckeditor/ckeditor.js"></script>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content">
        <fmt:message key="car.order" var="order"/>
        <fmt:message key="car.class" var="car_class"/>
        <fmt:message key="car.year" var="year"/>
        <fmt:message key="car.gearbox" var="gearbox"/>
        <fmt:message key="car.engine" var="engine"/>
        <fmt:message key="car.price" var="price"/>
        <fmt:message key="admin.submit" var="submit"/>
        <fmt:message key="admin.delete" var="delete"/>

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

        <fmt:message key="comments.no_comments" var="no_comments"/>
        <fmt:message key="comments.invalid_content" var="invalid_content"/>
        <fmt:message key="comments.write_comment" var="write_comment"/>
        <fmt:message key="comments.next" var="next"/>
        <fmt:message key="comments.previous" var="previous"/>
    </fmt:bundle>
</head>
<body>
<c:set var="comments" value="${requestScope.data}"/>
<c:set var="car" value="${requestScope.car}"/>

<jsp:include page="../jsp/header.jsp"/>
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

    <c:if test="${requestScope.validation eq 'Invalid content'}">
    <h2 id="no_comments">${invalid_content}<h2/>
        </c:if>

        <c:if test="${requestScope.able_to_comment}">
        <div class="comment_editor">
            <h4>${write_comment}:</h4>
            <form name="main_form" action="${context_path}/controller?command=leave_comment" method="post">
                <textarea name="content_editor" id="content_editor"></textarea>
                <input type="hidden" name="data_id" value="${car.id}">
                <button class="submit_but" type="submit">${submit}</button>
            </form>
        </div>
        </c:if>

        <c:if test="${empty comments}">
        <h2 id="no_comments">${no_comments}</h2>
        </c:if>

        <div class="comments">
            <c:forEach var="c" items="${comments}">
                <div class="comment">
                    <div class="comment__header">
                        <h4>${c.user.email.split('@')[0]}</h4>
                        <div class="comment__header__admin_panel">
                            <c:if test="${sessionScope.user.role eq 'ADMIN' or sessionScope.user eq c.user}">
                                <a href="${context_path}/controller?command=delete_comment&data_id=${c.id}" title="${delete}">&times;</a>
                            </c:if>
                        </div>
                    </div>
                    <div class="comment__content">
                            ${c.content}
                    </div>
                </div>
            </c:forEach>
        </div>

        <c:set var="currentPage" value="${requestScope.current_page}"/>


        <div class="content">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link" href="${context_path}/controller?command=go_to_car_comments_page&data_id=${car.id}&current_page=${currentPage-1}">${previous}</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${requestScope.pages_amount}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item_active">
                                <a class="page-link">${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="${context_path}/controller?command=go_to_car_comments_page&data_id=${car.id}&current_page=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt requestScope.pages_amount}">
                    <li class="page-item">
                        <a class="page-link" href="${context_path}/controller?command=go_to_car_comments_page&data_id=${car.id}&current_page=${currentPage+1}">${next}</a>
                    </li>
                </c:if>
            </ul>
        </div>
</div>

<script src="js/ckeditor_config.js"></script>
</body>
</html>
