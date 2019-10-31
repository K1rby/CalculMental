<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/vendor/foundation-6.5.1/css/foundation.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div class="row small-5 small-centered">
    <c:if test="${ !empty registerBean.authResult}">
        <div class="callout alert text-center">
            <p>${requestScope.registerBean.authResult}</p>
        </div>
    </c:if>
<form method="POST" action="register">
    <div class="form-icons">
        <h4>Formulaire d'inscription</h4>
        <div class="input-group">
								<span class="input-group-label">
									<i class="fa fa-user"></i>
								</span>
            <input required class="input-group-field" type="text" placeholder="Login" name="form-username"
                   value=""/>
        </div>
        <div class="input-group">
								<span class="input-group-label">
									<i class="fa fa-key"></i>
								</span>
            <input required class="input-group-field" type="password" placeholder="Mot de passe"
                   name="form-password"
                   value=""/>
        </div><div class="input-group">
								<span class="input-group-label">
									<i class="fa fa-key"></i>
								</span>
            <input required class="input-group-field" type="password" placeholder="confirmation Mot de passe"
                   name="form-password-confirm"
                   value=""/>
        </div>
    </div>
    <button class="button expanded">Créer</button>
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
