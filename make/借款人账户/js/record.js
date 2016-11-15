$(function(){
    $(".leixing li").click(function() {
        $(this).addClass("active").siblings().removeClass("active");
        var index = $(this).index();
        $(".tab")
            .eq(index)
            .addClass("active")
            .siblings(".tab")
            .removeClass("active");
    });
    $(".zhuangtai li a").click(function() {
        $(this).addClass("active").parent().siblings().children().removeClass("active");
    });
    /*$(".more").click(function(){
    	$(this).children("span").css("background","url(images/up.png) no-repeat center 6px");
    	$(this).removeClass("active");
    	$(this).css("color","#3fc6ff");
    	$(".zhuangtai").css("padding-bottom","0");
    	$(".moreNR").css("display","block");
    });*/
    var status=1;
    $(".more").click(function()
{
  switch (status){
    case 1:
      $(this).children("span").addClass("upImg").removeClass('downImg');
    	$(this).removeClass("active");
    	$(this).css("color","#3fc6ff");
    	$(".zhuangtai").css("padding-bottom","0");
    	$(".moreNR").css("display","block");
      status=0;
      break;
    case 0:
        $(this).children("span").addClass("downImg").removeClass('upImg');
    	$(this).removeClass("active");
    	$(this).css("color","#929292");
    	$(".zhuangtai").css("padding-bottom","10px");
    	$(".moreNR").css("display","none");
      status=1;
      break;
  }
})
    
    
    
    $(".help").hover(function(){
    	$(".helpDiv").css("display","block");
    },function(){
    	$(".helpDiv").css("display","none");
    }
    )
    $(".hide").click(function(){
    	$(".hided").css("display","none");
    })
    $(".active").click(function(){
    	$(".hided").css("display","block");
    })
    
})
