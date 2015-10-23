
function reloadStockReportDg(){
	$('#stockReportDg').datagrid('reload',{
		type: $('#groupTypeSelect').combobox('getValue'),
		shortNo:  $('#productNoBox').combobox('getValue')
	});
}
$(function(){
	$('#productNoBox').combobox({
		editable:true,
		url:'getShortProductNoData',
		valueField:'id',
		textField:'text',
		method:'get',
		onSelect: function(value){
			reloadStockReportDg();
		}
	});	
	
	$('#groupTypeSelect').combobox({
		editable:false,
		method:'get',
		url:'getTypeData',
		valueField:'id',
		textField:'text',
		onSelect: function(value){
			//重新加载指定类型的货号。
			reloadStockReportDg();
		}
	});
	
	$('#stockReportDg').datagrid({
		toolbar:"#stockReportToolbar",
		url:'reportData',
		singleSelect:true,
		method:'get',
		multiSort:false,
		rownumbers:true,
		showFooter: true,
		pageSize:20,
		pagination:true,
		striped:true,
		columns:[[
		          {field:'id',hidden:true},
		          {field:'productNo',title:'货号',width:'100px'},
		          {field:'stockNumber',title:'库存量(双)',width:'100px'},
		          {field:'sizeDetail',title:'尺码(数量)',width:'100px'}
		          ]]
	});
});