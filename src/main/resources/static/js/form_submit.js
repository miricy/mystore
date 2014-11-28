$(document).on('submit', '.form', function (event) {
	event.preventDefault();

	var $form = $( this ), url = $form.attr( "action" );
	
	var posting = $.post( url, $(this).serialize() );
	posting.done(function( data ) {
	    if(data == "") {
	    	$("#yes").show();
	    } else {
	    	var $temp  = $('<div/>', {html:data});
	    	$("#not").empty();
	    	$("#not").html( $temp.remove('head').html() );
	    }
	    
	    if( $(".pergunta").is(":visible") )
	    	$(".pergunta").hide();
		
		$(".form").each (function(){
			this.reset();
		});
	});
});
