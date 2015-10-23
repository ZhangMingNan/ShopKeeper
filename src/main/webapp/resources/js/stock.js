var  updateFlag;
function openStockOutDg(){
	$('#stockOutBarCodeList').textbox('setText','');	
	$('#stockOutDg').dialog('open');
}

function openReturnGoodDg(){
	$('#returnGoodDg').dialog('open');
}

function openBarcodeListDg(){
	$('#barCodeTextBox').textbox('setText','');	
	$('#barcodeListDg').dialog('open');
}

function progress(str){
	var win = $.messager.progress({
		title:'提示',
		msg:str
	});
}

function edit(){
	//2填充表单回显数据
	var row = $('#stockDg').datagrid('getSelected');
	if(row){
		$('#addStockform').form('load',{
			id:row.id,
			sellingPrice:row.sellingPrice,
			barCode:row.barCode
		});	
		$("#productNoSelect").combobox('setText',row.productNo);
		$("#productNoSelect").combobox('setValue',row.productNoId);
		$("#stockTypeSelect").combobox('setText',row.type);
		$("#stockTypeSelect").combobox('setValue',row.productTypeId);
		$("#stockSizeSelect").combobox('setText',row.size);
		$("#stockSizeSelect").combobox('setValue',row.productSizeId);
		updateFlag = 'update';
		$('#addStockDg').dialog('open');
	}else{
		$.messager.confirm("提示","请至少选中一行记录！",function(r){});
	}
}
function removeProduct(){
	$.messager.confirm("提示信息","确认删除?",function(r){
		if(r){
			var row = $('#stockDg').datagrid('getSelected');
			// 2后台删除 
			$.post('stock/'+row.id+'/delete' , {id:row.id},function(result){
				$('#stockDg').datagrid('reload');
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



function stockOutDetail(){
	$.ajax({
		type:'post',
		url:'stock/splitBarCode',
		cache:false , 
		dataType:'json',
		data:{barCode: $('#stockOutBarCodeList').textbox('getText'),isStockIn:false},
		success:function(datas){
			$.messager.progress('close');
			$('#stockOutDg').dialog('close'); 
			$('#stockOutDetailDg').dialog('open');
			$('#stockOutDetailTable').datagrid('loadData',datas);
		}
	});
}

$(function(){
	$('#stockOutDetailDg').dialog({
		title: '批量出库明细',
		width:400,
		height:500,
		closed: true,
		cache:  false,
		modal: true,
		inline:false,
		buttons: [{  
			text: '确认出库',  
			iconCls: 'icon-save',  
			handler: function () {  
				progress('正在出库...');
				var typeId =  $('#stockOutTypeSelect').combobox('getValue');
				var codeList = $('#stockOutBarCodeList').textbox('getText');
				if(typeId == ""){
					alert("请选择出库原因！");
					return ;
				}
				$('#stockOutDetailDg').dialog('close');
				$.ajax({
					type:'post',
					url:'stock/saveStockOut',
					cache:false , 
					dataType:'json',
					data:{barCode:codeList,typeId:typeId},
					success:function(data){
						$.messager.progress('close');
					}
				});
			}
		} , {  
			text: '取消',  
			handler: function () {    
				$('#stockOutDetailDg').dialog('close');
			}  
		}] 
	});

	$('#stockOutBarCodeList').textbox({multiline:true});
	$('#stockOutDg').dialog({
		title: '批量输入商品条码进行出库',
		width:400,
		
		height:500,
		closed: true,
		cache:  false,
		modal: true,
		inline:false,
		buttons: [{  
			text: '生成出库明细',  
			iconCls: 'icon-save',  
			handler: function () { 
				if($('#stockOutBarCodeList').textbox('getText') == ""){
					alert("请输入条码!");
					return ;
				}
				progress('生成出库明细...');
				stockOutDetail();
			}
		} , {  
			text: '取消',  
			handler: function () {    
				$('#stockOutDg').dialog('close');
			}  
		}] 
	});
	$('#returnGoodDg').dialog({
		title: '返货单',
		width:400,
		height:500,
		closed: true,
		cache:  false,
		modal: true,
		inline:false,
		buttons: [{  
			text: '确定',  
			iconCls: 'icon-save',  
			handler: function () { 

			}
			} , {  
			text: '取消',  
			handler: function () {    
				$('#stockOutDg').dialog('close');
			}  
		}] 
	
	});
	//2－24
	$('#barCodeTextBox').textbox({multiline:true});
	$('#barcodeListDg').dialog({
		title: '批量输入商品条码',
		width:400,
		height:500,
		inline:false,
		closed: true,
		cache:  false,
		modal: true,
		buttons: [{  
			text: '生成入库明细',  
			iconCls: 'icon-save',  
			handler: function () {  
				var typeId =  $('#groupTypeSelect').combobox('getValue');
				var newValue = $('#barCodeTextBox').textbox('getText');	
				if(typeId == ""){
					alert("请选择款式!");
					return ;
				}
				if(newValue == ""){
					alert("请输入条码!");
					return ;
				}
				
				
				//关闭当前对话框
				$('#barcodeListDg').dialog('close');

				if(newValue != '' ){
					//弹出等待窗口。
					progress('正在处理条码...');
					if (newValue.length > 8){
						$.ajax({
							type:'post',
							url:'stock/splitBarCode',
							cache:false , 
							dataType:'json',
							data:{barCode:newValue,isStockIn:true,type:typeId},
							success:function(datas){
								$('#addStockProductDg').dialog('open');
								$('#stockProductTable').datagrid('loadData',datas);
								$.messager.progress('close');
							}
						});

					}else{
						alert("错误的条码！");
					}
				}

			}  	
		}, {  
			text: '取消',  
			handler: function () {    
				$('#barcodeListDg').dialog('close');
			}  
		}] 
	});	



	$('#addStockProductDg').dialog({
		title: '添加库存',
		width:400,
		height:500,
		closed: true,
		cache:  false,
		modal: true,
		inline:false,
		buttons: [{  
			text: '确定入库',  
			iconCls: 'icon-save',  
			handler: function () {
				$('#addStockProductDg').dialog('close');
				progress('正在入库...');
				//提交一批新入库的条形码和款式信息。
				//获取条码
				var codeList = $('#barCodeTextBox').textbox('getText');	
				var typeId =  $('#groupTypeSelect').combobox('getValue');
				$.ajax({
					type:'post',
					url:'stock/saveProductList',
					cache:false , 
					dataType:'json',
					data:{codeList:codeList,typeId:typeId},
					success:function(data){
						$.messager.progress('close');
						var rows = 	$('#stockProductTable').datagrid("getRows");
						var dgcount = rows.length;
						for(var i=0;i<dgcount;i++){
							var b = $('#stockProductTable').datagrid('getRowIndex',rows[i]);
							$('#stockProductTable').datagrid("deleteRow",b)
						}
						$('#stockDg').datagrid('reload');
					}
				});
			}  	
		}, {  
			text: '取消',  
			handler: function () {    
				$('#addStockProductDg').dialog('close');	
			}  
		}] 
	});	


	$('#stockOutDetailTable').datagrid({
		singleSelect:false,
		multiSort:false,
		rownumbers:true,
		fitCloumns: true,
		showFooter: false,
		striped:true,
		columns:[[
		          {field:'productNo',title:'货号',width:"100"},
		          {field:'total',title:'数量',width:"150"},
		          {field:'sizeDetail',title:'尺码',width:"150"}
		          ]]
	});

	$('#stockProductTable').datagrid({
		singleSelect:false,
		multiSort:false,
		rownumbers:true,
		fitCloumns: true,
		showFooter: true,
		columns:[[
		          {field:'productNo',title:'货号',width:"100"},
		          {field:'total',title:'数量',width:"100"},
		          {field:'sizeDetail',title:'尺码',width:"150"}

		          ]]
	});



	$("#stockCancelBut").click(function(){
		//3关闭窗口
		$('#addStockProductDg').dialog('close');
	});

	$("#stockOkBut").click(function(){
		$.ajax({
			type:'post',
			url:'stock/'+updateFlag,
			cache:false , 
			dataType:'json',
			data:{
				id:$('#addStockform').find('input[name=id]').val(),
				'productNo.id' : $('#productNoSelect').combobox('getValue'),
				'productType.id' : $('#stockTypeSelect').combobox('getValue'),
				'productSize.id' :  $('#stockSizeSelect').combobox('getValue'),
				'productType.type' : $('#stockTypeSelect').combobox('getText'),
				'productSize.size' :  $('#stockSizeSelect').combobox('getText'),
				sellingPrice:$('#addStockform').find('input[name=sellingPrice]').val(),
				barCode:$('#addStockform').find('input[name=barCode]').val(),
				productNo:$('#addStockform').find('input[name=productNo]').val()
			} ,
			success:function(r){
				$('#addStockDg').dialog('close');		
				$('#stockDg').datagrid('reload');
				$.messager.show({
					title:'提示信息',
					msg:'操作成功!'
				});
			}
		});

	});

	//获取商品尺寸信息。
	$('#stockSizeSelect').combobox({
		method:'get',
		editable:false,
		url:'stock/getProductSizeData',
		valueField:'id',
		textField:'text',
	});
	$('#productNoBox').combobox({
		editable:true,
		url:'stock/getProductNoData',
		valueField:'id',
		textField:'text',
		onSelect: function(){
			query();
		}
	});
	$('#stockTypeSelect').combobox({
		method:'get',
		editable:false,
		url:'stock/getTypeData?isContainsAll=false',
		valueField:'id',
		textField:'text'
	});

	$('#productNoSelect').combobox({
		method:'get',
		editable:false,
		url:'stock/getProductNoComboboxData',
		valueField:'id',
		textField:'text'
	});

	$('#stockOutTypeSelect').combobox({
		method:'get',
		editable:false,
		url:'stock/getStockOutTypeComboboxData',
		valueField:'id',
		textField:'text'
	});

	$('#groupTypeSelect').combobox({
		editable:false,
		method:'get',
		url:'stock/getTypeData',
		valueField:'id',
		textField:'text',
		onSelect: function(res){
			//重新加载指定类型的货号。
			$('#productNoBox').combobox('clear');
			$('#productNoBox').combobox('reload', 'stock/getProductNoData?typeId='+res.id);
			query();
		}
	});

	$('#typeBox').combobox({
		editable:false,
		method:'get',
		url:'stock/getTypeData',
		valueField:'id',
		textField:'text',
		onSelect: function(res){
			//重新加载指定类型的货号。
			$('#productNoBox').combobox('clear');
			$('#productNoBox').combobox('reload', 'stock/getProductNoData?typeId='+res.id);
			query();
		}
	});
	$('#createDate').datebox({
		onSelect: function(){
			query();
		}
	});

	
	$('#returnNoTextBox').textbox({
	    buttonText:'确定',
	    prompt:'输入货号使用逗号隔开.',
	    iconAlign:'right',
	    onClickButton:function(){

	    	//将输入的货号转换成数组.
	    	var nolist = $("#returnNoTextBox").textbox("getText");
	    	//使用空格区分不同货号
	    	var noArray = nolist.split(" ");
	    	$('#noTree').tree({
	    		url:"stock/noTree",
	    		checkbox:true,
	    		lines:true,
	    		queryParams:{"noList":noArray},
	    		onLoadSuccess:function(node, data){
	    	    	//弹出货号选择树
	    	    	$('#noTreeDg').dialog('open');
	    		}
	    	});
	    
	    }
	})
	

	$('#noTreeDg').dialog({
		title: '选择货号',
		width:400,
		height:500,
		closed: true,
		cache:  false,
		modal: true,
		inline:false,
		buttons: [{  
			text: '确定',  
			iconCls: 'icon-save',  
			handler: function () {
				var nodes = $('#noTree').tree('getChecked');	
				var noidArray = [];
				nodes.forEach(function(node){
					if( $("#noTree").tree('isLeaf', node.target)){
						noidArray.push(node.id);
					}
				});

				$('#returnGoodsDataGrid').datagrid({
					url:"stock/noDetail",
					queryParams:{"noIds":noidArray}
				});
				
				noidArray = [];
				$('#noTreeDg').dialog("close");
			}  	
			}, {  
			text: '取消',  
			handler: function () {    

			}  
		}] 
	});	

	
	
	$('#returnGoodsDataGrid').datagrid({
		toolbar:"#returnGoodsToolbar",
		singleSelect:true,
	
		multiSort:false,
		rownumbers:true,
		striped:true,
		columns:[[
		          {field:'id',hidden:true},
		          {field:'productNo',title:'货号',width:'30%'},
		          {field:'stockNumber',title:'库存量(双)',width:'30%'},
		          {field:'sizeDetail',title:'尺码(数量)',width:'38%'}
		          ]]
	});

	$('#stockDg').datagrid({
		toolbar:"#stockToolbar",
		url:'stock/getData',
		singleSelect:true,
		multiSort:false,
		rownumbers:true,
		pagination:true,
		pageSize:20,
		striped:true,
		queryParams:{
			productNo:  $('#productNoBox').combobox('getValue'),
			typeId:$('#typeBox').combobox('getValue'),
			date:$('#createDate').datebox('getValue')
		},
		columns:[[
		          {field:'barCode',title:'条码',width:120},
		          {field:'productTypeId',hidden:true},
		          {field:'type',title:'款式',width:60},
		          {field:'productNoId',hidden:true},
		          {field:'productNo',title:'货号',width:100,sortable:true},
		          {field:'productSizeId',hidden:true},
		          {field:'size',title:'尺码',width:60,sortable:true},
		          {field:'sellingPrice',title:'销售价格',width:80,sortable:false},
		          {field:'date',title:'入库时间',width:70,sortable:true},
		          {field:'inventoryDay',title:'已入库天数',width:70,sortable:false},
		          ]]
	});

	var pager = $('#stockDg').datagrid().datagrid('getPager');   
	pager.pagination({
		buttons:[{
			iconCls:'icon-save',
			text:'批量入库',
			handler:function(d){
				openBarcodeListDg();
			}
		},{
			iconCls:'icon-undo',
			text:'批量出库',
			handler:function(d){
				openStockOutDg();
			}
		},{
			iconCls:'icon-undo',
			text:'返货',
			handler:function(d){
				openReturnGoodDg();
			}
		},{
			iconCls:'icon-edit',
			text:'修改',
			handler:function(){
				edit();
			}
		}]
	});         
	
});

function query(){
	$('#stockDg').datagrid('reload',{
		productNo:  $('#productNoBox').combobox('getValue'),
		typeId:$('#typeBox').combobox('getValue'),
		date:$('#createDate').datebox('getValue')
	});
}

