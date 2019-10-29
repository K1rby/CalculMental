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
<div class="top-bar" id="example-menu">
    <div class="top-bar-left">
        <ul class="dropdown menu" data-dropdown-menu>
            <li class="menu-text">Accueil</li>
        </ul>
    </div>
    <div class="top-bar-right">
        <ul class="menu">
            <li class="menu-text">${sessionScope.login}</li>
        </ul>
    </div>
</div>
<div class="row small-5 small-centered">
    <h1>Meilleurs Scores</h1>
    <table>
        <thead>
        <tr>
            <th>N°</th>
            <th>Score</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${homeBean.partie}" varStatus="loop">
            <c:if test="${loop.index == 0}">
                <tr style="background-color: yellow">
                    <td>${loop.count}</td>
                    <td>${item.score}</td>
                </tr>
            </c:if>
            <c:if test="${loop.index == 1}">
                <tr style="background-color: slategray">
                    <td>${loop.count}</td>
                    <td>${item.score}</td>
                </tr>
            </c:if>
            <c:if test="${loop.index == 2}">
                <tr style="background-color: maroon">
                    <td>${loop.count}</td>
                    <td>${item.score}</td>
                </tr>
            </c:if>
            <c:if test="${loop.index > 2}">
                <tr>
                    <td>${loop.count}</td>
                    <td>${item.score}</td>
                </tr>
            </c:if>

        </c:forEach>
        </tbody>
    </table>
    <a href="<c:url value="/resultat"/>" class="button">Nouvelle partie</a>
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
