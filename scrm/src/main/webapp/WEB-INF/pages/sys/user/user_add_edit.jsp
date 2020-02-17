<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="layui-form" lay-filter="form_add_edit">
	<!-- layui的隐藏域  -->
	<input class="layui-hide" name="rowId"/>
	<div class="layui-form-item">
		<label class="layui-form-label">用户角色</label>
		<div class="layui-input-block" lay-verify="onechecked">
			<c:if test="${!empty roleList}">
			<c:forEach items="${roleList}" var="role">
			<input type="checkbox" name="stuLikes" value="${role.roleCode}" title="${role.roleName}"> 
			</c:forEach>
			</c:if>
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">用户名称</label>
		<div class="layui-input-block">
			<!-- lay-verify 这个layui的自定义属性 ，设置表单的校验，多个校验 用 ‘|’ 隔开  -->
			<input name="userName" lay-verify="required" placeholder="请输入用户名称" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">登录账号</label>
		<div class="layui-input-block">
			<!-- lay-verify 这个layui的自定义属性 ，设置表单的校验，多个校验 用 ‘|’ 隔开  -->
			<input name="userCode" lay-verify="required|checkusercode" id="userCode" placeholder="请输入登录账号" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">登录密码</label>
		<div class="layui-input-block">
			<input type="password" name="userPass" lay-verify="required" placeholder="请输入登录密码" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="but_submit">提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
