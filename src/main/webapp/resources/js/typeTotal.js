$(function(){
	$('#typeTotalDg').datagrid({
		url:'typeTotal',
		fitColumns:true,
		singleSelect:true,
		pagination:false,
		showFooter: true,
		rownumbers:true,
		striped:true,
		columns:[[
		          {field:'type',title:'款式',width:"20%"},
		          {field:'total',title:'库存量',width:"79%"}
		          ]]
	});
});