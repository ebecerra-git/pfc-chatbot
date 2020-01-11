		<nav class="d-flex justify-content-center mt-2">
			<ul class="pagination m-0 p-0">
			
				<li class="page-item">
					<#if !hasPrevious>
						<span class="page-item disabled">
							<span class="page-link">
								<i class="fas fa-angle-double-left"></i>
							</span>
						</span>
					<#else>
						<a class="page-link" href="1"><i class="fas fa-angle-double-left"></i></a>
					</#if>
				</li>
				<li class="page-item">
					<#if !hasPrevious>
						<span class="page-item disabled">
							<span class="page-link">
								<i class="fas fa-angle-left"></i>
							</span>
						</span>
					<#else>
						<a class="page-link" href="${numConversacion-1}"><i class="fas fa-angle-left"></i></a>
					</#if>
				</li>
				<li class="page-item">
					<a class="page-link" href="/admin/conversaciones">Conversaciones</a>
				</li>
				<li class="page-item">	
					<#if !hasNext>
						<span class="page-item disabled">
							<span class="page-link">
								<i class="fas fa-angle-right"></i>
							</span>
						</span>
					<#else>
						<a class="page-link" href="${numConversacion+1}"><i class="fas fa-angle-right"></i></a>
					</#if>
				</li>
				<li class="page-item">	
					<#if !hasNext>
						<span class="page-item disabled">
							<span class="page-link">
								<i class="fas fa-angle-double-right"></i>
							</span>
						</span>
					<#else>
						<a class="page-link" href="${ultima}"><i class="fas fa-angle-double-right"></i></a>
					</#if>
				</li>
			</ul>
		</nav>