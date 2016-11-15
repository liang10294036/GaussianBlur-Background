/**
 * Created by company on 2016/10/19.
 */

$(function(){
    $('.leninforBottom1 ul').each(function(i,v){
        $(v).find('li i').on('click',function(){
            $(this).removeClass('select').parent('li').siblings().children('i').addClass('select');
            $(this).removeClass('select').parents('ul').siblings().children('li').children('i').addClass('select');
        })
    })
})
