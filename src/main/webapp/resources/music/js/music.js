var currentIndex = 0;
var nowTime = 0;
//记录当前播放进度
var duration = 0;
//总时长
var html = '';
for (var i = 0; i < musics.length; i++) {
    var m = musics[i];

    html += '<div class="item" data-index="' + i + '">';
    html += '<div class="item-name">' + m.name + '</div>';
    html += '<div class="item-size">' + fmtSize(m.size) + '</div>';
    html += '</div>';

}
document.querySelector('.list').innerHTML = html;
function fmtSize(size) {
    return (size / (1024 * 1024)).toFixed(1) + 'MB';
}
var player = document.getElementById('player');
var items = document.querySelectorAll('.item');
for (var i = 0; i < items.length; i++) {
    items[i].addEventListener('click', function () {
        //将上一首歌曲的常亮取消
        items[currentIndex].className = 'item';
        //取消定时器
        clearInterval(flag);

        currentIndex = this.dataset.index;
        player.src = musics[currentIndex].path;
        startPlay();
        //将当前选中项激活
        this.className = 'item active';
    });
}
// 同步歌曲的播放进度和时间
// 监听播放器的数据加载完毕时间

player.addEventListener('loadeddata', function () {
    // 获取歌曲总播放时间
    duration = this.duration;
    // 获取歌曲当前进度
    now = this.currentTime;
    document.querySelector('.current-time').innerHTML=fmtTime(now);
    document.querySelector('.total-time').innerHTML=fmtTime(duration);
})
// 将传入的进度（秒）转换为分秒格式(mm:ss)
function fmtTime(time) {
    //根据传入的时间构造一个日期对象
    var date = new Date(time * 1000);
    //  取出当前日期所表示的分钟数
    var minutes = date.getMinutes();
    // 秒
    var seconds = date.getSeconds();
    seconds = seconds >= 10?seconds:'0'+seconds;
    return minutes + ':' + seconds;
}
var flag;//定时器标记
function startPlay(){

    // 开始播放歌曲
    player.play();
    // 将歌曲信息展示在底部(歌曲名和状态)
    document.querySelector('.music-name').innerHTML = musics[currentIndex].name;
    document.querySelector('.btn-control').style.backgroundImage = 'url(img/暂停.png)';
    // 启动进度更新
    flag = window.setInterval(refreshState,1000);
}
// 更新歌曲的播放状态(修改进度，修改当前时间)
function refreshState(){
    // 获取最新的歌曲进度
    now = player.currentTime;
    // 更新时间
    document.querySelector('.current-time').innerHTML=fmtTime(now);
    // 计算当前进度
    var progress = now/duration * 100 + '%';
    // 将进度值作为进度条的宽度
    console.info(progress);
    document.querySelector('.progress').style.width = progress;
}
    
//当歌曲播放完毕以后自动切
player.addEventListener('ended',function(){
    // 取消上一首歌曲的列表项高亮状态
    items[currentIndex].classNmae = 'item';
    clearInterval(flag);
    currentIndex++;
    // 防止下一首越界
    if (currentIndex>musics.length)
    {
        currentIndex=0;
         // 设置当前歌曲列表项高亮
    items[currentIndex].classNmae = 'item active';
    }
    player.src = musics[currentIndex].path;
    startPlay();
})
// 为按钮绑定点击事件(控制歌曲的暂停和播放)
document.querySelector('.btn-control').addEventListener('click',function(){
    if(player.paused)
    {
        startPlay();
    }
    // 播放
    else{
        // 暂停
        player.pause();
        clearInterval(flag);
        document.querySelector('.btn-control').style.backgroundImage = 'url(img/play-sm.png)';
    }

});