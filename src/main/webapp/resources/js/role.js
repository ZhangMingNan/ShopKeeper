var  updateFlag = 'create';
$(function(){
	$("#RoleCancelBut").click(function(){
		//3关闭窗口
		$('#addRoleDg').dialog('close');
	});
	
	$("#RoleOkBut").click(function(){
		$.ajax({
			type:'post',
			url:'role/'+updateFlag,
			cache:false , 
			dataType:'json',
			data:{
			id:$('#addRoleform').find('input[name=id]').val(),
			role:$('#addRoleform').find('input[name=role]').val(),
			description:$('#addRoleform').find('input[name=description]').val(),
			resourceIdsStr:$('#resourceTree').combotree('getValues').toString()
			} ,
			success:function(r){
				$('#roleDg').datagrid('reload');
				$.messager.show({
					title:'提示信息',
					msg:'操作成功!'
				});
			}
		});
		//3关闭窗口
		$('#addRoleDg').dialog('close');
});
	
	$('#roleDg').datagrid({
	    toolbar:"#roleToolbar",
	    url:'role/getData',
	    fitColumns:"true",
	    singleSelect:"true",
		striped:true,
	    columns:[[
	        {field:'role',title:'角色名称',width:100},
	        {field:'description',title:'角色描述',width:100},
	        {field:'resourcesNames',title:'拥有的资源',width:100},
	        {field:'resourceIds',title:'',hidden:true}
	    ]]
	});
});


function editRole(){
	//2填充表单回显数据
	var row = $('#roleDg').datagrid('getSelected');
	$('#addRoleform').form('load',{
		id:row.id,
		role:row.role,
		description:row.description
	});	

	updateFlag = 'update';
	$('#resourceTree').combotree({
		url:'resource/getCombotreeData?roleId='+row.id ,
		checkbox:true,
		multiple:true,
		onlyLeafCheck:true,
		animate:true,
		valueField:"id",
		textField:"text",
		onShowPanel:function(){
				$('#resourceTree').combo('setText',row.resourcesNames);
		}
	});
$('#addRoleDg').dialog('open');
}
	
function destroyRole(){
	$.messager.confirm("提示信息","确认删除?",function(r){
		if(r){
			var row = $('#roleDg').datagrid('getSelected');
			// 2后台删除 
			$.post('role/'+row.id+'/delete' , {id:row.id},function(result){
						$('#roleDg').datagrid('reload');
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
function addRole(){
	updateFlag = 'create';
	$('#resourceTree').combotree({
		url:'resource/getCombotreeData' ,
		checkbox:true ,
		onlyLeafCheck:true,
	animate:true,
		multiple:true
});

							//1清空表单数据
						$('#addRoleform').form('clear');
						
						
						
						//2打开窗口
						$('#addRoleDg').dialog('open');
}

		