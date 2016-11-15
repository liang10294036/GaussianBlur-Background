/**
 * Created by baikaishui on 2016/6/9.
 */
define(function(require, exports, module){
    require('base');

    //回顶部
    if(hek.getById('GotoTop')){
        function goTopEx() {
            var obj=hek.getById('GotoTop');
            var goTop=null
            function getScrollTop() {
                return document.documentElement.scrollTop + document.body.scrollTop;
            }
            function setScrollTop(value) {
                if (document.documentElement.scrollTop) {
                    document.documentElement.scrollTop = value;
                } else {
                    document.body.scrollTop = value;
                }
            }
            window.onscroll = function() {
                getScrollTop() > 0 ? obj.style.display = "": obj.style.display = "none";

            }

            obj.onclick = function() {
                var goTop = setInterval(scrollMove, 1);
                function scrollMove() {
                    setScrollTop(getScrollTop() / 2);
                    if (getScrollTop() < 1) clearInterval(goTop);

                }
            }
            /*if(document.addEventListener){
                document.addEventListener('DOMMouseScroll',scrollFunc,false);
            }//W3C
            window.onmousewheel=document.onmousewheel=scrollFunc;
            function scrollFunc(){

                clearInterval(goTop);

            }*/
        }
        goTopEx()

    }
});
