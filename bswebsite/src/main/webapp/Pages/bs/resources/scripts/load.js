$(".inport-header").load("header.html", function() {
	$("#base").show();
	var testUrl = window.location;
	var path = decodeURI(window.location);
	if (path.indexOf("新闻列表") != '-1') {
	var urls = window.location.search;
	var loc =  decodeURI(urls.substring(urls.lastIndexOf('=')+1, urls.length));
	console.log('222222', loc);
	$(".input-box .searches").val(loc);
	}
	});
$(".inport-footer").load("footer.html", function() {});

function show_index(url) {      
if (document.all) { 
document.body.style.behavior = 'url(#default#homepage)';        
document.body.setHomePage(url);         
} else {
alert("您好,您的浏览器不支持自动设置页面为首页功能,请您手动在浏览器里设置该页面为首页!");       
}   
} 

//加入收藏  
function show_Favorite(sURL, sTitle) {     
sURL = encodeURI(sURL);  
try { 
window.external.addFavorite(sURL, sTitle);
}catch (e) {         
try {
	window.sidebar.addPanel(sTitle, sURL, ""); 
}catch (e) {
	alert("加入收藏失败,请使用Ctrl+D进行添加,或手动在浏览器里进行设置.");
}        
}    
} 
$(function(){
let persent = 375/(1960-1202);
if (1960-document.body.clientWidth> 0 && 1960-document.body.clientWidth> 658) {
// console.log('1111111');
$('.move').css('left', -(280)+'px');
$('html').css('width',  1070 +'px');
$('body').css('width',  1960-365-323 +'px');
$('html').css('overflow-x', 'scroll');
$('html').css('marginRight', '0px');
$("#u313").css('width', 1685 + 'px');
$("#u313_div").css('width', 1685 + 'px');
$("#u0").css('width', 1685 + 'px');
$("#u0_div").css('width', 1685 + 'px');

} else if (1960-document.body.clientWidth > 0 && 1960-$('.body').width() < 658) {
// console.log('1122221');
$('.move').css('left', -(1952-document.body.clientWidth)/2+'px');
$('html').css('width', document.body.clientWidth+'px');
}  else if (1960-$('.body').width() < 0 ) {
$('.move').css('left', (1960-document.body.clientWidth)/2+'px');
$('html').css('width',document.body.clientWidth+'px');
}

});