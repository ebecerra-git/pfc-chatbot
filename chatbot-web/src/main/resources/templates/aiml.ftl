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
				<div class="overflow-auto">
				<#list aimls as aiml>
					<div class="row alert alert-success text-center p-2" >
						<#assign lg = (aiml.topic?has_content || aiml.that?has_content || aiml.think?has_content || aiml.condition?has_content)>
						<#if aiml.topic?has_content>
							<div class="col-12 col-md-6 <#if lg>col-lg-4</#if>" 
								 data-toggle="tooltip" data-placement="top" title="Topic">
								 ${aiml.topic!}
							</div>
						</#if>
						<#if aiml.pattern?has_content>
							<div class="col-12 col-md-6 <#if lg>col-lg-4</#if> overflow-auto" style="max-height: 4.5em;"
								data-toggle="tooltip" data-placement="top" title="Pattern">
								${aiml.pattern!}
							</div>
						</#if>
						<#if aiml.that?has_content>
							<div class="col-12 col-md-6 <#if lg>col-lg-4</#if>"
								data-toggle="tooltip" data-placement="top" title="That">
								${aiml.that!}
							</div>
						</#if>
						<#if aiml.template?has_content>
							<div class="col-12 col-md-6 <#if lg>col-lg-4</#if> overflow-auto" style="max-height: 4.5em;"
								data-toggle="tooltip" data-placement="top" title="Template">
								${aiml.template!}
							</div>
						</#if>
						<#if aiml.think?has_content>
							<div class="col-12 col-md-6 <#if lg>col-lg-4</#if>"
								data-toggle="tooltip" data-placement="top" title="Think">
								${aiml.think!}
							</div>
						</#if>
						<#if aiml.condition?has_content>
							<div class="col-12 col-md-6 <#if lg>col-lg-4</#if>" 
								data-toggle="tooltip" data-placement="top" title="Condition">
								${aiml.condition!}
							</div>
						</#if>
					</div>
				</#list>
				</div>
			</div>
		</div>
		<#include "imports/import-js.ftl">
	</body>
</html>