<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户新增</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- 引入layui的样式表 -->
<link rel="stylesheet" href="assert/layui/css/layui.css">
</head>
<body>
	<!-- lay-filter="form_user"  可以理解成 id="form_user" -->
	<form class="layui-form" lay-filter="form_user">
		<div class="layui-form-item">
			<label class="layui-form-label">学生名称</label>
			<div class="layui-input-block">
				<!-- lay-verify="required" 的校验   -->
				<input name="stuName" required lay-verify="required|checkstuname" placeholder="请输入学生名称" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">学生年龄</label>
			<div class="layui-input-block">
				<!-- lay-verify="required" 的校验   -->
				<input name="stuAge" required lay-verify="required|number" placeholder="请输入学生年龄" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">学生生日</label>
			<div class="layui-input-block">
				<!-- lay-verify="required" 的校验   -->
				<input name="stuBirthday" id="stuBirthday"  lay-verify="required" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">学生性别</label>
			<div class="layui-input-block">
				<input type="radio" name="stuSex" value="1" title="男"> 
				<input type="radio" name="stuSex" value="0" title="女" checked>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">学生爱好</label>
			<div class="layui-input-block">
				<input type="checkbox" name="stuLikes" value="写作" title="写作"> 
				<input type="checkbox" name="stuLikes" value="阅读" title="阅读" checked> 
				<input type="checkbox" name="stuLikes" value="发呆" title="发呆">
			</div>
		</div> 
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</body>
<!-- 
	按模块开发
	layui.all.js 全部加载模块
	layui.js 按需加载模块
 -->
<script type="text/javascript" src="assert/layui/layui.js"></script>
<!-- 书写自己的脚本 -->
<script type="text/javascript">
	//layui.use 当页面渲染成功后，加载配置的模块
	layui.use([ 'layer','form','laydate' ], function() {
		//通过一个变量将加载的模块取出
		var layer = layui.layer;
		var form = layui.form;
		var laydate = layui.laydate;
		//layui内置jquey
		var $ = layui.$;
		//layer.msg('hello world!!');
		//渲染日历
		laydate.render({
			elem:'#stuBirthday'//指定元素
		});
		//表单的自定义校验
		form.verify({
			checkstuname:function(value, item){ //value：表单的值、item：表单的DOM对象
				//console.log(value);
				var msg ;
				$.ajax({
					type:'get',
					async:false,//为了让layui可以做唯一性的校验，需要将ajax的异步提交关闭。
					url:'student/checkname',
					data:{"stuName":value},
					success:function(result){
						//判定此名称不可以使用，已经有人用了
						if(result==0){
							//不要在ajax的success中return
							//return '此名称以有人使用';
							msg ='此名称以有人使用';
						}
					}
				});
				//在ajax的外面return 此次检测的结果。
				//如果return的数据有值的话，则 layui就认为是校验不通过。
				//如果return的结果是个空，则layui认为校验通过。
				return msg;
			}
		});
		
		//绑定提交按钮
		form.on('submit(formDemo)',function(data){
			console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
			console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
			console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
			//form.val('filter', object); 表单赋值，取值
			//var data = form.val('form_user');
			//通过使用 jQuery的表单序列化
			var fromData =  $(data.form).serialize();
			console.log(fromData);
			$.ajax({
				type:'post',
				url:'student',
				data:fromData,
				success:function(result){
					if(result){
						layer.msg("提交成功了！");
					}
				}
			});
			//layer.msg('点击提交了');
			//尝试使用ajax的方式提交到后台
			//$.ajax();
			//将按钮自带的提交动作屏蔽掉
			return false;
		});
	});
</script>
</html>