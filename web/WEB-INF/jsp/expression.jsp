<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Identifiez-vous</title>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/vendor/foundation-6.5.1/css/foundation.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div class="callout large primary">
    <div class="row column text-center">
        <h1>Calcul mental</h1>
    </div>
</div>
<form method="POST" action="expression">
    <c:forEach var="item" items="${expressionBean.expressions}" varStatus="loop">
    <div class="form-icons">
        <h4>Veuillez répondre à la question</h4>
        <label>${item.expression}</label>
<%--        <label>${expressionBean.resultats.get(loop.count)}</label>--%>
        <div class="input-group">
							<span class="input-group-label">
								<i class="fa fa-key"></i>
							</span>
            <input class="input-group-field" type="number" placeholder="Réponse"
                   name="form-answer${loop.count}" step="any" required
                   value=""/>
        </div>
    </div>
    </c:forEach>
    <button class="button expanded">Valider</button>
</form>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/jquery.js"></script>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/foundation.min.js"></script>
<script>
    $(document).foundation();
    document.documentElement.setAttribute('data-useragent', navigator.userAgent);
</script>
</body>
</html>
