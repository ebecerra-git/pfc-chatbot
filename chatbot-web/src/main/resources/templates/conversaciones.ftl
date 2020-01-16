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
				<#list conversaciones as conversacion>
					<a class="d-flex" href="${page.url!}/${conversacion.id!}" title="Conversacion ${conversacion.id!}">
						<div class="alert alert-success pt-2 pb-2 mb-3 mr-2" >
							${conversacion.id!}
						</div>
						<div class="alert alert-success pt-2 pb-2 mb-3" >
							${conversacion.fecha!}
						</div>
							
					</a>
				</#list>
				</div>
			</div>
			<#include "areas/paginacion.ftl">
		</div>
		<#include "imports/import-js.ftl">
	</body>
</html>