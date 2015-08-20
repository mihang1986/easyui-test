<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Insert title here</title>
		<link  type="text/css" rel="stylesheet" href="${basePath}component/easyui/themes/default/easyui.css"/>
		<script type="text/javascript" src="${basePath}component/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath}component/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${basePath}component/util/util.js"></script>
	</head>
	<body>
		<div class="easyui-panel" fit="true" border="false">
			<div class="easyui-layout" fit="true">   
			    <div region="north" height="150" border="false" noheader="true"></div>   
			    <div region="center" border="false" noheader="true">
			    	<table class="easyui-datagrid" url="user/all" method="get" fitColumns="true" singleSelect="true" striped="true" fit="true" pagination="true">   
					    <thead>
					        <tr>
					        	<th field="" checkbox="true"></th>   
					            <th field="id" width="100">编号</th>   
					            <th field="name" width="100">名称</th>   
					            <th field="dob" width="100" align="right" formatter="">生日</th>   
					        </tr>
					    </thead>   
					</table>
			    </div>   
			</div>
		</div>
	</body>
</html>