<!DOCTYPE html>
<html>
	<head>
		<title>${titulo}</title>
		<#include "imports/head.ftl">
	</head>
	<body class="bg-secondary">
		<div class="container">
			<#include "areas/header.ftl">
			<#include "areas/navegacion-general.ftl">
			<div class="contenido sombreado-caja rounded">
				<div class="d-flex flex-column align-items-start overflow-auto">
				<#list configFiles as configFile>
					<#if configFile.key != "">
					<div class="alert alert-success p-2" >
						<#if configFile.key != configFile.value>
						${configFile.key} : ${configFile.value}
						<#else>
						${configFile.key}
						</#if>
					</div>
					</#if>
				</#list>
				</div>
			</div>
		</div>
		<#include "imports/import-js.ftl">
	</body>
</html>