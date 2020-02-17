<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>layui-table</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- 引入layui的样式表 -->
<link rel="stylesheet" href="assert/layui/css/layui.css">
</head>
</head>
<body>
<button type="button" class="layui-btn" id="btn_add">新增</button>
<button type="button" class="layui-btn" id="btn_render">重新渲染</button>

<table id="demo" lay-filter="table_user"></table>

<!-- layui 定义的模板数据 -->
<script type="text/html" id="userKindTpl">
 {{# if(d.userKind ==1){ }}
    <span class="layui-badge layui-bg-orange">超级用户</span>
  {{#  } else { }}
    <span class="layui-badge layui-bg-cyan">普通用户</span>
  {{#  } }}
</script>
<!-- 修改，删除 按钮 -->
<!-- 此处注意：必须有lay-event 才能通过table.on完成事件的绑定 -->
<script type="text/html" id="userBtnTpl">
 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">修改</a>
{{# if(d.userKind ==1){ }}
	<button type="button" class="layui-btn layui-btn-xs layui-btn-disabled">删除</button>
{{# }else{  }}
	<button type="button" class="layui-btn layui-btn-xs" lay-event="delete">删除</button>
 {{#  } }}
</script>
</body>

<script type="text/javascript" src="assert/layui/layui.js"></script>
<script type="text/javascript">
	//尝试进行自定义的配置
	layui.config({
		restful:true
	});
	layui.use(['table','form'],function(){
		var $ = layui.$;
		//取得layui的table模块
		var table = layui.table;
		var form = layui.form;
		//通过render方法开始渲染 talbe的数据
		table.render({
			 elem: '#demo' //要绑定的页面元素
		    ,height: 312 // 设置高度
		    					// user/{page}/{limit}
		    ,url: 'user' //数据接口 user/find?page=1&limit=10
		    ,where:{"userCode":"lisi"}//模拟额外的参数
		    ,limit:2 //没有要显示的条数 。自定义成2.
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'rowId', title: 'ID', width:80, sort: true, fixed: 'left'}
		      ,{field: 'userName', title: '用户名称', width:180}
		      ,{field: 'userCode', title: '登录账号', width:180}
		      ,{field: 'userKind', title: '用户类型', width:180,templet:'#userKindTpl'}
		      ,{field: 'userPhone', title: '手机号码', width:180} 
		      ,{field: 'loginCount', title: '登录次数', width: 100}
		      ,{field: 'loginIp', title: '登录IP', width: 200, sort: true}
		      ,{field: '', title: '操作', width: 140,templet:'#userBtnTpl'}
		     /*  ,{field: 'score', title: '评分', width: 80, sort: true}
		      ,{field: 'classify', title: '职业', width: 80}
		      ,{field: 'wealth', title: '财富', width: 135, sort: true} */
		    ]]
		});
		//对table里面的按钮进行绑定
		////注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
		table.on('tool(table_user)',function(obj){
			 var data = obj.data; //获得当前行数据
			 var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			// var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
			 //console.log(data);
			 //console.log(layEvent);
			 //通过data将要修改的数据的主键 取出
			 var rowId = data.rowId;
			 switch (layEvent) {
			case 'edit':
				console.log('准备执行 修改');
				break;
			case 'delete':
				//让用户再进行一次确认
				if(layer.confirm('你确定要删除码？')){
					
				}
				break;
			default:
				break;
			}
		});
		//模拟 数据提交成功，重新刷新表格。
		$('#btn_render').on('click',function(){
			alert('ttt');
			//重新加载表格数据 # 'demo' <table id="demo" />
			table.reload('demo');
		});
		//绑定新增 按钮
		$('#btn_add').on('click',function(){
			//alert('tttt');
			$.ajax({
				url:'user/goadd',
				success:function(htmlData){
					console.log(htmlData);
					layer.open({
						  type: 1,
						  title:'新增用户',
						  area: '800px',//设置宽度，高度自适应
						  content: htmlData ,//这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
						  success:function(){// layer打开成功后的回调
							  //让form表单渲染一下。 form_user_add_edit = <form lay-filter="form_user_add_edit">
								form.render(null, 'form_user_add_edit');
							}	
					});
				}
			});
			/* layer.open({
			  type: 1,  // 弹出的是页面层
			  title:'新增用户',
			  content: '传入任意的文本或html' //这里content是一个普通的String
			}); */
		});
	});
</script>
</html>