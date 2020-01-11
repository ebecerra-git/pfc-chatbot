			<div class="caja-texto sombreado-caja">
				<form id="form-mensaje" action="/" method="post"> 
					<div class="talk input-group">
						<textarea id="mensaje" name="mensaje" class="form-control noresize" rows="3" placeholder="Esbribe tu mensaje..."></textarea>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<div name="_csrf" class="input-group-append">
							<button class="input-group-text">
								<i class="far fa-paper-plane"></i>
							</button>
						</div>
					</div>
				</form>
			</div>