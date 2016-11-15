/**
 * Created by company on 2016/11/10.
 */
$(function(){
    $.ajax({
        type:'POST',
        contentType: "application/x-www-form-urlencoded",
        url:'http://www.hzdjr.com/activity/Double11Activity/getInvestAmt/aa.js',
        async: false,
        dataType:"json",
        success: function(data){
            $('#turnover').html(data);
        }
    })
})