$(function() {

	$('#p1').panel({
		style:{float:'left'},
		title:'系统内存',
		width:300,    
		height:150  
	});  
	$('#p2').panel({  
		style:{float:'left'},
		title:'JVM',
		width:300,    
		height:150  
	});  
	$("#grid").datagrid({
		autoRowHeight : true,
		border : false,
		fitColumns: true,
		fit : true,
		iconCls: 'icon-save',
		nowrap : false,
		pagePosition : 'bottom',
		resizeHandle : 'both',
		scrollbarSize : 10,
		singleSelect: true,
		striped : true,  
		columns:[[      
		          {field:'index',title:'CPU序号',width:40,sortable:false,align:'center'},
		          {field:'user',title:'使用率',width:100,sortable:false,align:'center',formatter:formatbfb},
		          {field:'sys',title:'系统使用率',width:100,sortable:false,align:'center',formatter:formatbfb},
		          {field:'wait',title:'当前等待率',width:100,sortable:false,align:'center',formatter:formatbfb},
		          {field:'nice',title:'当前错误率',width:100,sortable:false,align:'center',formatter:formatbfb},
		          {field:'idle',title:'当前空闲率',width:100,sortable:false,align:'center',formatter:formatbfb},
		          {field:'combined',title:'总使用率',width:100,sortable:false,align:'center',formatter:formatbfb}
		          ]]
	});
	function formatbfb(value,row,index){
		return (Math.round(value*100)/1.00).toString()+"%" ;
	}
	url = "serverInfo/getStatusData";

	var idInt = setInterval(function(){
		$.ajax({
			type : "get",
			url : url,
			dataType : 'json',
			success : function(result) {
				$("#memorytotal").html(result.memorytotal);
				$("#memoryused").html(result.memoryused);
				$("#memoryfree").html(result.memoryfree);

				$("#jvmtotalmemory").html(result.jvmtotalmemory);
				$("#jvmfreememory").html(result.jvmfreememory);
				$("#jvmusedmemory").html(result.jvmusedmemory);
				$("#jvmversion").html(result.jvmversion);
				$("#jvmvendor").html(result.jvmvendor);
				$("#jvmname").html(result.jvmname);

				$("#javahome").html(result.javahome);
				$("#javapath").html(result.javapath);
				$("#javaversion").html(result.javaversion);


				$("#diskpath").html(result.diskpath);
				$("#disktotal").html(result.disktotal);
				$("#diskused").html(result.diskused);
				$("#diskfree").html(result.diskfree);
				$('#grid').datagrid("loadData",result.cPU);
			}
		});
	},2000);
});