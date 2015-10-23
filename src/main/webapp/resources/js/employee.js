$(function(){
	$('#saveEmployeeDg').dialog({
		title: '新增员工',
		width:400,
		height:180,
		closed: true,
		cache:  false,
		modal: true,
		buttons: [{  
			text: '确定保存',  
			iconCls: 'icon-save',  
			handler: function () {
				$('#fm').form('submit',{
					url: "employee/save",
					onSubmit: function(){
						return $(this).form('validate');
					},
					success: function(result){
						$('#saveEmployeeDg').dialog('close');  
						$('#employeeDg').datagrid('reload'); 
					}
				});
			}  	
		}, {  
			text: '取消',  
			handler: function () {    
				$('#saveEmployeeDg').dialog('close');  
			}  
		}] 
	});

	
	
	
	$('#employeeDg').datagrid({
		toolbar: [{
			iconCls: 'icon-edit',
			handler: function(){		
				$('#saveEmployeeDg').dialog('open');
			},
			text:'新增'
		},'-',{
			iconCls: 'icon-help',
			handler: function(){
					var row = $('#employeeDg').datagrid('getSelected');
					if(row){
						$.messager.confirm("提示信息","确认删除?",function(r){
							if(r){
								// 2后台删除 
								$.post('employee/delete' , {empId:row.id},function(result){
									$.messager.alert('提示信息','员工删除成功!');
									$('#employeeDg').datagrid('reload');
								});
							} else {
								return ;
							}
						});
					}else{
						$.messager.confirm("提示","请至少选中一行记录！",function(r){});
					}
				
		},
			text:'删除'
		}],
		url:'employee/getData',
		fitColumns:"true",
		singleSelect:"true",
		striped:true,
		rownumbers:true,
		columns:[[
		          {field:'name',title:'姓名',width:100},
		          {field:'phone',title:'电话',width:100}
		          ]]
	});
});
