<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>会员管理</title>
    <%-- 浏览器中的图标 --%>
    <link rel="Shortcut Icon" href="../images/xy.ico">
    <%-- 收藏夹中的图标 --%>
    <link rel="Bookmark" href="../images/xy.ico"/>
    <%-- 核心样式文件 --%>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../easyui/demo/demo.css">
    <%-- 核心脚本文件 --%>
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <%-- 国际化脚本文件 --%>
    <script type="text/javascript" src="../easyui/locale/easyui-lang-zh_CN.js"></script>
    <%-- 自定义样式 --%>
    <style type="text/css">
      a{color:blue;text-decoration:none}
      a:vlink{color:blue}
      a:hover{color:red}
    </style>
</head>
<body>
  <div class="easyui-layout" data-options="fit:true">
    <!-- 顶部logo -->
    <div data-options="region:'north'" style="height:60px;">
      <div style="font-size:12px;font-fimaly:微软雅黑;background-image:url(../images/logo1.jpg);background-repeat:no-repeat;background-color:#eee;color:#636363;padding-right:20px;padding-top:40px;height:18px;text-align:right">
  		 <label>欢迎您：享悦[管理员]</label>&nbsp;&nbsp;
  		 <a href="../login.html">退出登录</a>
	  </div>
    </div>
    <!-- 底部信息 -->
    <div data-options="region:'south'" style="height:30px;line-height:30px">
      <div align="center" style="background:#eee;font-size:12px">
  		Copyright &copy; 2000-2010 享悦 版权所有
	  </div>
    </div>
    <!-- 左侧导航 -->
    <div data-options="region:'west'" style="width:200px;">
      <% String panelIndex=request.getParameter("panelIndex");%>
<div class="easyui-accordion" style="width:100%;">
  <div title="用户管理" <%if("0".equals(panelIndex)){%> data-options="selected:true"<%}%>>
    <a id="user" href="user.jsp?panelIndex=0" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" style="width:100%;text-align:left;">用户管理</a>
  </div>
  <div title="分享管理" <%if("1".equals(panelIndex)){%> data-options="selected:true"<%}%>>
    <a id="talk" href="talk.jsp?panelIndex=1" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" style="width:100%;text-align:left;">分享管理</a>
  </div>
  <div title="学习管理" <%if("2".equals(panelIndex)){%> data-options="selected:true"<%}%>>
    <a id="study" href="study.jsp?panelIndex=2" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" style="width:100%;text-align:left;">学习管理</a>
  </div>
  <div title="图片管理" <%if("3".equals(panelIndex)){%> data-options="selected:true"<%}%>>
    <a id="view" href="view.jsp?panelIndex=3" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" style="width:100%;text-align:left;">图片管理</a>
  </div>
  <div title="评论管理" <%if("4".equals(panelIndex)){%> data-options="selected:true"<%}%>>
    <a id="reply" href="replytalk.jsp?panelIndex=4" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" style="width:100%;text-align:left;">评论分享管理</a>
    <a id="reply" href="replystudy.jsp?panelIndex=4" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" style="width:100%;text-align:left;">评论学习管理</a>
  </div>
</div>
    </div>
    <!-- 主体部分 -->
    <div data-options="region:'center'">
      <!-- 用户数据表格 -->
<table id="dg_user" class="easyui-datagrid" style="width:100%;height:100%" data-options="toolbar:'#tb',singleSelect:true,fit:true,pagination:true,pageSize:20,pageList:[10,20,50],loadMsg:'数据正在努力加载，请稍后...'">
  <thead>
    <tr>
      <th data-options="field:'selector',checkbox:true"></th>
      <th data-options="field:'use_id',width:40,align:'center'">编号</th>
      <th data-options="field:'account',width:100,align:'center'">用户名</th>
      <th data-options="field:'passWord',width:120,align:'center'">密码</th>
      <th data-options="field:'avaPath',width:100,align:'center'">头像地址</th>
      <th data-options="field:'nickName',width:100,align:'center'">昵称</th>
      <th data-options="field:'birth',width:100,align:'center'">生日</th>
      <th data-options="field:'sex',width:100,align:'center'">性别</th>
      <th data-options="field:'school',width:100,align:'center'">学校</th>
      <th data-options="field:'academy',width:120,align:'center'">学院</th>
      <th data-options="field:'major',width:120,align:'center'">专业</th>
      <th data-options="field:'entryYear',width:120,align:'center'">入学时间</th>
      <th data-options="field:'perSign',width:240,align:'center'">个人签名</th>
      <th data-options="field:'hobby',width:240,align:'center'">兴趣爱好</th>
      <th data-options="field:'homeTown',width:240,align:'center'">家乡</th>
    </tr>
  </thead>
</table>
 
     <!-- 工具栏 -->
<div id="tb">
  <input id="txt_UserName" class="easyui-textbox" data-options="prompt:'请输入姓名',iconCls:'icon-man'"/>
  <a id="btn_Query" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
  <a id="btn_Add" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
  <a id="btn_Edit" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
  <a id="btn_Del" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">删除</a>
</div>
      <!-- 添加|修改对话框 -->
<div id="dlg_user" class="easyui-dialog" data-options="title:'会员信息维护',buttons:'#bt_dlg_user',closed:true" style="width:270px;height:350px;padding:10px"  >
  <form id="fm_user" method="post">
    <input name="use_id" type="hidden">
    <div>
      <label>用户名：</label>
      <input name="account" class="easyui-textbox" style="width:180px">
    </div><br/>
    <div>
      <label>密码：</label>
      <input name="passWord" class="easyui-textbox" style="width:180px">
    </div><br/>
    <div>
      <label>头像地址：</label>
      <input name="avaPath" class="easyui-textbox" style="width:180px">
    </div><br/>
    <div>
      <label>昵称：</label>
      <input name="nickName" class="easyui-textbox" style="width:180px">
      </div>
    <div>
      <label>生日：</label>
      <input name="birth" class="easyui-textbox" style="width:180px">
    </div><br/>
    <div>
      <label>性别：</label>
      <input name="sex" class="easyui-textbox" style="width:180px">
    </div><br/>
    <div>
      <label>学校：</label>
      <input name="school" class="easyui-textbox" style="width:180px">
    </div><br/>
    <div>
      <label>学院：</label>
      <input name="academy" class="easyui-textbox" style="width:180px">
    </div><br/>
    <div>
      <label>专业：</label>
      <input name="major" class="easyui-textbox" style="width:180px">
    </div><br/>
    <div>
      <label>入学时间：</label>
      <input name="entryYear" class="easyui-textbox" style="width:180px">
    </div><br/>
    <div>
      <label>个人签名：</label>
      <input name="perSign" class="easyui-textbox" style="width:180px">
    </div><br/>
    <div>
      <label>兴趣爱好：</label>
      <input name="hobby" class="easyui-textbox" style="width:180px">
    </div><br/>
    <div>
      <label>家乡：</label>
      <input name="homeTown" class="easyui-textbox" style="width:180px">
    </div><br/>
  </form>
</div>
<!-- 对话框按钮 -->
<div id="bt_dlg_user">
  <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveRecord()">保存</a>
  <a class="easyui-linkbutton" data-options="iconCls:'icon-no'" onclick="javascript:$('#dlg_user').dialog('close')">退出</a>
</div>
    </div>
  </div>
  <%-- 自定义脚本 --%>
  <script type="text/javascript" src="js/user_option.js"></script>
</body>
</html>
