<!DOCTYPE html>
<html>
	<head>
		<title>Conversaciones</title>
	</head>
	<body>
		<div class="container">
			Conversaciones
			<#list conversaciones as conversacion>
				${conversacion.id!}
			</#list>
		</div>
	</body>
</html>