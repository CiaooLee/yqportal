/**
 *@Description 初始化函数
 *@author CiaoLee
 */
$(function () {
    /* 打电话 */
    //window.location.href = "tel:13320337453";
    /* 加载百度地图 */
    mapOnload();
});

function mapOnload() {
    // 创建地图实例
    var map = new BMapGL.Map("map-container");
    // 创建点坐标
    var point = new BMapGL.Point(106.495918,29.543255);
    // 初始化地图，设置中心点坐标和地图级别
    map.centerAndZoom(point, 19);
    //开启鼠标滚轮缩放
    map.enableScrollWheelZoom(true);
    //向地图中添加信息窗口
    var marker = new BMapGL.Marker(point);  // 创建标注
    map.addOverlay(marker);              // 将标注添加到地图中
    var opts = {
        width : 240,     // 信息窗口宽度
        height: 100,     // 信息窗口高度
        title : "重庆雅秋文化传播有限公司" , // 信息窗口标题
        message:"雅秋文化"
    };
    var infoWindow = new BMapGL.InfoWindow("地址：重庆市九龙坡区渝州路珠江华轩。<br/>地铁一号线石桥铺站<br/>公交车138.222.462.467.823.827.839.871.870.873路等 均可到达", opts);  // 创建信息窗口对象
    map.openInfoWindow(infoWindow, point); //开启信息窗口
    marker.addEventListener("click", function(){
        map.openInfoWindow(infoWindow, point); //开启信息窗口
    });
}