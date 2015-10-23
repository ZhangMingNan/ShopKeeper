var  updateFlag = 'add';
$(function(){
	
	$("#cancelBut").click(function(){
		//关闭窗口。
		$('#addOrganizationDialog').dialog('close');
	});
				$("#okBut").click(function(){
					if(updateFlag == 'add'){
										//前台保存
										var node = $('#organizationListTree').treegrid('getSelected');
										$('#organizationListTree').treegrid('append',{
												parent:node.id ,
												data:[{
														name:$('#addOrganizationform').find('input[name=name]').val(),
												}]
										});
										//2 后台保存 
										$.ajax({
											type:'post',
											url:'organization/appendChild',
											cache:false , 
											dataType:'json',
											data:{
														parentId:node.id ,
														name:$('#addOrganizationform').find('input[name=name]').val(),
											} ,
											success:function(data){
													//刷新节点 : 刷新当前选中节点
													$('#organizationListTree').treegrid('reload',node.id);
													$.messager.show({
														title:'提示信息' , 
														msg:data.message
													});
											}
										});
										//关闭窗口。
										$('#addOrganizationDialog').dialog('close');
							} else{			
								//获取当前选中的节点。
								var node = $('#organizationListTree').treegrid('getSelected');
								var parent =	$('#organizationListTree').treegrid('getParent' , node.id);
										$.ajax({
											type:'post',
											url:'organization/'+node.id+'/update',
											cache:false , 
											dataType:'json',
											data:{
													id:$('#addOrganizationform').find('input[name=id]').val() ,
											        parentId:parent.id,
													name:$('#addOrganizationform').find('input[name=name]').val()
											} ,
											success:function(r){
												//刷新节点  :如果当前选中的节点是叶子节点的话,刷新该节点的父亲 ,如果不是叶子节点,刷新当前选中节点即可.
												$('#organizationListTree').treegrid('reload' , parent.id);
												$.messager.show({
													title:'提示信息',
													msg:'操作成功!'
												});
											}
										});
										//3关闭窗口
										$('#addOrganizationDialog').dialog('close');
							}
					});

					$('#organizationListTree').treegrid({
							nowrap: false,
							rownumbers: true,
							collapsible:true,
							url:'organization/getData',			
							idField:'id',				//数据表格要有主键	
							treeField:'name',			//treegrid 树形结构主键 text
							fitColumns:true ,
							columns:[[
								{field:'name',title:'名称',width:200} 
							]],
							onContextMenu: function(e,row){
								e.preventDefault();					//屏蔽浏览器的菜单
								$(this).treegrid('unselectAll');	//清除所有选中项
								$(this).treegrid('select', row.id);	//选中状态 
								$('#organizationm').menu('show', {
									left: e.pageX,
									top: e.pageY
								});
							}
					});
		});

function appendOrganization(){
						updateFlag='add';
						//1清空表单数据
						$('#addOrganizationform').form('clear');
						//2打开窗口
						$('#addOrganizationDialog').dialog('open');
};

function editorOrganization(){
						updateFlag='edit';
						//1清空表单数据
						$('#addOrganizationform').form('clear');
						//2填充表单回显数据
						var  node  = $('#organizationListTree').treegrid('getSelected');
						$('#addOrganizationform').form('load',{
								id:node.id ,
								name:node.name
						});
						//3打开窗口
						$('#addResourceDialog').dialog('open');
}

function removeOrganization(){
						$.messager.confirm("提示信息","确认删除?",function(r){
								if(r){
									// 1前台删除
									var  node  = $('#organizationListTree').treegrid('getSelected');
									$('#organizationListTree').treegrid('remove',node.id);
									// 2后台删除 
									$.post('organization/'+node.id+'/delete', function(result){
												$('#organizationListTree').treegrid('unselectAll');
												//刷新节点 : 刷新当前选中节点
												$('#organizationListTree').treegrid('reload',node.id);
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