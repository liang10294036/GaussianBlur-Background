/**
 * Created by lkk on 2016/9/26.
 */
//我的账户
$(function(){
    $(".shareLink a").click(function(event){
        event.stopPropagation();
        var $this = $(this).next("div");
        if($this.css("display") == "none"){
            $this.css("display","block");
        }else{
            $this.css("display","none");
        }
    })

})

/**
 * Created by lkk on 2016/9/26.
 */
$(function(){
    $('.czcont input[name=bank]').each(function(i,v){
        $(v).on('click',function(){
            $('.isExcess').css("display","block");
        })
    })
})
//我的账户
$(function(){
    $(".shareLink a").click(function(event){
        event.stopPropagation();
        var $this = $(this).next("div");
        if($this.css("display") == "none"){
            $this.css("display","block");
        }else{
            $this.css("display","none");
        }
    })
	
	
	$(".tabCz").click(function(){
		$(".czcont").css("height","65px");
	});
	$(".current").click(function(){
		$(".czcont").css("height","65px");
	})
})


//充值方式
$(function(){
    $(".tabCz").click(function() {
        $(this).addClass("current").siblings('li').removeClass("current");
        var index = $(this).index();
        $(".czContent > li")
            .eq(index)
            .addClass("showCurrent")
            .siblings("li")
            .removeClass("showCurrent");
    });
})

//tab栏切换
$(function(){
    $(".tab-item").click(function() {
        $(this).addClass("curColor").parent('li').siblings('li').children('a').removeClass("curColor");
        var index = $(this).parent('li').index();
        //console.log(index);
        $(".inforContent > li")
            .eq(index)
            .addClass("selected")
            .siblings("li")
            .removeClass("selected");
    });
})


$(function(){
    $(".tabItem").click(function() {
        $(this).addClass("curColor").parent('li').siblings('li').children('a').removeClass("curColor");
        var index = $(this).parent('li').index();
        console.log(index);
        $(".mainCon")
            .eq(index)
            .addClass("cZshow")
            .siblings()
            .removeClass("cZshow");
    });
})

//银行是否显示隐藏
$(function(){
    $('.isShowBotCon').click(function(){
        $(this).hide();
        $('.botImgBank').toggle();
    });
    $(".tabCz").click(function(){
		$(".czcont").css("height","65px");
	});
	$(".current").click(function(){
		$(".czcont").css("height","65px");
	})
})