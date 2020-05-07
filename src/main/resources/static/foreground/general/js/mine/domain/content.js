/**
 *@Description Vue全局变量
 *@author CiaoLee
 */
let contentVue = new Vue({
    el: "#content-area",
    data: {
        content: {},
        comments: []
    },
    beforeCreate() {
        getSpecifiedContent();
    },
    mounted() {
        contentHitsUp();
        getSpecifiedComments();
    }
});

/**
 *@Description 绑定事件
 *@author CiaoLee
 */
/* “发布评论”模态框按钮 */
$("#comment-btn").click(function() {
    $("#nickname").val("");
    $("#comment").val("");
    $("#phone-number").val("");
    $("#nickname-warning").hide();
    $("#comment-warning").hide();
    $("#phone-number-warning").hide();
    $("#comment-modal").addClass("in");
});
/* 昵称输入框 */
$("#nickname").focus(function() {
    $("#nickname-warning").hide();
});
/* 评论输入框 */
$("#comment").focus(function() {
    $("#comment-warning").hide();
});
/* 手机号输入框 */
$("#phone-number").focus(function() {
    $("#phone-number-warning").hide();
});

/**
 *@Description 查询当前文章详情
 *@param contentId
 *@author CiaoLee
 */
function getSpecifiedContent() {
    $.ajax({
        url: "/content/getSpecifiedContent",
        type: "get",
        data: {"id": contentId},
        success: function (rtnData) {
            contentVue.content = rtnData.data;
        }
    });
}

/**
 *@Description 文章点击量增加
 *@private contentId 文章主键（全局变量）
 *@author CiaoLee
 */
function contentHitsUp() {
    $.ajax({
        url: "/content/contentHitsUp",
        type: "post",
        data: {"contentId": contentId}
    });
}

/**
 *@Description 游客发布评论
 *@private contentId 文章主键（全局变量）
 *@author CiaoLee
 */
function visitorCommentPublish() {
    /* 获取参数 */
    let nickname = $("#nickname").val();
    let comment = $("#comment").val();
    let phoneNumber = $("#phone-number").val();
    /* 获取警告框 */
    let nicknameWarning = $("#nickname-warning");
    let commentWarning = $("#comment-warning");
    let phoneNumberWarning = $("#phone-number-warning");
    /* 参数验证 */
    let flag = 1;
    if(nickname === "") {
        nicknameWarning.html("请输入昵称");
        nicknameWarning.show();
        flag = 0;
    }
    if(comment === "") {
        commentWarning.html("请输入评论");
        commentWarning.show();
        flag = 0;
    }
    if(nickname.length > 20) {
        nicknameWarning.html("昵称长度请保持在20字以内");
        nicknameWarning.show();
        flag = 0;
    }
    if(comment.length > 80) {
        commentWarning.html("评论字数请控制在80字以内");
        commentWarning.show();
        flag = 0;
    }
    if(phoneNumber!=="" && !(/^1[3456789]\d{9}$/.test(phoneNumber))) {
        phoneNumberWarning.html("请输入正确手机号，或者不填写手机号");
        phoneNumberWarning.show();
        flag = 0;
    }
    if(flag === 0) return;
    $.ajax({
        url: "/comment/visitorCommentPublish",
        type: "post",
        data: {"contentId": contentId, "creatorNickname":nickname, "mainContent":comment, "phoneNumber": phoneNumber},
        success: function(rtnData) {
            if(rtnData.code === "success") {
                alert("发布成功！");
                $('#comment-modal').modal('hide');
            }
            if(rtnData.code === "error") {
                alert("发布失败，请重试");
            }
        }
    });
}

/**
 *@Description 获取文章的评论
 *@private contentId 文章主键（全局变量）
 *@author CiaoLee
 */
function getSpecifiedComments() {
    $.ajax({
        url: "/comment/getSpecifiedComments",
        type: "get",
        data: {"contentId": contentId},
        success: function(rtnData) {
            if(rtnData.code === "success") {
                contentVue.comments = rtnData.data;
                $("#comment-num").html(rtnData.data==null?0:rtnData.data.length + " 回复");
            }
            if(rtnData.code === "error") {
                $(".ptilte").html("评论区加载失败，请刷新页面");
            }
        }
    });
}