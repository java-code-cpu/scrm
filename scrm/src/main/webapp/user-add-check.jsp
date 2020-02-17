<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户新增</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>开始使用layui</title>
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
				<input name="stuName" required lay-verify="required" placeholder="请输入学生名称" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">学生年龄</label>
			<div class="layui-input-block">
				<!-- lay-verify="required" 的校验   -->
				<input name="stuAge" required lay-verify="required" placeholder="请输入学生年龄" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">学生生日</label>
			<div class="layui-input-inline">
				<!-- lay-verify="required" 的校验   -->
				<input name="stuBirthday" id="stuBirthday"  lay-verify="required" class="layui-input">
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
		
		
		//绑定提交按钮
		form.on('submit(formDemo)',function(data){
			console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
			  console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
			  console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
			//form.val('filter', object); 表单赋值，取值
			//var data = form.val('form_user');
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
			//将按钮自带的提交动作屏蔽掉
			return false;
		});
	});
</script>
</html>