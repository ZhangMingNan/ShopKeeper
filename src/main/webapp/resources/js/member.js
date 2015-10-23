
function openSaveMemberDialog(){
	$('#fm').form('clear');
	$('#saveMemberDg').dialog('open');	
}

function removeMember(){
	var row = $('#memberDg').datagrid('getSelected');
	if(row){
		$.messager.confirm("提示信息","确认删除?",function(r){
			if(r){
				$.post('delete' , {id:row.id},function(result){
					$('#memberDg').datagrid('reload');
					$.messager.show({
						title:'提示信息',
						msg:'操作成功!'
					});
				});
			} else {
				return ;
			}
		});
	}else{
		$.messager.confirm("提示","请至少选中一行记录！",function(r){});
	}
}


$(function(){
	$('#memberDg').datagrid({
		url:'getData',
		fitColumns:"true",
		singleSelect:"true",
		pageSize:20,
		idField:'id',
		pagination:true,
		rownumbers:true,
		striped:true,
		columns:[[
		          {field:'phone',title:'电话',width:'100px'},
		          {field:'score',title:'积分',width:'100px'}
		          ]]
	});
	var pager = $('#memberDg').datagrid().datagrid('getPager');   
	pager.pagination({
		buttons:[{
			iconCls:'icon-add',
			text:'新增会员',
			handler:function(d){
				openSaveMemberDialog();
			}
		},{
			iconCls:'icon-no',
			text:'删除会员',
			handler:function(d){
				removeMember();
			}
		}]
	}); 


	$('#saveMemberDg').dialog({
		title: '新增会员',
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
					url: "save",
					onSubmit: function(){
						return $(this).form('validate');
					},
					success: function(result){
						$('#saveMemberDg').dialog('close');  
						$('#memberDg').datagrid('reload'); 
					}
				});
			}  	
		}, {  
			text: '取消',  
			handler: function () {    
				$('#saveMemberDg').dialog('close');  
			}  
		}] 
	});



});

