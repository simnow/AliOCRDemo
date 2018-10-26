$(function() {
    var paths = decodeURI(window.location);
    $(".search-people .btn").on('click', function() {
      if (paths.indexOf("结果") != '-1') {
        if ($(".search-people .searches").val().length <= 0) {
          window.location.href = encodeURI( "专家搜索结果页.html");
        } else {
          window.location.href = encodeURI( "专家搜索结果页.html?search=" + $(".search-people .searches").val());
        }
      } else {
        if ($(".search-people .searches").val().length <= 0) {
          return;
        }
        window.location.href = encodeURI( "专家搜索结果页.html?search=" + $(".search-people .searches").val());
      }
    });
});