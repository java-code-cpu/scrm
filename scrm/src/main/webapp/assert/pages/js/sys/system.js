layui.use([ 'form' ], function() {
	var $ = layui.$;
	var form = layui.form;
	var layer = layui.layer;
	form.on('submit(but_resource_submit)', function(data) {
		$.ajax({
			type : 'put',
			url : 'system',
			data : $(data.form).serialize(),
			success : function(result) {
				if (result) {
					layer.msg("设置成功");
				}
			}
		});
		return false;
	});
});