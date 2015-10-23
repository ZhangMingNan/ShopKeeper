var  updateFlag;


function removeTR(){
	var row = $('#tradingRecordDg').datagrid('getSelected');
	if(row){
		$.messager.confirm("提示信息","确认删除?",function(r){
			if(r){

				// 2后台删除 
				$.post('tradingRecord/delete' , {id:row.id,sspId:row.stockProductId},function(result){
					$.messager.alert('提示信息','商品已经重新入库!');
					$('#tradingRecordDg').datagrid('reload');
				});
			} else {
				return ;
			}
		});
	}else{
		$.messager.confirm("提示","请至少选中一行记录！",function(r){});
	}
}

function add(){
	updateFlag = 'create';
//	1清空表单数据
	$('#addtradingRecordform').form('clear');
//	2打开窗口
	$('#addtradingRecordDg').dialog('open');

}


function removeOrderItem(){
	var row = $('#orderDg').datagrid('getSelected');
	var rowIndex = $('#orderDg').datagrid('getRowIndex', row)
	$('#orderDg').datagrid('deleteRow',rowIndex);
}

function updatePrice(){
	var row = $('#orderDg').datagrid('getSelected');
	var price = row.price;
	$("#newPrice").val(price);
	$('#updatePriceDg').dialog('open');
}

function updateOrderPrice(){
	var row = $('#orderDg').datagrid('getSelected');
	row.sellingPrice = $("#newPrice").val();
	var rowIndex = $('#orderDg').datagrid('getRowIndex', row)
	$('#orderDg').datagrid('refreshRow',rowIndex);
	$('#updatePriceDg').dialog('close');
}



function submitOrder(){
	var rows = 	$('#orderDg').datagrid("getRows");
	var orderData = {
			payment:$('#paymentBox').combobox('getValue'),
			employeeId:$('#employeeBox').combobox('getValue'),
			memberBarCode:$('#memberBarCodeTextBox').numberbox('getValue')
	} ;
	for(var i=0;i<rows.length;i++){
		orderData['stockProducts['+i+'].barCode'] = rows[i].barCode;
		orderData['stockProducts['+i+'].id'] = rows[i].id;
		orderData['stockProducts['+i+'].sellingPrice'] = rows[i].sellingPrice;
	}

	//提交数据。
	$.ajax({
		type:'post',
		url:'tradingRecord/create',
		cache:false , 
		dataType:'json',
		data: orderData,
		success:function(r){
			$('#tradingRecordDg').datagrid('reload');
			$.messager.show({
				title:'提示信息',
				msg:'操作成功!'
			});
			//清空表格数据！
			var rows = 	$('#orderDg').datagrid("getRows");
			var dgcount = rows.length;
			for(var i=0;i<dgcount;i++){
				var b = $('#orderDg').datagrid('getRowIndex',rows[i]);
				$('#orderDg').datagrid("deleteRow",b)
			}
			$('#addtradingRecordDg').dialog('close');
		}
	});

}

