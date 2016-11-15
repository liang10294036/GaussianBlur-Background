/**
 * Created by company on 2016/9/22.
 */
function AutoScroll(obj){
    $(obj).find("div:first").animate({
        marginTop:"-40px"
    },500,function(){
        $(this).css({marginTop:"0px"}).find("a:first").appendTo(this);
    });
}
$(document).ready(function(){
    setInterval('AutoScroll("#scrollDiv")',3000)
});

var oHzwdFlashId=document.getElementById("hzwdFlashId");
var oHzwdFlashIdIco=document.getElementById("hzwdFlashIdIco");
var aHzwdFlashIdImgW=2560;
var ww=window.innerWidth|| document.documentElement.clientWidth|| document.body.clientWidth;
var aHzwdFlashIdLiL=parseInt((-(aHzwdFlashIdImgW-ww)/2));
var zIndexs=0;
var timer=null;

function getStyle(obj,attr){
    if(obj.currentStyle){
        return obj.currentStyle[attr];
    }
    else{
        return getComputedStyle(obj,false)[attr];
    }
}
function startMove(obj, json, fn)
{
    clearInterval(obj.timer);
    obj.timer=setInterval(function (){
        var bStop=true;
        for(var attr in json)
        {

            var iCur=0;

            if(attr=='opacity')
            {
                iCur=parseInt(parseFloat(getStyle(obj, attr))*100);
            }
            else
            {
                iCur=parseInt(getStyle(obj, attr));
            }


            var iSpeed=(json[attr]-iCur)/8;
            iSpeed=iSpeed>0?Math.ceil(iSpeed):Math.floor(iSpeed);

            if(iCur!=json[attr])
            {
                bStop=false;
            }

            if(attr=='opacity')
            {
                obj.style.filter='alpha(opacity:'+(iCur+iSpeed)+')';
                obj.stysle.opacity=(iCur+iSpeed)/100;
            }
            else
            {
                obj.style[attr]=iCur+iSpeed+'px';
            }
        }

        if(bStop)
        {
            clearInterval(obj.timer);

            if(fn)
            {
                fn();
            }
        }
    }, 30)
}



