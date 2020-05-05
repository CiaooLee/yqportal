/**
 *@Description Vue全局变量
 *@author CiaoLee
 */
/* Content内容Vue */
let contentVue = new Vue({
    el: "#content-area",
    data: {
        caseTopNine: [],
        newsTopFour: [],
        forumTopFour: []
    },
    beforeCreate() {
        /* 获取首页内容-各版块TopX */
        portalContentOnload();
    }
});

/**
 *@Description 获取首页内容-各版块TopX
 *@author CiaoLee
 */
function portalContentOnload() {
    $.ajax({
        url: "/content/portalContentOnload",
        type: "get",
        success: function (rtnData) {
            if(rtnData.code === "success") {
                let data = rtnData.data;
                contentVue.caseTopNine = data["case"];
                contentVue.newsTopFour = data["news"];
                contentVue.forumTopFour = data["forum"];
            }
            if(rtnData.code === "error") {
                alert("服务器错误，请刷新");
            }
        }
    });
}