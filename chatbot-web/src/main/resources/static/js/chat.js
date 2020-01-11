$(document).ready(function(){
	$('.mensajes').prop("scrollTop", $('.mensajes').prop("scrollHeight"));
	
	 $("#mensaje").keypress(function (e) {
		if(e.which == 13) {
		    e.preventDefault();
		    $("#form-mensaje").submit();
	    }
	});
	 
	 document.getElementById("mensaje").focus()
});