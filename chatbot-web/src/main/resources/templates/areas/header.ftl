			<header class="d-flex justify-content-between cabecera sombreado-caja bg-dark text-light flex-wrap rounded-bottom">
				<div class="align-self-center">
					<h1 class="h3"><i class="fas fa-robot text-success"></i> ${titulo}</h1>
					<p class="text-muted">${estado!"Esperarndo resolver tus dudas..."}</p>
				</div>
				<div id="ajustes" class="d-flex">
					<#if autenticado>
					<div id="menu" class="mt-2 ml-2">
						<div class="button" data-toggle="dropdown" aria-expanded="false" aria-haspopup="true" title="Menú"><i class="h2 fas fa-chevron-circle-down text-secondary"></i></div>
						<div class="dropdown-menu dropdown-menu-right bg-secondary">
							<div class="font-weight-bold text-dark pl-4 pb-1">Menú</div>
							<a class="dropdown-item bg-secondary text-light" href="/.."><i class="fas fa-robot"></i> Chat</a>
							<a class="dropdown-item bg-secondary text-light" href="/../admin/conversaciones"><i class="fas fa-comments"></i> Conversaciones</a>
							<#if autenticado && roles?join(" ")?contains("ROLE_ADMIN")>
							<a class="dropdown-item bg-secondary text-light" href="/../admin/bot/aimls"><i class="fas fa-code"></i> AIML's</a>
							<a class="dropdown-item bg-secondary text-light" href="/../admin/bot/config-files"><i class="fas fa-list"></i> Propiedades</a>
							<a class="dropdown-item bg-secondary text-light" href="/../admin/bot/sets"><i class="fas fa-ellipsis-v"></i> Sets</a>
							</#if>
						</div>
					</div>
					</#if>
					<div id="acciones" class="mt-2 ml-2">
						<div class="button" data-toggle="dropdown" aria-expanded="false" aria-haspopup="true" title="Opciones"><i class="h2 fas fa-cog text-secondary"></i></div>
						<div class="dropdown-menu dropdown-menu-right bg-secondary">
							<#if autenticado>
								<div class="font-weight-bold text-center text-dark pl-4 pr-4 pb-1">Bienvenido ${user?cap_first!}</div>
								<a class="dropdown-item bg-secondary text-light" href="/logout"><i class="fas fa-sign-out-alt"></i> Cerrar sesión</a>
							<#else>
								<a class="dropdown-item bg-secondary text-light" href="/../admin"><i class="fas fa-user-circle"></i> Iniciar sesión</a>
							</#if>
							<#if pag == "aimls">
								<button class="dropdown-item bg-secondary text-light" data-toggle="modal" data-target="#import"><i class="fas fa-upload"></i> Importar Excel</button>
							</#if>
							<#if pag == "chat">
								<a class="dropdown-item bg-secondary text-light" href="/..?format=pdf" target="blank"><i class="fas fa-save"></i> Exportar conversación</a>
								<a class="dropdown-item bg-secondary text-light" href="/..?action=delete"><i class="fas fa-trash-alt"></i> Borrar conversación</a>
							<#elseif pag == "conversacion">
								<a class="dropdown-item bg-secondary text-light" href="/../admin/conversaciones/${id!}?format=pdf" target="blank"><i class="fas fa-save"></i> Exportar conversación</a>
							</#if>
						</div>
					</div>
				</div>
			</header>
			<div class="modal fade" id="import" role="dialog" tabindex="-1">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Modal title</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<form id="form-import" action="/admin/bot/aimls?action=import" method="post" enctype="multipart/form-data">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<div class="modal-body">
								<div class="input-group">
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="file" name="file" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
										<label class="custom-file-label select-label" for="file">Seleccionar fichero</label>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div name="_csrf">
									<button type="submit" class="btn btn-outline-secondary" type="button">Importar</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>