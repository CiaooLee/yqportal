/**
 *@Description Vue全局变量
 *@author CiaoLee
 */
/* DomainColumn栏目Vue */
let domainColumnVue = new Vue({
    el: "#domain-column",
    data: {
        domainColumns: []
    },
    beforeCreate() {
        /* 加载DomainColumn */
        domainColumnOnload();
    },
    watch: {
        "domainColumns": function () {
            this.$nextTick(function () {
                /* 选取Domain并分页加载其Content */
                domainSwitch($("#" + currentDomain));
            });
        }
    }
});
/* Content内容Vue */
let contentVue = new Vue({
    el: "#content-area",
    data: {
        currentPage: {}
    }
});

/**
 * @Description 按钮点击高亮
 * @author CiaoLee
 */
function widgetHighlight(widget) {
    widget = $(widget);
    widget.addClass("active");
    widget.siblings().removeClass("active");
}

/**
 * @Description 获取DomainColumns
 * @author CiaoLee
 */
function domainColumnOnload() {
    $.ajax({
        url: "/domainColumn/getActiveList",
        type: "get",
        success: function(rtnData) {
            if(rtnData.code === "success") {
                domainColumnVue.domainColumns = rtnData.data;
                for(let i=0; i<rtnData.data; i++) {
                    let identifier = rtnData[i].identifier;
                    /* 初始化指定Identifier的Contents */
                    let typicalContents = contentVue.contents[identifier+"Contents"];
                    if(typicalContents == null) {
                        typicalContents = {};
                    }
                }
            }
            if(rtnData.code === "error") {
                alert("加载失败了，请刷新");
                location.href = "/";
            }
        }
    });
}

/**
 * @Description 切换板块 获取指定DomainColumns下的Contents
 * @param domainBtn 板块按钮
 * @author CiaoLee
 */
function domainSwitch(domainBtn) {
    /* 按钮高亮 */
    widgetHighlight($(domainBtn).parent());
    /* 获取Identifier 并把Identifier存入全局变量 */
    domainBtn = $(domainBtn);
    let identifier = domainBtn.attr("id");
    currentDomain = identifier;
    /* 刷新currentPageIndex */
    currentPageIndex = 1;
    /* 查询指定内容 */
    getSpecifiedPage(currentDomain, currentPageIndex);
}

/**
 * @Description 切换排序方式
 * @param sortBtn 排序按钮
 * @author CiaoLee
 */
function sortTypeBtn(sortBtn) {
    /* 排序按钮前端样式改变 */
    let specifiedBtn = $(sortBtn);
    specifiedBtn.addClass("active");
    specifiedBtn.siblings().removeClass("active");
    /* 把当前指定的排序方式 放入全局变量 */
    currentSortType = specifiedBtn.attr("id");
    /* 刷新currentPageIndex */
    currentPageIndex = 1;
    /* 查询指定内容 */
    getSpecifiedPage();
}

/**
 * @Description 分页加载指定板块的内容
 * @private currentDomain 当前板块标识符（全局变量）
 * @private currentPageIndex 当前页数（全局变量）
 * @private currentSortType 排序方式（全局变量）
 * @author CiaoLee
 */
