$(function($){
	$('#adduser').on('click',function(e){
        var name = $('#nameInput').val();
        var sex = $("input[name='optionsRadios']:checked").val();
        var age = $('#ageInput').val();
        var tel = $('#telInput').val();
        var address = $('#addressInput').val();

        $.ajax({
			url:'http://127.0.0.1:8080/ssm/adduser',
			type:'post',
			dataType:'jsonp',
			jsonpCallback:'callback',
			timeout:10000,
			cache:false,
			data:{
				name : name,
                sex : sex,
                age : age,
                telephone : tel,
                address : address
			},
			error : function() {
				alert("添加失败...");
				return false;
			},
			success : function (res) {
				alert("添加成功...");
				window.location.href = "frame.html";
			}
		});
    });
    $('#cancle').on('click',function(e){
        window.location.href = "frame.html";
    });
});