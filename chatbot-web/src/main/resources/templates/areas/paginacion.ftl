		<nav class="d-flex justify-content-center mt-2">
			<ul class="pagination m-0 p-0">
				<li class="page-item">
					<#if page.first>
						<span class="page-item disabled">
							<span class="page-link">
								<i class="fas fa-angle-double-left"></i>
							</span>
						</span>
					<#else>
						<a class="page-link" href="${page.url}?page=1"><i class="fas fa-angle-double-left"></i></a>
					</#if>
				</li>
				<li class="page-item">
					<#if !page.hasPrevious>
						<span class="page-item disabled">
							<span class="page-link">
								<i class="fas fa-angle-left"></i>
							</span>
						</span>
					<#else>
					<a class="page-link" href="${page.url}?page=${page.paginaActual-1}"><i class="fas fa-angle-left"></i></a>
					</#if>
				</li>
				<#list page.paginas as pagina>
					<li class="page-item">
						<#if pagina.numero == page.paginaActual>
							<span class="page-item active">
								<span class="page-link">
									${pagina.numero}
								</span>
							</span>
						<#else>
						<a class="page-link" href="${page.url}?page=${pagina.numero}">${pagina.numero}</a>
						</#if>
					</li>
				</#list>
				<li class="page-item">
					<#if !page.hasNext>
						<span class="page-item disabled">
							<span class="page-link">
								<i class="fas fa-angle-right"></i>
							</span>
						</span>
					<#else>
					<a class="page-link" href="${page.url}?page=${page.paginaActual+1}"><i class="fas fa-angle-right"></i></a>
					</#if>
				</li>
				<li class="page-item">
					<#if page.last>
						<span class="page-item disabled">
							<span class="page-link">
								<i class="fas fa-angle-double-right"></i>
							</span>
						</span>
					<#else>
					<a class="page-link" href="${page.url}?page=${page.totalPaginas}"><i class="fas fa-angle-double-right"></i></a>
					</#if>
				</li>
			</ul>
		</nav>