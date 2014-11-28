function load_content(url) {
	$("tbody.content").empty();

	var atributos = [];
	$("thead").find("th").each(function(){
		atributos.push($(this).text());
	});

	$.get(url, function(data) {
		$.each(data, function(index, item) {
			var row = $('<tr>');
			for(var i=0; i<atributos.length; i++) {
				if(atributos[i] == '#')
					row.append('<td>'+item.id+'</td>');
				else if(atributos[i] == '...') {
				    var col = $('<td>');
				    var grupo = $('<div class="btn-group">');
				    $(".cmd").each(function(){
					    var nome = $(this).html();
					    var action = $(this).data("action") + "/" + item.id;
					    grupo.append('<button type="button" class="btn btn-default link" data-action="'+action+'">'+nome+'</button>');
				    });
				    col.append(grupo);
				    row.append(col);
				}
				else
					row.append('<td>'+item[atributos[i]]+'</td>');
			}
			$("tbody.content").append(row);
		});
	});
}
