		<nav class="navbar navbar-expand-md navbar-dark bg-dark sombreado-caja rounded text-center justify-content-center p-1 mt-2">
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Menú de navegación">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse text-center justify-content-center" id="navbarNavDropdown">
				<ul class="navbar-nav bg-dark w-100 m-0 p-0 justify-content-around">
					<li class="nav-item <#if pag! = "chat">active</#if>">
						<a class="nav-link" href="/">Chat</a>
					</li>
					<li class="nav-item-dark <#if pag! = "conversaciones">active</#if>">
						<a class="nav-link" href="/admin/conversaciones">Conversaciones</a>
					</li>
					<li class="nav-item">
						<a class="nav-link <#if pag! = "aimls">active</#if>" href="/admin/bot/aimls">AIML's</a>
					</li>
					<li class="nav-item">
						<a class="nav-link <#if pag! = "config-files">active</#if>" href="/admin/bot/config-files">Propiedades</a>
					</li>
					<li class="nav-item">
						<a class="nav-link <#if pag! = "sets">active</#if>" href="/admin/bot/sets">Sets</a>
					</li>
				</ul>
			</div>
		</nav>