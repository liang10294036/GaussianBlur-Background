$(function(){
    $(".leixing li").click(function() {
        $(this).addClass("active").siblings().removeClass("active");
        $(this).children("a").addClass("current").parent().siblings().children().removeClass("current");

        var index = $(this).index();
        console.log(index)
        $(".tab")
            .eq(index)
            .addClass("block")
            .siblings(".tab")
            .removeClass("block");
    });
})
