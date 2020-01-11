			<div class="contenido d-flex flex-column sombreado-caja rounded">
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
			</div>