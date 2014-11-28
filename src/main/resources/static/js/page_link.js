$(document).on("click", ".popup", function(event){
	event.preventDefault();
	var url = $(this).attr("href");
	var target = $(this).data("target");
	$.get(url, function(data){
		var $temp  = $('<div/>', {html:data});
		var dialog_box = $( '#'+target );
		$(dialog_box).dialog();
		$(dialog_box).find('.content').empty();
		$(dialog_box).find('.content').html( $temp.remove('head').html() );
		$(dialog_box).dialog('option', 'title', $temp.find('title').text());
		$(dialog_box).dialog('option', 'minWidth', '640');
		$(dialog_box).dialog('option', 'minHeight', '480');
		$(dialog_box).dialog( "open" );
	});
});

$(document).on("click", ".link", function(event){
	event.preventDefault();
	var link = $(this).data("action");
	$.ajax({
		type: 'GET',
		url: link
	}).done(function(data){
		var $temp  = $('<div/>', {html:data});
		$("div#main_container").empty();
		$("div#main_container").html( $temp.remove('head').html() );
	});
});
