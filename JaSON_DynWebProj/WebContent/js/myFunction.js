function sendAjax() {
	console.log("Started");
    // get inputs
    var json = new Object();
    json.fName = $('#fName').val();
    json.lName = $('#lName').val();
    json.url = $('#url').val();
    json.movies = $('#movies').val().split(";");
    json.tvShows = $('#tvShows').val().split(";");
 
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
 
                var td_tvShows = $("<td/>");
                $.each(json.tags, function (i, tag) {
                    var span = $("<span class='label' style='margin:4px;padding:4px' />");
                    span.text(tvShows);
                    td_tvShows.append(span);
                });
 
                $("#added-jasons").append($('<tr/>')
                        .append($('<td/>').html("<a href='"+jason.url+"'>"+jason.fName+jason.lName+"</a>"))
                        .append(td_movies)
                        .append(td_tvShows)
                );
 
            }); 
        },
        error:function(data,status,er) {
        	console.log("Error function started");
            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });
}