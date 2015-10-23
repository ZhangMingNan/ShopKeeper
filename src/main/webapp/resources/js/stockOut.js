$(function(){
	$('#stockOutDg').datagrid({
		view: detailview,
		detailFormatter:function(index,row){
			return '<div style="padding:0px"><table class="ddv"></table></div>';
		},
        onExpandRow: function(index,row){
            var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');

            ddv.datagrid({
                url:'stockOutDetailData?id='+row.id,
                fitColumns:true,
                singleSelect:true,
                loadMsg:'正在加载数据',
                height:'auto',
                columns:[[
                    {field:'no',title:'货号',width:'100px'},
                    {field:'total',title:'数量',width:'100px'},
                    {field:'sizeList',title:'尺码(数量)',width:'395px'}
                ]],
                onResize:function(){
                    $('#stockOutDg').datagrid('fixDetailRowHeight',index);
                },
                onLoadSuccess:function(){
                    setTimeout(function(){
                        $('#stockOutDg').datagrid('fixDetailRowHeight',index);
                    },0);
                }
            });
            $('#stockOutDg').datagrid('fixDetailRowHeight',index);
        },
		url:'stockOutData',
		fitColumns:"true",
		singleSelect:"true",
		pageSize:20,
		idField:'id',
		pagination:true,
		  rownumbers:true,
			striped:true,
		columns:[[
		          {field:'date',title:'出库日期',width:'100px'},
		          {field:'total',title:'数量',width:'100px'},
		          {field:'type',title:'原因',width:'400px'}
		          ]]
	});
});

