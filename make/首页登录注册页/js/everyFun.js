/**
 * Created by company on 2016/9/21.
 */
/**
 * Created by company on 2016/9/18.
 */

//$(function(){
    //切换登录
    //返回顶部
    //$(function () {
    //    $(window).scroll(function(){
    //        if ($(window).scrollTop()>100){
    //            $("#back-to-top").fadeIn(500);
    //        }
    //        else
    //        {
    //            $("#back-to-top").fadeOut(500);
    //        }
    //    });
    //
    //    $("#go-top").click(function(){
    //        $('body,html').animate({scrollTop:0},500);
    //        return false;
    //    });
//    });
//});

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

//点击隐藏显示推荐码
$(function(){
    $(".ponter-bot").click(function(){
        $(".logIn-btn").css("margin-top","0px")
        if($(".referrer").css("display") == "none"){
            $(".referrer").slideDown(0);
            $(".logIn-btn").css("margin-top","58px")

        }else{
            $(".referrer").slideUp(0);
            $(".logIn-btn").css("margin-top","0px")
        }
    })
})

//显示隐藏密码
$(function(){
    $(".my-password").addClass("openword-eye");
    $(".my-password").click(function(){
        var $val=$(".pwd-show").val();
        var $val2=$(".pass-none").val();
        var dp=$(".pass-none").css("display");
        if(dp=="none"){
            $(".pass-none").val($val);
            $(".pwd-show").hide();
            $(".pass-none").show();
            $(".my-password").removeClass("openword-eye").addClass("password-eye");
        }else{
            $(".pwd-show").val($val2);
            $(".pwd-show").show();
            $(".pass-none").hide();
            $(".my-password").removeClass("password-eye").addClass("openword-eye");
        }
    })
})
