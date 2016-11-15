/**
 * Created by company on 2016/9/28.
 */
//关闭banner图片
$(function(){
    $(".lendClose").click(function(){
        $(this).hide(200);
        $(".lendBanner").hide(200);
        $(".marTop20").css("margin-top","0px");
    })
})

//tab栏切换
$(".tab-item").click(function() {
    $(this).addClass("active").siblings().removeClass("active");
    var index = $(this).index();
    $(".del_content > ul > li")
        .eq(index)
        .addClass("selected")
        .siblings("li")
        .removeClass("selected");
});

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

//普享标列表切换
$(function(){
    $('.uls').find(' .current li').each(function(i,v){
        $(v).on('click',function(){
            $(this).children('a').addClass('active');
            $(this).siblings().children('a').removeClass('active');
        })
    })
})

//查看详情信息轮播
$(function(){
    var ban = $('.contentImg');
    ban.each(function(i,v){
        var left = $(v).children('.left'),
            right = $(v).children('.right'),
            card = $(v).children('.card'),
            ul = card.children('.flashImg'),
            ulevery = ul.children('li').outerWidth(true),
            cardNum = card.width() / ulevery,
            flag = cardNum * ulevery,
            num = 0,
            allWidth = ulevery * ul.children('li').length;
        var cardWidth = card.width();
        ul.css('width',allWidth);
        if((cardWidth-allWidth) < 0){
            $('.contentImg .arrow').show();
        }else{
            $('.contentImg .arrow').hide();
        }
        right.on('click',function(){
            num -= ulevery;
            if(num >= -(allWidth - flag)){
                ul.stop().animate({'left':num});
            }else{
                num = -(allWidth - flag)
            }
        })

        left.on('click',function(){
            num += ulevery;
            if(num <= 0){
                ul.stop().animate({'left':num});
            }else{
                num = 0;
            }
        })

    })
})

//查看
$(function(){
    $('.reveInfor').find('a').each(function(i,v){
        $(v).on('click',function(){
            $('.inforMask,.inforWindow').css('display','block');
        })
    })
    $('.inforClose a').on('click',function(){
        $('.inforMask,.inforWindow').css('display','none');
    })

    //提示信息
    $('.warmPro').mouseover(function(){
        $('.warmPuxing').css('display','block');
    })
    $('.warmPro').mouseout(function(){
        $('.warmPuxing').css('display','none');
    })
})

//立即出借弹窗
$(function(){
    $('.lendNow').click(function(){
        $('.hzlendInfor,.inforMask').css('display','block');
    })
    $('.isSure .cancle').click(function(){
        $('.hzlendInfor,.inforMask').css('display','none');
    })
})

//出借计划-详情页-出借明细-详情
$(function(){
    $('.lendInfor table tr td.corSpecial').each(function(i,v){
        $(v).on('click',function(){
            $('.inforMask,.hzlendDetail').css('display','block');
        })
    })
    $('.setailClose').click(function(){
        $('.inforMask,.hzlendDetail').css('display','none');
    })
})
