<!DOCTYPE html>
<html>
	<head>
		<title>${titulo}</title>
		<#include "imports/head.ftl">
		<link rel="stylesheet" href="/css/estilos-chat.css">
	</head>
	<body class="bg-secondary">
		<div class="container">
			<#include "areas/header.ftl">
			<#include "areas/navegacion-general.ftl">
			<#include "areas/area-conversacion.ftl">
			<#include "areas/navegacion-conversaciones.ftl">
		</div>
		<#include "imports/import-js.ftl">
	</body>
</html>