function getSpecifiedPage() {
    /* 获取排序方式 */
    let sortType = currentSortType;
    /* 加载指定 排序方式&&栏目&&页数 的内容（若已经加载过 不做重复加载） */
    let contentsExpected = globalContents[currentDomain];
    //如果全局内容管理对象中有当前页 则直接拿给Vue渲染
    if(contentsExpected!=null && contentsExpected[sortType]!=null && contentsExpected[sortType][currentPageIndex]!=null) {
        contentVue.currentPage = contentsExpected[sortType][currentPageIndex];
        /* 获取指定栏目的页签栏 */
        getSpecifiedPagination();
        return;
    }
    /* 如果没有当前页 Compromise=>查库 */
    let params = {};
    params['sortType'] = sortType;
    params["identifier"] = currentDomain;
    params['pageIndex'] = currentPageIndex;
    $.ajax({
        url: "/content/getSpecifiedPage",
        type: "get",
        data: params,
        async: false,
        success: function(rtnData) {
            if(rtnData.code === "success") {
                /* 把本页内容存入Vue对象 用于直接渲染 */
                contentVue.currentPage = rtnData.data;
                /* 把本页内容存入全局内容管理对象 用于缓存 */
                let specifiedContents = globalContents[currentDomain];
                //如果指定Identifier下的内容都不存在 那么直接初始化
                if(specifiedContents == null) {
                    globalContents[currentDomain] = {};
                    globalContents[currentDomain][sortType] = {};
                    globalContents[currentDomain][sortType][currentPageIndex] = rtnData.data;
                }
                //如果指定Identifier下的内容存在 那么就继续看指定SortType下的内容存不存在
                if(specifiedContents != null) {
                    //如果指定SortType下的内容存在 那么就赋值当前页
                    if(specifiedContents[sortType] != null) {
                        globalContents[currentDomain][sortType][currentPageIndex] = rtnData.data;
                    }
                    //如果指定SortType下的内容不存在 那么就初始化SortType 然后再赋值当前页
                    if(specifiedContents[sortType] == null) {
                        globalContents[currentDomain][sortType] = {};
                        globalContents[currentDomain][sortType][currentPageIndex] = rtnData.data;
                    }
                }
            }
            if(rtnData.code === "error") {
                alert("加载失败了，请刷新");
                location.href = "/";
            }
        }
    });
    /* 获取指定栏目的页签栏 */
    getSpecifiedPagination();
}

/**
 * @Description 获取指定栏目页签栏数据
 * @private globalPagination 全局页签储存对象（全局变量）
 * @private currentDomain 当前板块标识符（全局变量）
 * @author CiaoLee
 */
function getSpecifiedPagination() {
    let pageCount = null;
    /* 从本地缓存中读取分页参数 */
    if(globalPagination[currentDomain]!=null && globalPagination[currentDomain]["pageCount"]!=null) {
        pageCount = globalPagination[currentDomain]["pageCount"];
        paginationPrint(pageCount);
        return;
    }
    /* Compromise=> 连接数据库查询页签栏分页参数 */
    $.ajax({
        url: "/content/getSpecifiedPagination",
        type: "get",
        data: {"identifier": currentDomain},
        async: false,
        success:function (rtnData) {
            if(rtnData.code === "success") {
                pageCount = rtnData.data.pageCount;
                /* 使用Pagination插件渲染页签栏 */
                paginationPrint(pageCount);
                /* 将数据存入全局变量缓存 */
                if(globalPagination[currentDomain] == null) {
                    globalPagination[currentDomain] = {};
                    globalPagination[currentDomain]["pageCount"] = pageCount;
                }
            }
            if(rtnData.code === "error") {
                alert("加载失败了，请刷新");
                location.href = "/";
            }
        }
    });
}

/**
 * @Description 绘制页签栏
 * @param pageCount 指定栏目的页数
 * @private currentPageIndex 当前页数（全局变量）
 * @private currentDomain 当前板块标识符（全局变量）
 * @author CiaoLee
 */
function paginationPrint(pageCount) {
    $(".pagination").pagination({
        pageCount: pageCount,
        current: currentPageIndex,
        count: 2,
        jump: true,
        coping: true,
        homePage: "首页",
        endPage: "末页",
        prevContent: "上页",
        nextContent: "下页",
        activeCls: "current-page",
        jumpIptCls: "jump-input",
        jumpBtnCls: "jump-btn",
        callback: function (api) {
            /* 回到页面顶部 */
            scrollTo(0,0);
            /* 加载内容数据 并跳转页面 */
            let pageIndex = api.getCurrent();
            currentPageIndex = pageIndex;
            getSpecifiedPage(currentDomain, pageIndex);
        }
    });
}