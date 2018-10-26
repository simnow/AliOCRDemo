var title=location.href;
var newsid=title.split("?id=");
	$.ajax({
		type:"get",
		url:'/bswebsite/api/news'+"/"+newsid[1],
		dataType : "json",
	    success:function(data){
	    		console.log(data);
	    		showNews(data);
	    	}
		});
	function showNews(data){
		var news=data["obj"];
		$("#title").text(news.newstitle);
		$("#content").text(news.newscontent);
	};