<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resultat</title>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <script src="https://kit.fontawesome.com/bc8750f867.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/vendor/foundation-6.5.1/css/foundation.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div class="top-bar" id="example-menu">
    <div class="top-bar-right">
        <ul class="menu">
            <li class="menu-text">${sessionScope.login}</li>
            <a href="<c:url value="/logout"/>">Déconnexion</a>
        </ul>
    </div>
</div>
<div class="row small-5 small-centered" style="margin-top: 4%">
    <table>
        <thead>
        <tr>
            <th>N°</th>
            <th>Réponse</th>
            <th>Solution</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="mapV" items="${resultReponse}" varStatus="loop">
            <tr>
                <td>${loop.count}</td>
                <td> ${mapV.key} </td>
                <td> ${mapV.value}</td>
                <td>
                    <c:if test="${mapV.key == mapV.value}">
                        <i style="font-size: 30px; color: green;" class="fas fa-check-circle"></i>
                    </c:if>
                    <c:if test="${mapV.key != mapV.value}">
                        <i style="font-size: 30px; color: red;"class="fas fa-window-close"></i>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a style="width: 100%" href="<c:url value="/home"/>" class="button">Retour a l'accueil</a>
    <h2>Vous avez réaliser un score de ${score}</h2>
</div>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/jquery.js"></script>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/foundation.min.js"></script>
<script>
    $(document).foundation();
    document.documentElement.setAttribute('data-useragent', navigator.userAgent);
</script>
</body>
</html>
