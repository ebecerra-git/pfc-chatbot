<!DOCTYPE html>
<html lang="es">
	<head>
		<title>${titulo}</title>
		<#include "imports/head.ftl">
		<link rel="stylesheet" href="/css/estilos-chat.css">
	</head>
	<body class="bg-secondary">
		<div class="container">
			<#include "areas/header.ftl">
			<#include "areas/area-conversacion.ftl">
			<#include "areas/form-chat.ftl">
		</div>
		<#include "imports/import-js.ftl">
		<script src="/js/chat.js"></script>
	</body>
</html>