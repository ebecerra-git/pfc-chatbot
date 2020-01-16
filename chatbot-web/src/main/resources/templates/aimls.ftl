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
				<#list enlaces as enlace>
					<a href="aimls/${enlace!}" title="Archivo AIML ${enlace!}">
						<div class="alert alert-success pt-2 pb-2 mb-3" >
							${enlace!}
						</div>
					</a>
				</#list>
				</div>
			</div>
		</div>
		<#include "imports/import-js.ftl">
	</body>
</html>