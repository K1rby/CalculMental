<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/vendor/foundation-6.5.1/css/foundation.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <title>Compte Detail</title>
</head>
<body>
<div class="top-bar" id="example-menu">
    <div class="top-bar-left">
        <ul class="dropdown menu" data-dropdown-menu>
            <a href="<c:url value="/home"/>">Accueil</a>
        </ul>
    </div>
    <div class="top-bar-right">
        <ul class="menu">
            <li class="menu-text">${sessionScope.login}</li>
            <a href="<c:url value="/logout"/>">Déconnexion</a>
        </ul>
    </div>
</div>
<div class="row small-5 small-centered">
    <c:if test="${ !empty accountBean.authResult}">
        <div class="callout alert text-center">
            <p>${requestScope.registerBean.authResult}</p>
        </div>
    </c:if>
    <form method="POST" action="${pageContext.request.contextPath}/account/details">
        <div class="form-icons">
            <h4>Formulaire de modification</h4>
            <div class="input-group">
								<span class="input-group-label">
									<i class="fa fa-user"></i>
								</span>
                <input required class="input-group-field" type="text" placeholder="Login" name="form-username"
                       value="${sessionScope.connectedUser.pseudo}"/>
            </div>
            <div class="input-group">
								<span class="input-group-label">
									<i class="fa fa-key"></i>
								</span>
                <input required class="input-group-field" type="password" placeholder="Mot de passe"
                       name="form-password"
                       value="${sessionScope.connectedUser.password}"/>
            </div><div class="input-group">
								<span class="input-group-label">
									<i class="fa fa-key"></i>
								</span>
            <input required class="input-group-field" type="password" placeholder="confirmation Mot de passe"
                   name="form-password-confirm"
                   value="${sessionScope.connectedUser.password}"/>
        </div>
        </div>
        <button class="button expanded">Modifier</button>
    </form>
</div>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/jquery.js"></script>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/foundation.min.js"></script>
<script>
    $(document).foundation();
    document.documentElement.setAttribute('data-useragent', navigator.userAgent);
</script>
</body>
</html>
