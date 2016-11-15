/**
 * Created by company on 2016/9/21.
 */
/**
 * Created by company on 2016/9/18.
 */

$(function(){
    //切换登录
    $(function () {
        $(".box-title .log").click(function () {
            $(this).removeClass("hzRegistered").siblings(".reg").addClass("hzRegistered");
            $("#my-logIn").show().siblings("#my-counselor").hide();
        })
        $(".box-title .reg").click(function () {
            $(this).removeClass("hzRegistered").siblings(".log").addClass("hzRegistered");
            $("#my-counselor").show().siblings("#my-logIn").hide();
        })
    })

    $(function(){
        $(".box-title .log").click(function(){
            $(this).removeClass("hzRegistered").siblings(".reg").addClass("hzRegistered").css("border-radius","0px 5px 0px 0px","-webkit-border-radius","0px 5px 0px 0px","-oz--border-radius","0px 5px 0px 0px");
            $("#my-counselor").show().siblings("#my-register").hide();

        })
        $(".box-title .reg").click(function(){
            $(this).removeClass("hzRegistered").siblings(".log").addClass("hzRegistered").css("border-radius","5px 0px 0px 0px","-webkit-border-radius","5px 0px 0px 0px","5px 0px 0px 0px","5px 0px 0px 0px");
            $("#my-register").show().siblings("#my-counselor").hide();
        })
    })
    //返回顶部
    $(function () {
        $(window).scroll(function(){
            if ($(window).scrollTop()>100){
                $("#back-to-top").fadeIn(500);
            }
            else
            {
                $("#back-to-top").fadeOut(500);
            }
        });

        $("#go-top").click(function(){
            $('body,html').animate({scrollTop:0},500);
            return false;
        });
//  轮播图
        $(function() {
            $(".flexslider").flexslider();
        });
    });
});

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
            $(".referrer").slideDown(100);
            $(".logIn-btn").css("margin-top","58px")

        }else{
            $(".referrer").slideUp(100);
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
