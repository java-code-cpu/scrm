<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 引入自定义的权限管理标签 -->
<%@ taglib prefix="auth" uri="/auth-tags"%>
<div class="layui-card-header">系统设置</div>
<form class="layui-form" action="">
	<input type="hidden" id="rowId" name="rowId" value="${rowId}">
	<div class="layui-form-item">
		<label class="layui-form-label">公司名称</label>
		<div class="layui-input-inline">
			<input type="text" name="config1" placeholder="請輸入。。。"
				autocomplete="off" class="layui-input" value="${config}">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">公海天数</label>
		<div class="layui-input-inline">
			<input type="text" name="highSeas" placeholder="請輸入。。。"
				autocomplete="off" class="layui-input" value="${highSeas}">
		</div>
		<div class="layui-form-mid layui-word-aux">最后的跟单超过这个天数后，客户转成公海状态，进入客户池。</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">跟单提醒</label>
		<div class="layui-input-inline">
			<input type="text" name="DocReminder" placeholder="請輸入。。。"
				autocomplete="off" class="layui-input" value="${docReminder}">
		</div>
		<div class="layui-form-mid layui-word-aux">距离最后一次跟单后，到达这个天数进行提醒</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="but_resource_submit">提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
<!-- 引入自定义的JS脚本 -->
<script type="text/javascript" src="assert/pages/js/sys/system.js"></script>
