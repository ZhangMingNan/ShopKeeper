function openSaveTypeDialog(){
	
	$('#fm').form('clear');
	$('#saveTypeDg').dialog('open');	
}

$(function(){
	$('#saveTypeDg').dialog({
		title: '新增款式',
		width:400,
		height:140,
		closed: true,
		cache:  false,
		modal: true,
		buttons: [{  
			text: '确定保存',  
			iconCls: 'icon-save',  
			handler: function () {
				$('#fm').form('submit',{
					url: "productType/save",
					onSubmit: function(){
						return $(this).form('validate');
					},
					success: function(result){
						$('#saveTypeDg').dialog('close');  
						$('#productTypeDg').datagrid('reload'); 
					}
				});

			}  	
		}, {  
			text: '取消',  
			handler: function () {    
				$('#saveTypeDg').dialog('close');  
			}  
		}] 
	});

	$('#productTypeDg').datagrid({
		url:'productType/allType',
		fitColumns:"true",
		singleSelect:"true",
		pageSize:20,
		idField:'id',
		pagination:true,
		rownumbers:true,
		striped:true,
		columns:[[
		          {field:'type',title:'款式',width:'100px'}
		          ]]
	});

	var pager = $('#productTypeDg').datagrid().datagrid('getPager');   
	pager.pagination({
		buttons:[{
			iconCls:'icon-add',
			text:'新增款式',
			handler:function(d){
				openSaveTypeDialog();
			}
		}]
	}); 
})