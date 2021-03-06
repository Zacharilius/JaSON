function sendAjax() {
	console.log("Started");
    // get inputs
    var json = new Object();
    json.fName = $('#fName').val();
    json.lName = $('#lName').val();
    json.url = $('#url').val();
    json.movies = $('#movies').val().split(";");
    json.tvshows = $('#tvshows').val().split(";");
 
    console.log("Finished creating variables");
    console.log(JSON.stringify(json));
    $.ajax({
        url: "ServletJSON",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(json),
        contentType: 'application/json',
        mimeType: 'application/json',
 
        
        
        success: function (data) {
        	console.log("Started success");
            $("tr:has(td)").remove();
 
            $.each(data, function (index, json) {
 
                var td_movies = $("<td/>");
                $.each(json.movies, function (i, tag) {
                    var span = $("<span class='label label-info' style='margin:4px;padding:4px' />");
                    span.text(tag);
                    td_movies.append(span);
                });
 
                var td_tvshows = $("<td/>");
                $.each(json.tvshows, function (i, tag) {
                    var span = $("<span class='label label-info' style='margin:4px;padding:4px' />");
                    span.text(tag);
                    td_tvshows.append(span);
                });
 
                $("#added-jasons").append($('<tr/>')
                        .append($('<td/>').html(json.fName))
                        .append($('<td/>').html(json.lName))
                        .append(td_movies)
                        .append(td_tvshows)
                );
 
            }); 
        },
        error:function(data,status,er) {
        	console.log("Error function started");
            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });
}