<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="css" fragment="true" %>
<html>
<head>
    <jsp:invoke fragment="css"/>
</head>
<body>
<jsp:invoke fragment="header"/>
<jsp:doBody/>
<jsp:invoke fragment="footer"/>
</body>
</html>