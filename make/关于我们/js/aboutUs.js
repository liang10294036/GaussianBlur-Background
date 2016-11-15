/**
 * Created by company on 2016/11/3.
 */
$(function(){
    $('.isShow').click(function(){
        var _this = $(this);
        if(_this.children().hasClass('goUp')){
            _this.children().removeClass('goUp');
        }else{
            _this.children().addClass('goUp');
        }
        $('.botAousCon').toggle(200);
    })

    $('.leftFirst li a').click(function(){
        $(this).addClass('current').parent('li').siblings().children('a').removeClass('current');
    })
})
