/**
 *@Description 全局变量
 *@author CiaoLee
 */
let gifs = $(".brief-img");

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
    },
    mounted() {
        let that = this;
        /* 替换第一行gif */
        firstLineGifReplace();
        /* 替换第二行gif */
        setTimeout(secondLineGifReplace, 2000);
        /* 替换“实时信息”4张图 */
        setTimeout(newsImgReplace, 3000);
        /* 替换“讨论交流”4张图 */
        setTimeout(forumImgReplace, 3000);
        /* 替换“经典案例”9张图 */
        setTimeout(caseImgReplace, 3000);
    }
});

/**
 *@Description 替换第一行gif
 *@author CiaoLee
 */
function firstLineGifReplace() {
    for(let i=0; i<4; i++) {
        $(gifs[i]).attr("src", "foreground/general/img/portal/gif-"+i+".gif");
    }
}

/**
 *@Description 替换第二行gif
 *@author CiaoLee
 */
function secondLineGifReplace() {
    for(let i=4; i<8; i++) {
        $(gifs[i]).attr("src", "foreground/general/img/portal/gif-"+i+".gif");
    }
}

/**
 *@Description 替换“实时信息”4张图
 *@author CiaoLee
 */
function newsImgReplace() {
    let newsImgs = $(".news-img");
    for(let i=0; i<newsImgs.length; i++) {
        $(newsImgs[i]).attr("src", "foreground/general/img/portal/news-"+i+".jpg");
    }
}

/**
 *@Description 替换“讨论交流”4张图
 *@author CiaoLee
 */
function forumImgReplace() {
    let forumImgs = $(".forum-img");
    for(let i=0; i<forumImgs.length; i++) {
        $(forumImgs[i]).attr("src", "foreground/general/img/portal/forum-"+i+".jpg");
    }
}

/**
 *@Description 替换“经典案例”9张图
 *@author CiaoLee
 */
function caseImgReplace() {
    let caseImgs = $(".case-img");
    for(let i=0; i<caseImgs.length; i++) {
        $(caseImgs[i]).attr("src", "foreground/general/img/portal/case-"+i+".jpg");
    }
}

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