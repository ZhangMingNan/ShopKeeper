$(function(){
	$('#stockInDg').datagrid({
		view: detailview,
		detailFormatter:function(index,row){
			return '<div style="padding:0px"><table class="ddv"></table></div>';
		},
        onExpandRow: function(index,row){
            var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');

            ddv.datagrid({
                url:'stockInDetailData?id='+row.id,
                fitColumns:false,
                singleSelect:true,
                loadMsg:'正在加载数据',
                height:'auto',
                columns:[[
                    {field:'no',title:'货号',width:100},
                    {field:'total',title:'数量',width:100},
                    {field:'sizeList',title:'尺码(数量)',width:300}
                ]],
                onResize:function(){
                    $('#stockInDg').datagrid('fixDetailRowHeight',index);
                },
                onLoadSuccess:function(){
                    setTimeout(function(){
                        $('#stockInDg').datagrid('fixDetailRowHeight',index);
                    },0);
                }
            });
            $('#stockInDg').datagrid('fixDetailRowHeight',index);
        },
		url:'stockInData',
		fitColumns:"true",
		singleSelect:"true",
		pageSize:20,
		idField:'id',
		pagination:true,
		  rownumbers:true,
			striped:true,
		columns:[[
		          {field:'date',title:'入库日期',width:'100px'},
		          {field:'type',title:'款式',width:'100px'},
		          {field:'total',title:'入库总数',width:'500px'}
		          ]]
	});
});

