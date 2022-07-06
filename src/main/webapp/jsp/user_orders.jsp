<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/user_orders.css" type="text/css"/>
    <fmt:setLocale value="${sessionScope.locale}" scope="session"/>

    <fmt:bundle basename="content">
        <fmt:message key="orders.user" var="user"/>
        <fmt:message key="orders.car" var="car"/>
        <fmt:message key="order.start_date" var="start_date"/>
        <fmt:message key="order.end_date" var="end_date"/>
        <fmt:message key="orders.status" var="status"/>
        <fmt:message key="orders.rejection_comment" var="rejection_comment"/>
        <fmt:message key="orders.return_comment" var="return_comment"/>
        <fmt:message key="orders.action" var="actions"/>
        <fmt:message key="orders.pay" var="pay"/>
        <fmt:message key="orders.received" var="received"/>
        <fmt:message key="orders.return" var="return"/>
        <fmt:message key="orders.return_placeholder" var="return_placeholder"/>
        <fmt:message key="orders.rejection_placeholder" var="rejection_placeholder"/>
        <fmt:message key="orders.new_status" var="new_status"/>
        <fmt:message key="orders.approve" var="approve"/>
        <fmt:message key="orders.reject" var="reject"/>
        <fmt:message key="orders.change_status" var="change_status"/>
        <fmt:message key="orders.approved_status" var="approved_status"/>
        <fmt:message key="orders.cancelled_status" var="cancelled_status"/>
        <fmt:message key="orders.paid_status" var="paid_status"/>
        <fmt:message key="orders.received_status" var="received_status"/>
        <fmt:message key="orders.returned_status" var="returned_status"/>
        <fmt:message key="orders.rejected_status" var="rejected_status"/>
        <fmt:message key="orders.cancel" var="cancel"/>
        <fmt:message key="orders.leave_comment" var="leave_comment"/>
        <fmt:message key="admin.edit" var="edit"/>
    </fmt:bundle>
</head>
<body>
<jsp:include page="../jsp/blocks/header.jsp"/>
<c:set var="is_admin" value="${sessionScope.user.role eq 'ADMIN'}"/>

<div class="main_block">
    <form id="main_form" action="${context_path}/controller?command=change_order_status" method="post">
        <display:table name="requestScope.orders" uid="order" class="table" pagesize="8" requestURI=""
                       sort="list" defaultorder="ascending">
            <c:if test="${is_admin}">
                <display:column title="${user}" sortable="true" sortProperty="user.email">
                    ${order.user.email}
                </display:column>
            </c:if>
            <display:column title="${car}" sortable="true" sortProperty="car.brand">
                ${order.car.brand} ${order.car.model}
            </display:column>
            <display:column title="${start_date}" sortable="true" sortProperty="startDate">
                ${order.startDate}
            </display:column>
            <display:column title="${end_date}" sortable="true" sortProperty="endDate">
                ${order.endDate}
            </display:column>
            <display:column title="${status}" sortable="true" sortProperty="status">
                <sharing:ConstantFormatTag constant="${order.status}" enumeration="OrderStatus"/>
            </display:column>
            <display:column title="${rejection_comment}">
                <c:choose>
                    <c:when test="${is_admin and (order.status eq 'NEW' or order.status eq 'APPROVED')}">
                        <textarea name="rejection_comment" placeholder="${rejection_placeholder}" form="main_form"></textarea>
                    </c:when>
                    <c:otherwise>
                        ${order.rejectionComment}
                    </c:otherwise>
                </c:choose>
            </display:column>
            <c:if test="${is_admin}">
                <display:column title="${return_comment}">
                    <c:if test="${is_admin and order.status eq 'RECEIVED'}">
                        <textarea name="return_comment" placeholder="${return_placeholder}" form="main_form"></textarea>
                    </c:if>
                    ${order.returnComment}
                </display:column>
            </c:if>
            <display:column title="${actions}">
                <div class="actions">
                    <c:choose>
                        <c:when test="${is_admin}">
                            <c:if test="${order.status eq 'NEW'}">
                                <a id="approve"
                                   href="${context_path}/controller?command=change_order_status&status=approved&data_id=${order.id}">${approve}</a><br/>
                                <input type="hidden" name="status" value="rejected">
                                <input type="hidden" name="data_id" value="${order.id}">
                                <button id="reject" type="submit">${reject}</button><br/>
                            </c:if>
                            <c:if test="${order.status eq 'PAID'}">
                                <a id="approve" href="${context_path}/controller?command=change_order_status&status=received&data_id=${order.id}">${received}</a><br/>
                            </c:if>
                            <c:if test="${order.status eq 'RECEIVED'}">
                                <button id="reject" type="submit">${retrn}</button><br/>
                                <input type="hidden" name="status" value="returned">
                                <input type="hidden" name="data_id" value="${order.id}">
                            </c:if>
                            <c:if test="${order.status eq 'APPROVED'}">
                                <input type="hidden" name="status" value="rejected">
                                <input type="hidden" name="data_id" value="${order.id}">
                                <button id="reject" type="submit">${reject}</button><br/>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${order.status eq 'APPROVED'}">
                                <a id="approve" href="${context_path}/controller?command=go_to_payment_page&data_id=${order.id}">${pay}</a><br/>
                                <c:set var="order_id" value="${order.id}"/>
                            </c:if>
                            <c:if test="${order.status eq 'NEW' or order.status eq 'APPROVED'}">
                                <a id="reject" href="${context_path}/controller?command=change_order_status&status=cancelled&data_id=${order.id}">${cancel}</a><br/>
                            </c:if>
                            <c:if test="${order.status eq 'RETURNED'}">
                                <a id="approve" href="Controller?command=go_to_car_comments_page&data_id=${order.car.id}&current_page=1">${leave_comment}</a>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
            </display:column>
        </display:table>
    </form>
</div>
</body>
</html>
