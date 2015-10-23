var  updateFlag = 'add';
$(function(){
	

$("select[name='type']").change( function() {
  // 这里可以写些验证代码
	var value  = $(this).children('option:selected').val();
	if(value == 'button'){
		$("#urlInput").hide();
	}else{
		$("#urlInput").show();
	}
});

	$("#cancelBut").click(function(){
		//关闭窗口。
		$('#addResourceDialog').dialog('close');
	});
	
				$("#okBut").click(function(){
					if(updateFlag == 'add'){
										//前台保存
										var node = $('#resourceListTree').treegrid('getSelected');
										$('#resourceListTree').treegrid('append',{
												parent:node.id ,
												data:[{
														name:$('#addResourceform').find('input[name=name]').val(),
														type:$('#addResourceform').find('select[name=type]').val(),
														url:$('#addResourceform').find('input[name=url]').val(),
														permission:$('#addResourceform').find('input[name=permission]').val()
												}]
										});
										//2 后台保存 
										$.ajax({
											type:'post',
											url:'resource/appendChild',
											cache:false , 
											dataType:'json',
											data:{
														parentId:node.id ,
														name:$('#addResourceform').find('input[name=name]').val(),
														type:$('#addResourceform').find('select[name=type]').val(),
														url:$('#addResourceform').find('input[name=url]').val(),
														permission:$('#addResourceform').find('input[name=permission]').val()
											} ,
											success:function(data){
													//刷新节点 : 刷新当前选中节点
													$('#resourceListTree').treegrid('reload',node.id);
													$.messager.show({
														title:'提示信息' , 
														msg:data.message
													});
											}
										});
										//关闭窗口。
										$('#addResourceDialog').dialog('close');
							} else{			
								//获取当前选中的节点。
								var node = $('#resourceListTree').treegrid('getSelected');
								var parent =	$('#resourceListTree').treegrid('getParent' , node.id);
										$.ajax({
											type:'post',
											url:'resource/'+node.id+'/update',
											cache:false , 
											dataType:'json',
											data:{
													id:$('#addResourceform').find('input[name=id]').val() ,
											        parentId:parent.id,
													name:$('#addResourceform').find('input[name=name]').val(),
													type:$('#addResourceform').find('select[name=type]').val(),
													url:$('#addResourceform').find('input[name=url]').val(),
													permission:$('#addResourceform').find('input[name=permission]').val()
											} ,
											success:function(r){
												//刷新节点  :如果当前选中的节点是叶子节点的话,刷新该节点的父亲 ,如果不是叶子节点,刷新当前选中节点即可.
												$('#resourceListTree').treegrid('reload' , parent.id);
												$.messager.show({
													title:'提示信息',
													msg:'操作成功!'
												});
											}
										});
										//3关闭窗口
										$('#addResourceDialog').dialog('close');
							}
					});

					$('#resourceListTree').treegrid({
							nowrap: false,
							rownumbers: true,
							collapsible:true,
							url:'resource/getData',			
							idField:'id',				//数据表格要有主键	
							treeField:'name',			//treegrid 树形结构主键 text
							fitColumns:true ,
							columns:[[
								{field:'name',title:'名称',width:200} ,
								{field:'type',title:'类型',width:120} ,
								{field:'url',title:'URL路径',width:120} ,
								{field:'permission',title:'权限字符串',width:120} 
							]],
							onContextMenu: function(e,row){
								e.preventDefault();					//屏蔽浏览器的菜单
								$(this).treegrid('unselectAll');	//清除所有选中项
								$(this).treegrid('select', row.id);	//选中状态 
								$('#resourcem').menu('show', {
									left: e.pageX,
									top: e.pageY
								});
							}
					});
		});

function appendResource(){
						updateFlag='add';
						//1清空表单数据
						$('#addResourceform').form('clear');
						//2打开窗口
						$('#addResourceDialog').dialog('open');
};

function editorResource(){
						updateFlag='edit';
						//1清空表单数据
						$('#addResourceform').form('clear');
						//2填充表单回显数据
						var  node  = $('#resourceListTree').treegrid('getSelected');
						$('#addResourceform').form('load',{
								id:node.id ,
								name:node.name ,
								url:node.url,
								type:node.type ,
								permission:node.permission
						});
						//3打开窗口
						$('#addResourceDialog').dialog('open');
}

function removeResource(){
						$.messager.confirm("提示信息","确认删除?",function(r){
								if(r){
									// 1前台删除
									var  node  = $('#resourceListTree').treegrid('getSelected');
									$('#resourceListTree').treegrid('remove',node.id);
									// 2后台删除 
									$.post('resource/'+node.id+'/delete' , {id:node.id} , function(result){
												$('#resourceListTree').treegrid('unselectAll');
												//刷新节点 : 刷新当前选中节点
												$('#resourceListTree').treegrid('reload',node.id);
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