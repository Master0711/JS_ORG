$(function($){
	$('#btnRtSubmit').on('click',function(e){
		var userName = $('#username').val();
		var passwd = $('#password').val();

		$.ajax({
			url:'http://127.0.0.1:8080/ssm/login',
			type:'post',
			dataType:'jsonp',
			jsonpCallback:'callback',
			timeout:10000,
			cache:false,
			data:{
				username : userName,
				password : passwd
			},
			error : function() {
				alert("登录连接超时...");
				return false;
			},
			success : function (res) {
				if (res.loginmsg == "loginsuccess") {
					window.location.href = "frame.html";
				}else if(res.loginmsg == "passworderror"){
					alert("password error!!");
				}else{
					alert("no such user!!");
				}
			}
		});
	});	
});