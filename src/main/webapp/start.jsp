<%-- 
    Document   : start
    Created on : 12 févr. 2017, 19:57:26
    Author     : Richard
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<c:set var="titre_main" value="ID Card"/>
<html>
    <head>
        <title>${titre_main}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<link rel="stylesheet" href="libs/bootstraplib/css/bootstrap.min.css"/>

		<link rel="stylesheet" href="css/main.css"/>

    </head>
    <body>

		<div id="react_container">
		</div>

		<script>
			var GLOBALS = {
				titre_main: "${titre_main}",
				donnees: ${donnees},
				page: "${page}"	//Défini la première page
			};
		</script>
		<script type="text/javascript" data-main="js/app" src="libs/require/require.js"></script>
    </body>
</html>
