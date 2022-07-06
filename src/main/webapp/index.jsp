<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/news.css" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/cars.css" type="text/css">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:bundle basename="content">
        <fmt:message key="admin.add_news" var="add_news"/>
        <fmt:message key="admin.edit" var="edit_news"/>
        <fmt:message key="admin.delete" var="delete"/>
        <fmt:message key="delete_dialog.message" var="message"/>
        <fmt:message key="header.news" var="news"/>
    </fmt:bundle>
    <title>${news}</title>

</head>
<body>
   <jsp:include page="/jsp/blocks/header.jsp"/>
   <jsp:include page="/jsp/blocks/footer.jsp"/>
</body>
</html>