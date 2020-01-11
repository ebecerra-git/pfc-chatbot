<!DOCTYPE html>
<html>
	<head>
		<title>AIML's</title>
		<#include "imports/head.ftl">
	</head>
	<body class="bg-secondary">
		<div class="container">
			<#include "areas/header.ftl">
			<#include "areas/navegacion-general.ftl">
			<div class="contenido sombreado-caja rounded">
				<div class="d-flex flex-column align-items-start overflow-auto">
				<#list sets as set>
					<div class="alert alert-success p-2" >
						${set}
					</div>
				</#list>
				</div>
			</div>
		</div>
		<#include "imports/import-js.ftl">
	</body>
</html>