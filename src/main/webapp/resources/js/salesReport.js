//创建年报表选择器。
function createYearReportBox(){
	$('#yearReportBox').combobox({
		method:'get',
		editable:false,
		url:'salesReport/getYearReportBoxData',
		valueField:'id',
		textField:'text',
		onLoadSuccess:function(datas){
		$("#yearReportBox").combobox('select',datas[0].text);
		updateYearData(datas[0]);
		},
		onSelect: function(data){
			updateYearData(data);
		}
	});
}

function updateYearData(data){
	//当年份发生变化时判断，报表类型是年类型还是月类型。
	var type = $('#reportTypeBox').combobox('getValue');
	if(type == '1'){
		//加载这一年的数据。
		loadYearData(data.text);
	}else if(type == '2'){
		//月类型
		var year = $("#yearReportBox").combobox('getText');
		$('#monthReportBox').combobox({url:'salesReport/getMonthReportBoxData?year='+year});
	}

}
function loadYearData(year){
	$('#salesReportDg').datagrid({
	    url:'salesReport/getData',
	    fitColumns:"true",
	    singleSelect:"true",
	    queryParams: {year: year},
	    columns:[[
	        {field:'date',title:'时间',width:100},
	        {field:'count',title:'数量',width:100},
	        {field:'totalSale',title:'销售额',width:100}
	    ]]
	});

}
function loadMonthData(year,month){
	$('#salesReportDg').datagrid({
	    url:'salesReport/getData',
	    fitColumns:"true",
	    singleSelect:"true",
	    queryParams: {year: year,month: month},
	    columns:[[
	        {field:'date',title:'时间',width:100},
	        {field:'count',title:'数量',width:100},
	        {field:'totalSale',title:'销售额',width:100}
	    ]]
	});

}

//创建月份选择下拉框
function createMonthReportBox(){
$('#monthReportBox').combobox({
		method:'get',
		editable:false,
		url:'salesReport/getMonthReportBoxData',
		valueField:'id',
		textField:'text',
		onSelect: function(data){
			//加载这一个月的数据。
			var year = $("#yearReportBox").combobox('getText');
			loadMonthData(year,data.text);
		}
	});
}

function createDayReportBox(){
	$('#dayReportDateBox').datebox({
		onSelect: function(date){
		loadDayData(date.getFullYear(),(date.getMonth()+1),date.getDate());
	}
});
}
function loadDayData(year,month,day){
	$('#salesReportDg').datagrid({
	    url:'salesReport/getData',
	    fitColumns:"true",
	    singleSelect:"true",
	    queryParams: {year: year,month: month,day:day},
	    columns:[[
	        {field:'date',title:'时间',width:100},
	        {field:'count',title:'数量',width:100},
	        {field:'totalSale',title:'销售额',width:100}
	    ]]
	});

}

$(function(){
	 
	$('#salesReportDg').datagrid({
	    toolbar:"#salesReportToolbar",
	    singleSelect:true,
	    multiSort:false,
	    rownumbers:true
	});
	
//	createDayReportBox()
createYearReportBox();
createMonthReportBox();
createDayReportBox();
	//隐藏不需要展示的组件
	$("#yearReportBoxDiv").hide();
	$("#monthReportBoxDiv").hide();
	$("#dayReportBoxDiv").hide();
	
	//创建报表类型选择框。
	$('#reportTypeBox').combobox({
		method:'get',
		editable:false,
		url:'salesReport/getReportTypeBoxData',
		valueField:'id',
		textField:'text',
		onSelect: function(data){
			var typeId = data.id;
			if(typeId == '1'){
			//创建年报表选择器。
				$("#dayReportBoxDiv").hide();
				$("#monthReportBoxDiv").hide();
				$("#yearReportBoxDiv").show();
			}else if(typeId == '2'){
				$("#dayReportBoxDiv").hide();
				$("#yearReportBoxDiv").show();
				
				//加载月份数据。
				var year = $("#yearReportBox").combobox('getText');
				$('#monthReportBox').combobox({url:'salesReport/getMonthReportBoxData?year='+year});
				
				$("#monthReportBoxDiv").show();
			}else if(typeId == '3'){
				$("#yearReportBoxDiv").hide();
				$("#monthReportBoxDiv").hide();
				$("#dayReportBoxDiv").show();

			}
		}
	});
	
})


