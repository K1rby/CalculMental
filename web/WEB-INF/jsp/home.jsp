<%--
  Created by IntelliJ IDEA.
  User: quentin
  Date: 24/10/19
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/vendor/foundation-6.5.1/css/foundation.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<h1>Bienvenue sur le jeu de calcul mental</h1>
<div class="row small-5 small-centered">
    <table>
        <thead>
        <tr>
            <th>N°</th>
            <th>Score</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${homeBean.partie}" varStatus="loop">
            <tr>
                <td>${loop.count}</td>
                <td>${item.value.score}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<a href="<c:url value="/expression" />" class="button">Nouvelle partie</a>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/jquery.js"></script>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/foundation.min.js"></script>
<script>
    $(document).foundation();
    document.documentElement.setAttribute('data-useragent', navigator.userAgent);
</script>
</body>
</html>
