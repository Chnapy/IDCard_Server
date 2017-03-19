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

		<link rel="stylesheet" href="css/test.css"/>

    </head>
    <body>

		<div id="react_container">
			<div id="react_content">
				<header class="header">
					<nav class="header-content container">
						<span class="logo nav-item">${titre_main}</span>
						<span class="nav-item active">Configuration</span>
						<span class="nav-item">Sessions</span>
					</nav>
				</header>

				<div id="content" class="body-content">

					<div class="bandeau dark">
						<div class="container">
							<div class="row">
								<h1>Configuration</h1>
								<div class="lead">
									Configurer vos propriétés, leurs valeurs et visibilités.
								</div>
							</div>
						</div>
					</div>

					<div id="list-box" class="container">
						<div class="row">

							<div class="box col-md-6">
								<div class="box-content bloc col-md-12">
									<div class="container-fluid">
										<div class="box-head row">
											<span class="box-title">Login</span>
											<div class="box-head-right">
												<select class="field" disabled>
													<option>String</option>
												</select>
											</div>
										</div>
										<div class="box-body row">
											<div class="container-fluid">
												<div class="box-line row">
													<input type="text" class="box-line-ip field field-main col-xs-6"
														   value="Chnapy" readonly />
													<div class="box-line-visib col-xs-6">Principal | Publique</div>
												</div>
												<div class="box-line row">
													<input type="text" class="box-line-ip field col-xs-6"
														   value="ChnapChnap" />
													<div class="box-line-visib col-xs-6">google.com, truc.fr</div>
													<button class="but but-delete but-error but-fh"></button>
												</div>
												<div class="box-line row">
													<input type="text" class="box-line-ip field col-xs-6"
														   value="Chnapyk" />
													<div class="box-line-visib col-xs-6">machin.org</div>
													<button class="but but-delete but-error but-fh"></button>
												</div>
												<div class="box-line row">
													<button class="but but-add but-fh"></button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="box col-md-6">
								<div class="box-content bloc col-md-12">
									<div class="container-fluid">
										<div class="box-head row">
											<span class="box-title">Mot de passe</span>
											<div class="box-head-right">
												<select class="field" disabled>
													<option>Chiffré</option>
												</select>
											</div>
										</div>
										<div class="box-body row">
											<div class="container-fluid">
												<div class="box-line row">
													<input type="text" class="box-line-ip field field-main col-xs-6"
														   value="SHA-512" readonly />
													<div class="box-line-visib col-xs-6">Principal | Privé</div>
												</div>
												<div class="box-line row">
													<input type="text" class="box-line-ip field col-xs-6"
														   value="SHA-1" readonly />
													<div class="box-line-visib col-xs-6">google.com, truc.fr</div>
													<button class="but but-delete but-error but-fh"></button>
												</div>
												<div class="box-line row">
													<input type="text" class="box-line-ip field col-xs-6"
														   value="SHA-32" readonly />
													<div class="box-line-visib col-xs-6">machin.org</div>
													<button class="but but-delete but-error but-fh"></button>
												</div>
												<div class="box-line row">
													<button class="but but-add but-fh"></button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="box col-md-6">
								<div class="box-content bloc col-md-12">
									<div class="container-fluid">
										<div class="box-head row">
											<span class="box-title">Prénom</span>
											<div class="box-head-right">
												<select class="field">
													<option>String</option>
													<option>Entier</option>
													<option>Booléen</option>
												</select>
												<button class="but but-delete but-error but-fh"></button>
											</div>
										</div>
										<div class="box-body row">
											<div class="container-fluid">
												<div class="box-line row">
													<input type="text" class="box-line-ip field col-xs-6"
														   value="Bernard" />
													<div class="box-line-visib col-xs-6">Publique</div>
													<button class="but but-delete but-error but-fh"></button>
												</div>
												<div class="box-line row">
													<input type="text" class="box-line-ip field col-xs-6"
														   value="Georges" />
													<div class="box-line-visib col-xs-6">google.com, truc.fr</div>
													<button class="but but-delete but-error but-fh"></button>
												</div>
												<div class="box-line row">
													<input type="text" class="box-line-ip field col-xs-6"
														   value="Gérard" />
													<div class="box-line-visib col-xs-6">machin.org</div>
													<button class="but but-delete but-error but-fh"></button>
												</div>
												<div class="box-line row">
													<button class="but but-add but-fh"></button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>

				<footer class="footer">

				</footer>
			</div>
		</div>

		<script>
			var GLOBALS = {
				titre_main: "${titre_main}"
			};
		</script>
    </body>
</html>
