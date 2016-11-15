//   活动操作

  //是否或将操作
  $(function(){

	  //$('#search').submit(option);
	  $('#search').click(function(){
		  var s= $("#phoneNum").val();
		  var b=$("#passWord").val();
		  $.ajax({
			  type:'POST',
			  contentType: "application/x-www-form-urlencoded",
			  url:'http://211.103.153.135:8081/hzcfmobile/midautumnActivity/getAwardInfo',
			  async: false,
			  dataType:"json",
			  data:{
				  mobile:s,
				  password:b
			  },
			  success: function(data){
				  console.log(data);
				  if(data.retCode == '1'){
					  console.log('恭喜您中奖！');
					  if(data.awardInfo.rewardLevel == 0){
						  //console.log('参与没中奖');

						  window.location.href = "./js/noWinning.html"
					  }else if(data.awardInfo.rewardLevel == '1' ){
						  $('#winner').innerText('恭喜您，获得一等奖[Apple iPhone 7]');
						  window.location.href = "./js/noAddress.html?mobile="+s
					  }else if(data.awardInfo.rewardLevel == 2){
						  window.location.href = "./js/noAddress.html?mobile="+s;
						  $('#winner').innerText('恭喜您，获得二等奖[Apple iPhone 7]');
					  }else if(data.awardInfo.rewardLevel == 3){
						  window.location.href = "./js/noAddress.html?mobile="+s;
						  $('#winner').innerText('恭喜您，获得三等奖[Apple iPhone 7]');
					  }else if(data.awardInfo.rewardLevel == 4){
						  window.location.href = "./js/noAddress.html?mobile="+s;
						  $('#winner').innerText('恭喜您，获得四等奖[Apple iPhone 7]');
					  }else if(data.awardInfo.rewardLevel == 5){
						  window.location.href = "./js/noAddress.html?mobile="+s;
						  $('#winner').innerText('恭喜您，获得五等奖[Apple iPhone 7]');
					  }else if(data.awardInfo.rewardLevel == 6){
						  window.location.href = "./js/winResults.html";
					  }
				  }else if(data.retCode == '0'){
					  console.log('您没有参加此次活动！');
					  window.location.href = "./js/noAttend.html"
				  }else if(data.retCode == "-1"){
					  $('#warmErr1').html('手机号未注册！').addClass('showErrimg');
				  }else if(data.retCode == "-2"){
					  $('#warmErr2').html('手机号和密码不匹配！').addClass('showErrimg');
				  }
			  },
			  error: function(){
				  alert("抱歉，系统错误！");
			  }  //请求失败

		  })
	  })

	  //收货地址
	  $('#submitAddress').click(function(){
		  $.ajax({
			  type: "POST",
			  url: "http://211.103.153.135:8081/hzcfmobile/midautumnActivity/updateAddress",
			  data: {deliveryName:$('#name'),detailedAddress:$('#address'),deliveryMobile:$('#telphonenumber')},
			  async: false,
			  dataType:"json",
			  success: function(data){
				  console.log(data);
				  console.log("请求成功了");
			  }
		  })
	  })
  })


