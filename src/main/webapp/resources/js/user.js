$(function(){
	$("#userCancelBut").click(function(){
		//关闭窗口
		$('#addUserDg').dialog('close');
	});
	
	$("#userOkBut").click(function(){
		$.ajax({
			type:'post',
			url:'user/create',
			cache:false , 
			dataType:'json',
			data:{
				username:$('#addUserform').find('input[name=username]').val(),
				password:$('#addUserform').find('input[name=password]').val(),
				description:$('#addUserform').find('input[name=description]').val(),
				organizationId:$('#addUserform').find('input[name=organizationId]').val(),
				roleIdsStr:$('#addUserform').find('select[name=roleIdsStr]').val().toString()
			} ,
			success:function(r){
				$('#userDg').datagrid('reload');
				$.messager.show({
					title:'提示信息',
					msg:'操作成功!'
				});
			}
		});
		//3关闭窗口
		$('#addUserDg').dialog('close');
});
	
	$('#userDg').datagrid({
	    toolbar:"#userToolbar",
	    url:'user/getData',
	    fitColumns:"true",
	    singleSelect:"true",
	    striped:true,
	    idField:'id',
	    columns:[[
	        {field:'username',title:'用户名',width:100},
	        {field:'organizationName',title:'所属组织',width:100},
	        {field:'roleList',title:'角色列表',width:100}
	    ]]
	});
});
function destroyUser(){
	$.messager.confirm("提示信息","确认删除?",function(r){
		if(r){
			var row = $('#userDg').datagrid('getSelected');
			// 2后台删除 
			$.post('user/'+row.id+'/delete' , {id:row.id},function(result){
						$('#userDg').datagrid('reload');
						$.messager.show({
							title:'提示信息',
							msg:'操作成功!'
						});
			});
		} else {
			return ;
		}
});
}
function addUser(){
	$('#orgTree').combotree({
		url:'organization/getCombotreeData' ,
		checkbox:true ,
		multiple:false,
		onlyLeafCheck:true,
		onClick:function(node){
		$("#organizationId").val(node.id);
	}
	});
	
	//1清空表单数据
	$('#addUserform').form('clear');
	//2打开窗口
	$('#addUserDg').dialog('open');
}