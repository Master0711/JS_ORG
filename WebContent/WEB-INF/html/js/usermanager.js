// JavaScript Document
$(function(){
	var userlist;
	var $table=$("#usertab");
	//获取user列表
	$.ajax({
		url:'http://127.0.0.1:8080/ssm/showuser',
		type:'post',
		dataType:'jsonp',
		jsonpCallback:'callback',
		timeout:10000,
		cache:false,
		data:{},
		error : function() {
			alert("查询出错...");
			return false;
		},
		success : function (res) {
			userlist = res.userlist;
			console.log("111"+userlist);
		}
	});
	//jsonp无法同步，所以延时展示
	setTimeout(function () {
		console.log('out' + userlist); 
		$table.bootstrapTable({
			height:'600px',
			data:userlist,
				
			columns:[
				{
					field:'select',
					checkbox:true,
					align:'center',
					valign:'middle'
				},{
					title:'编号',
					field:'id',
					align:'center',
					valign:'middle'
				},{
					title:'用户名',
					field:'name',
					align:'center',
					valign:'middle'
				},{
					title:'性别',
					field:'sex',
					align:'center',
					valign:'middle'
				},{
					title:'年龄',
					field:'age',
					align:'center',
					valign:'middle'
				},{
					title:'电话',
					field:'telephone',
					align:'center',
					valign:'middle'
				},{
					title:'住址',
					field:'address',
					align:'center',
					valign:'middle'
				}
			]
			});
			
			//delete按钮控制
			document.getElementById("delete").setAttribute("disabled","true");
			document.getElementsByClassName("bootstrap-table")[0].onclick = function(e) {
				buttonstyle();
			}
			document.getElementsByClassName("bootstrap-table")[0].onchange = function(e) {
				buttonstyle();
			}
	}, 1000);
		function buttonstyle() {
			var count = 0;
				var array = document.getElementsByTagName('tr');
				
				for (let index = 0; index < array.length; index++) {
					if (array[index].hasAttribute('class') && array[index].getAttribute('class') == "selected") {
						count++;
					}
				}
				console.log("++++"+count);
				if (count == 0) {
					document.getElementById("delete").setAttribute("disabled","true");
				}else{
					document.getElementById("delete").removeAttribute("disabled");
				}
		}
		//跳转adduser页面
		$('#add').on('click',function(e) {
			window.location.href = "add.html";
		})
		//delete user
		arraydel = new Array();
		$('#delete').on('click',function(e) {

			var array = document.getElementsByTagName('tr');
			
			for (let index = 0; index < array.length; index++) {
				if (array[index].hasAttribute('class') && array[index].getAttribute('class') == "selected") {
					arraydel.push(array[index].getElementsByTagName('td')[0].
					getElementsByTagName('input')[0].getAttribute('value'));
				}
			}
			console.log("///"+arraydel);
			$.ajax({
				url:'http://127.0.0.1:8080/ssm/deleteuser',
				type:'post',
				dataType:'jsonp',
				jsonpCallback:'callback',
				timeout:5000,
				cache:false,
				data:{
					stringids : arraydel.join(","),
				},
				error : function() {
					alert("删除出错...");
					return false;
				},
				success : function (res) {
					alert("删除成功...");
					location.reload();
				}
			});
		});
	});