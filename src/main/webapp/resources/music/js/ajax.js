$(function () {
    $.ajax({
        method: 'get',
        url: 'http://www.softeem.top:8080/music/music?method=list',
        success: function (data) { 
            //将json字符串转换为js对象
            var musics = JSON.parse(data);
            //jquery提供的遍历方式
            $('.list').empty();
            $.each(musics, function (index, m) {
               var  html = '<div class="item" data-index="' + index + '">';
                html += '<div class="item-name">' + m.name + '</div>';
                html += '<div class="item-size">' + fmtSize(m.size) + '</div>';
                html += '</div>';
                $('.list').append(html);
            })
        }
      })
})
function fmtSize(size) {
    return (size / (1024 * 1024)).toFixed(1) + 'MB';
}
