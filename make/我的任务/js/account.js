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

$(function(){
	$(".buttonUsual3").click(function(){
		$(".xieyiBj").css("display","block");
		$(".xieyi").css("display","block");
		})
	$(".xieyi h3 a").click(function(){
		$(".xieyiBj").css("display","none");
		$(".xieyi").css("display","none");
		})
	$(".tab_title li a").click(function(){
		var titleIndex=$(this).parent().index();	
	    $(this).addClass("current").parent().siblings().children().removeClass("current");
		$(".tab_main li").eq(titleIndex).css({"display":"block"}).siblings().css({"display":"none"});
		})	
	
	})