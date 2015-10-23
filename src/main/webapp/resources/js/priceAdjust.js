



function editPrice(){
	//2填充表单回显数据
	var rows = $('#priceAdjustDg').datagrid('getSelections');
	var idList = [];
	if(rows){
		for(var i=0;i<rows.length;i++){
			idList.push(rows[i].id);
		}
		console.log(idList);
		$('#editPriceAdjustform').form('load',{
			price:rows[0].price,
			sellingPrice:rows[0].sellingPrice,
			ids:idList.join(',')
		});	
		$('#editPriceAdjustDg').dialog('open');
	}else{
		$.messager.confirm("提示","请至少选中一行记录！",function(r){});
	}
}

$(function(){
	$('#editPriceAdjustDg').dialog({
		width: '300px',
		height: '150px',
		closed: true,
		cache:  false,
		modal: true,
		inline:false,
		buttons: [{  
			text: '确定',  
			iconCls: 'icon-save',  
			handler: function () {  
				var v=	$('#editPriceAdjustform').form('validate');
				if(v){
					$.ajax({
						type:'post',
						url:'priceAdjust/update',
						cache:false , 
						dataType:'json',
						data:{
							price:$('#editPriceAdjustform').find('input[name=price]').val(),
							sellingPrice:$('#editPriceAdjustform').find('input[name=sellingPrice]').val(),
							ids:$('#editPriceAdjustform').find('input[name=ids]').val()
						} ,
						success:function(r){
							$('#priceAdjustDg').datagrid('reload');
							$.messager.show({
								title:'提示信息',
								msg:'操作成功!'
							});
						}
					});
					//3关闭窗口
					$('#editPriceAdjustDg').dialog('close');
				}
			}  	
		}, {  
			text: '取消',  
			handler: function () {    
				$('#editPriceAdjustDg').dialog('close');	
			}  
		}]  
	});

	$('#priceAdjustDg').datagrid({
		url:'priceAdjust/getData',
		singleSelect:false,
		multiSort:false,
		rownumbers:true,
		pagination:true,
		pageSize:20,
		striped:true,
		columns:[[
		          {field:'id',checkbox:true},
		          {field:'typeValue',title:'款式',width:100},
		          {field:'productNo',title:'货号',width:100},
		          {field:'price',title:'进货价格',width:100},
		          {field:'sellingPrice',title:'销售价格',width:100},
		          {field:'salesVolume',title:'累计销量',width:100}
		          ]]
	});
	var pager = $('#priceAdjustDg').datagrid().datagrid('getPager');   
	pager.pagination({
		buttons:[{
			iconCls:'icon-edit',
			text:'批量修改价格',
			handler:function(){
				editPrice();
			}
		}]
	});       
})