/**
 *@Description 全局变量
 *@author CiaoLee
 */
var signModal = $("#sign-modal"); //登录/注册模态框
var modalSignInBtn = $(".modal-sign-button").eq(0); //模态框[登录]按钮最内层<a>标签
var modalSignUpBtn = $(".modal-sign-button").eq(1); //模态框[注册]按钮最内层<a>标签
var modalSignInForm = $("#signin-form"); //模态框登录表单区域
var modalSignUpForm = $("#signup-form"); //模态框注册表单区域

/**
 *@Description 绑定事件
 *@author CiaoLee
 */
/* 导航栏[登录]按钮-点击事件 */
$(".sign-in").click(function() {
    //显示模态框
    signModal.addClass("in");
    //显示登录按钮 并隐藏注册按钮
    modalSignInBtn.addClass("active");
    modalSignUpBtn.removeClass("active");
    //显示登录内容区域 并隐藏注册内容区域
    modalSignInForm.addClass("active");
    modalSignUpForm.removeClass("active");
});

/* 导航栏[注册]按钮-点击事件 */
$(".sign-up").click(function() {
    //显示模态框
    signModal.addClass("in");
    //显示注册按钮 并隐藏登录按钮
    modalSignUpBtn.addClass("active");
    modalSignInBtn.removeClass("active");
    //显示注册内容区域 并隐藏登录内容区域
    modalSignUpForm.addClass("active");
    modalSignInForm.removeClass("active");
});
