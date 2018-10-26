 /*战略院动态
 * id spanztitle
 * id spanzcontent
 * id spanzdm
 * id spanzdd*/

/*id号    u129_img 战略院动态图片
 *id号    u236_img 社会热点图片*/

/*社会热点id spanrtitle1
 *     id spanrcontent1
 *     id spanrdate
 *     id spanrtitle2*/


/*科普知识   id spanktitle1
 *      id spankcontent1
 *      id spankdate
 *      */

/*消息    id spanmtitle
 *    id spanmcontent
 *    id spanmdate*/



/*$("#u129_img").attr("src","images/index/u236.jpg");
 * newsabstract

*/
/*战略院动态*/
$.ajax({
	type:"get",
	url:'/bswebsite/api/news',
	dataType : "json",
    success:function(data){
    		/*console.log(data);
    		console.log(data['attributes'].alist[0].id)*/
    		addBox(data);
    	}
	});
/*返回一个对象*/
function addBox(data){
	var zlist=data['attributes'].alist;
	var rlist=data['attributes'].blist;
	var klist=data['attributes'].dlist;
	var mlist=data['attributes'].clist;
	for (var int = 0; int < zlist.length; int++) {
		var span="spanztitle";
		var span1="spanzcontent";
		var span2="spanzdm";
		var span3="spanzdd";
		var newsid=' <input type="hidden" value="'+ zlist[int].id +'">';
		span="#"+span+(int);
		span1="#"+span1+(int);
		span2="#"+span2+(int);
		span3="#"+span3+(int);
		$(span).html(zlist[int].newstitle+newsid);
		if (int==0) {
			console.log(int);
			console.log(zlist[int].newsimg);
			$("#u129_img").attr("src",zlist[int].newsimg);
			$(span1).text(zlist[int].newsabstract.substring(0,100));
			continue;
		}
		$(span1).text(zlist[int].newsabstract.substring(0,42));
		span1=span1+"1";
		$(span1).text(zlist[int].newsabstract.substring(42,84));
		var datem=zlist[int].publishtime.substring(5,7);
		var dated=zlist[int].publishtime.substring(8);
		$(span2).text(datem);
		$(span3).text(dated);
	}
	for (var int = 0; int < rlist.length; int++) {
		var span="spanrtitle";
		var span1="spanrcontent";
		var span2="spanrdate";
		
		var newsid=' <input type="hidden" value="'+ rlist[int].id +'">';
		span="#"+span+(int+1);
		span1="#"+span1+(int+1);
		span2="#"+span2+(int+1);
		if (int==0) {
			$("#u236_img").attr("src",rlist[int].newsimg);
		}
		$(span).html(rlist[int].newstitle+newsid);
		$(span1).text(rlist[int].newsabstract.substring(0,70)+"......");
		var date=rlist[int].publishtime.substring(5);
		$(span2).text(date);
	}
	for (var int = 0; int < klist.length; int++) {
		var span="spanktitle";
		var span1="spankcontent";
		var span2="spankdate";
		var newsid=' <input type="hidden" value="'+ klist[int].id +'">';
		span="#"+span+(int+1);
		span1="#"+span1+(int+1);
		span2="#"+span2+(int+1);
		$(span).html(klist[int].newstitle+newsid);
		$(span1).text(klist[int].newsabstract.substring(0,60)+"......");
		var date=klist[int].publishtime.substring(5);
		$(span2).text(date);
	}
	for (var int = 0; int < mlist.length; int++) {
		var span="spanmtitle";
		var span1="spanmdate";
		var newsid=' <input type="hidden" value="'+ mlist[int].id +'">';
		span="#"+span+(int+1);
		span1="#"+span1+(int+1);
		$(span).html(mlist[int].newstitle+newsid);
		$(span1).text(mlist[int].publishtime);
	}
}
/*图片活动播放 id u498_img  最前面的页面
 * 		  id u494_img  左侧页面
 * 		  id u496_img  右侧页面*/
	var img1="images/index/66_u494.jpg";
	var img2="images/index/77_u496.png";
	var img3="images/index/88_u498.jpg";
	var num=3;
	/*移动板块
	$(imgId).attr("src",img1);*/
	setInterval(Playback,3000);
	function Playback(){
		var numId=492;
		var img4="";
		for (var int = 0; int < 3; int++) {
			var imgId="#u";
			var imgm="_img"
			num=(num+1)%3;
			numId=numId+2;
			imgId=imgId+numId+imgm;
			var img="img";
			img=img+(num);
			if (img=="img1") {
				img4=img1;
			}else if(img=="img2"){
				img4=img2;
			}else if(img=="img0"){
				img4=img3;
			}
			$(imgId).attr("src",img4);
		}
		num=num%3+1;
	};
	//$("#spanlian").click(OngoingNews());
	function OngoingNews(title){
		var titleN=title.split("&");
		var titlename=titleN[0].trim()+".html";
		location.href=titlename;
		 event.stopPropagation();
	}
	function showNewsByTitle(id){
		//var id4=id.substring(4,5);
		id="#"+id;
		var newsid=$(id).find('input').val();
		var htmlName="新闻详情.html"
		//var htmlName;
		/*switch (id4)
		{
		case "z":
		  htmlName="战略院动态.html";
		  break;
		case "r":
		  htmlName="社会热点.html";
		  break;
		case "k":
		  htmlName="科普知识.html";
		  break;
		case "m":
		  htmlName="通知公告.html";
		  break;
		}*/
		htmlName=htmlName+"?id="+newsid;
		location.href=htmlName;
		event.stopPropagation();
	}
	function chainedAddress(id){
		var id4=id.substring(4,5);
		var htmlName;
			switch (id4)
		{
		case "z":
		  htmlName="战略院动态.html";
		  break;
		case "r":
		  htmlName="社会热点.html";
		  break;
		case "k":
		  htmlName="科普知识.html";
		  break;
		case "y":
		  htmlName="院士咨询.html";
		  break;
		case "h":
		  htmlName="战略咨询.html";
		  break;
		}
		location.href=htmlName;
		event.stopPropagation();
	}
	function BusAddress(id){
		var name=id.substring(0,1);
		console.log(name);
  		var htmlName;
  			switch (name)
		  		{
		  		case "k":
		  		  htmlName="知识服务.html";
		  		  break;
		  		case "w":
		  		  htmlName="智慧服务.html";
		  		  break;
		  		case "c":
		  		  htmlName="战略咨询.html";
		  		  break;
		  		case "n":
		  		  htmlName="集成创意.html";
		  		  break;
		  		}
  		location.href=htmlName;
  		event.stopPropagation();
  	}


























