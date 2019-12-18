<!DOCTYPE html>
<html>
	<head>
		<title>Chatbot</title>
		<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="/css/estilos-chat.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" 
	      integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" 
	      crossorigin="anonymous" />
	</head>
	<body>
		<div class="container">
			<div class="chat d-flex flex-column">
				<header class="cabecera">	
					<h1><i class="fas fa-robot"> ${titulo}</i></h1>
				</header>
				<div class="mensajes d-flex flex-column overflow-auto">
					<#list conversacion as mensaje>
					<div class="d-flex flex-column mensaje <#if mensaje.mensajeBot>mensaje-bot<#else>mensaje-humano</#if>">
						<span class="<#if mensaje.mensajeBot>align-self-start<#else>align-self-end</#if>">
							${mensaje.fechaEnvio?time!}
						</span>
						<div class="<#if mensaje.mensajeBot>alert alert-secondary align-self-start<#else>alert alert-success align-self-end</#if>">
							<p>${mensaje.texto!}</p>
						</div>
					</div>
					</#list>
				</div>
				<div class="caja-texto">
					<form id="form-mensaje" action="" method="post"> 
						<div class="talk input-group">
							<textarea id="mensaje" name="mensaje" class="form-control noresize" rows="3" placeholder="Esbribe tu mensaje..."></textarea>
							<div class="input-group-append">
								<button class="input-group-text">
									<i class="far fa-paper-plane"></i>
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script src="/jquery/jquery-3.4.1.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="/bootstrap/js/bootstrap.min.js"></script>
		<script src="/js/chat.js"></script>
	</body>
</html>