$(function(){

	$('#detailgrid').datagrid({    
		toolbar:'#detailToolbar',
		border:false,
		fit:true,
		scrollbarSize: 0,
		fitColumns:true,
		onBeforeRender:function(target,rows){
			console.log(target);
		},
		columns:[[    
		          {field:'name',title:'属性',width:100},    
		          {field:'value',title:'信息',width:100}
		          ]] 
	}); 

	$('#barCodeTextBox').searchbox({ 
		searcher:function(barCode,name){ 
			//根据输入的条码查询
			$.ajax({
				type:'post',
				url:'stock/getByBarCode',
				cache:false , 
				dataType:'json',
				data:{barCode:barCode},
				error:function(e){
					$.messager.alert('提示信息','网络异常,检查网络是否链接!');
				},
				success:function(data){
					if(data.error){
						$.messager.alert('提示信息','	没有在库存中查找的对应的条码,请检查输入是否正确!');
					}else{
						$('#barCodeTextBox').searchbox('setText','');	
						//$('#orderDg').datagrid('appendRow',data.vo);
						$('#productId').val(data.vo.id);
						$('#detailgrid').datagrid("loadData",     
								[
							       {name:'款式', value:data.vo.type},
							       {name:'货号', value:data.vo.productNo},
							       {name:'尺码', value:data.vo.size},
							       {name:'售价', value:data.vo.sellingPrice},
							       {name:'条码', value:data.vo.barCode},
							       ]
						);
						$('#detailDg').dialog('open');	
					}
				}
			});
		},
		prompt:'请输入条码！' 
	}); 

	$('#detailDg').dialog({
		title: '确认销售',
		width: 300,
		height: 250,
		closed: true,
		cache: false,
		modal: true,
		inline:false,
		buttons: [{  
			text: '确定',  
			iconCls: 'icon-save',  
			handler: function () {  

				var rows = 	$('#detailgrid').datagrid("getRows");
				var orderData = {
						payment:$('#paymentBox').combobox('getValue'),
						employeeId:$('#employeeBox').combobox('getValue')
				} ;
				//memberBarCode:$('#memberBarCodeTextBox').numberbox('getValue')
				orderData['stockProduct.barCode'] = rows[4].value;
				orderData['stockProduct.id'] = $('#productId').val();
				orderData['stockProduct.sellingPrice'] = rows[3].value;
				//提交数据。
				$.ajax({
					type:'post',
					url:'tradingRecord/create',
					cache:false , 
					dataType:'json',
					data: orderData,
					success:function(r){
						$('#productId').val('');
						$('#tradingRecordDg').datagrid('reload');
						$('#detailDg').dialog('close');
						$.messager.show({
							title:'提示信息',
							msg:'操作成功!'
						});
					}
				});
			}  	
		}, {  
			text: '取消',  
			handler: function () {    
				$('#detailDg').dialog('close');	
			}  
		}]  
	});


	$('#updatePriceDg').dialog({
		title: '修改销售价格',
		width: 300,
		height: 250,
		closed: true,
		cache: false,
		modal: true,
		inline:false,
		buttons: [{  
			text: '确定',  
			iconCls: 'icon-save',  
			handler: function () {  
				updateOrderPrice();
			}  	
		}, {  
			text: '取消',  
			handler: function () {    

			}  
		}]  
	});

	$('#addtradingRecordDg').dialog({
		title: '添加销售记录',
		width: '680px',
		height: '300px',
		closed: true,
		cache:  false,
		modal: true,
		inline:false,
		style:'{left:100px;top:100px}',
		buttons: [{  
			text: '确定',  
			iconCls: 'icon-save',  
			handler: function () {  
				submitOrder();
			}  	
		}, {  
			text: '取消',  
			handler: function () {    
				$('#addtradingRecordDg').dialog('close');	
			}  
		}]  
	});
	


	$('#employeeBox').combobox({
		editable:false,
		method:'get',
		url:'employee/getEmployeeCombotBoxData',
		valueField:'id',
		textField:'text',
		onSelect: function(res){

		}
	});

//	$('#barCodeTextBox').textbox({
//	onClickButton:function(){
//	var newValue = $('#barCodeTextBox').textbox('getText');	
//	if(newValue != '' ){
//	insertToTable(newValue);

//	}
//	}
//	});

	$('#paymentBox').combobox({
		editable:false,
		method:'get',
		url:'productPayment/getProductPaymentCombotBoxData',
		valueField:'id',
		textField:'text',
		onSelect: function(res){

		}
	});
	$('#orderDg').datagrid({
		singleSelect:true,
		multiSort:false,
		rownumbers:true,
		onRowContextMenu: function(e, rowIndex, rowData){
			e.preventDefault();					//屏蔽浏览器的菜单
			$('#trMenu').menu('show', {
				left: e.pageX,
				top: e.pageY
			})},
			columns:[[
			          {field:'id',hidden:true},
			          {field:'barCode',title:'条码',width:100},
			          {field:'productNo',title:'货号',width:100,sortable:true},
			          {field:'type',title:'款式',width:100},
			          {field:'size',title:'尺码',width:100,sortable:true},
			          {field:'sellingPrice',title:'销售价格',width:100,sortable:true}
			          ]]
	});

	$('#reflushButton').linkbutton({
		iconCls:'icon-reload',
		height:22,
		plain:true,
		text: '刷新'
	});
	$('#reflushButton').bind('click', function(){
		$('#tradingRecordDg').datagrid('reload');
	});


	$('#tradingButton').linkbutton({
		iconCls:"icon-ok",
		height:22,
		plain:true,
		text: '售出'
	});
	$('#tradingButton').bind('click', function(){
		add();
	});

	$('#removeTradingButton').linkbutton({
		iconCls:"icon-remove",
		height:22,
		plain:true,
		text: '删除'
	});
	$('#removeTradingButton').bind('click', function(){
		removeTR();
	});



	$('#tradingRecordDg').datagrid({
		toolbar:"#tradingRecordToolbar",
		url:'tradingRecord/getData',
		fitColumns:true,
		singleSelect:true,
		multiSort:false,
		rownumbers:true,
		pagination:false,
		showFooter: true,
		striped:true,
		method:'get',
		columns:[[
		          {field:'stockProductId',hidden:true},
		          {field:'type',title:'款式',width:'10%'},
		          {field:'productInfo',title:'货号',width:'10%'},
		          {field:'size',title:'尺码',width:'10%'},
		          {field:'sale',title:'销售价格',width:'10%'},
		          {field:'employeeName',title:'销售人员',width:'10%'},
		          {field:'paymentStr',title:'付款方式',width:'10%'},
		          {field:'date',title:'销售时间'}
		          ]]
	});

	var pager = $('#tradingRecordDg').datagrid().datagrid('getPager');   
	pager.pagination({
		buttons:[{
			text:'销售',
			handler:function(){
				add();
			}
		},{
			iconCls:'icon-remove',
			text:'删除销售记录',
			handler:function(){
				removeTR();
			}
		}]
	});            
	$('#tradingRecordDateBox').datebox({
		onSelect: function(){
			//带上时间参数刷新表格。
			$('#tradingRecordDg').datagrid('reload', {
				date:$('#tradingRecordDateBox').datebox('getValue')
			});
		}
	});

	var cTime = getCurrentTime();
	$("#tradingRecordDateBox").datebox("setValue", cTime); 

});