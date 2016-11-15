/**
 * Created by company on 2016/9/22.
 */
    //ͷ导航栏
$(function(){
    $(".right > ul > li a.bot-line").click(function(){
        $(this).addClass("active");
        $(this).parent("li").siblings().children("a").removeClass("active")
    })
    $(".right > ul > li").mouseenter("on",function(){
        $(this).children("ul").stop().slideDown();
        $(this).find('.triangle_border_up').show();
        $(this).addClass("blueFont");
        $(this).children("a").addClass("showColor");
    })
    $(".right > ul > li").mouseleave(function () {
        $(this).removeClass("blueFont");
        $(this).children("a").removeClass("showColor");
        $(this).children("ul").stop().hide();
        $(this).find('.triangle_border_up').hide();
    });
    $(".right > ul > li > ul > li.firstList").hover(function(){
        $(this).parent().siblings('.triangle_border_up').find('span').css({"border-color":"transparent transparent #f4f4f4"});
    },function(){
        $(this).parent().siblings('.triangle_border_up').find('span').css({"border-color":"transparent transparent #ffffff"});
    })
})

//placehoder 兼容
$(function(){
    if(!placeholderSupport()){   // 判断浏览器是否支持 placeholder
        $('[placeholder]').focus(function() {
            var input = $(this);
            if (input.val() == input.attr('placeholder')) {
                input.val('');
                input.removeClass('placeholder');
            }
        }).blur(function() {
            var input = $(this);
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.addClass('placeholder');
                input.val(input.attr('placeholder'));
            }
        }).blur();
    };
})
function placeholderSupport() {
    return 'placeholder' in document.createElement('input');
}