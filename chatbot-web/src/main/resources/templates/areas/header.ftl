			<header class="d-flex justify-content-between cabecera sombreado-caja bg-dark text-light flex-wrap rounded-bottom">
				<div class="align-self-center">
					<h1 class="h3"><i class="fas fa-robot text-success"></i> ${titulo}</h1>
					<p class="text-muted">${estado!"Esperarndo resolver tus dudas..."}</p>
				</div>
				<div class="align-self-start">
					<div class="button" data-toggle="dropdown" aria-expanded="false" aria-haspopup="true" title="Opciones"><i class="h3 fas fa-cog text-secondary"></i></div>
					<div class="dropdown-menu dropdown-menu-right bg-secondary">
						<#if autenticado>
							<div class="font-weight-bold text-dark pl-4 pb-1">Bienvenido ${user?cap_first!}</div>
							<a class="dropdown-item bg-secondary text-light" href="/logout"><i class="fas fa-sign-out-alt"></i> Cerrar sesión</a>
							<a class="dropdown-item bg-secondary text-light" href="/.."><i class="fas fa-robot"></i> Chat</a>
							<a class="dropdown-item bg-secondary text-light" href="/../admin/conversaciones"><i class="fas fa-comments"></i> Conversaciones</a>
							<a class="dropdown-item bg-secondary text-light" href="/../admin/bot/aimls"><i class="fas fa-code"></i> AIML's</a>
							<a class="dropdown-item bg-secondary text-light" href="/../admin/bot/config-files"><i class="fas fa-list"></i> Propiedades</a>
							<a class="dropdown-item bg-secondary text-light" href="/../admin/bot/sets"><i class="fas fa-ellipsis-v"></i> Sets</a>
						<#else>
							<a class="dropdown-item bg-secondary text-light" href="admin/conversaciones"><i class="fas fa-user-circle"></i> Iniciar sesión</a>
						</#if>
						<a class="dropdown-item bg-secondary text-light" href="/..?action=export"><i class="fas fa-save"></i> Exportar conversación</a>
						<a class="dropdown-item bg-secondary text-light" href="/..?action=delete"><i class="fas fa-trash-alt"></i> Borrar conversación</a>
					</div>
				</div>
			</header>