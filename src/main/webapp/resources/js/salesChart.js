	var	 currentDate = new Date();
	var currentYear = currentDate.getFullYear();    //获取完整的年份(4位,1970-????)
	var temp = currentDate.getMonth()+1;
	var currentMonth = temp<10?'0'+temp:temp;  
	var currentTime = currentYear+"-"+currentMonth;
	
	function flushGrid(type,time){
		$("#grid1").datagrid({
			method:"get",
			url:"allStaffSalesReports?type="+type+"&date="+time,
		});
	}
	
$(function(){
	loadCharts("YM",currentTime);
	flushGrid("YM",currentTime);
	
	$('#sbtn').linkbutton({    
	    iconCls: 'icon-search',
	    height:22,
	    plain:false,
	    onClick:function(){
	    	var type = $("#typeCombox").combobox('getValue');
	    	var time = $("#timeCombox").combobox('getValue');
	    	loadCharts(type,time);
	    	flushGrid(type,time);
	    }
	}); 
	
	/**
	 * 初始化月份选择下拉框
	 * 
	 */
	$('#typeCombox').combobox({  
		width:80,
		panelHeight:60,
		editable:false,
		data: [{
			id: 'Y',
			text: '年销售'
		},{
			id: 'YM',
			text: '月销售'
		}], 
		valueField:'id',    
		textField:'text',
		onChange:function(newValue,oldValue){
				if(newValue == 'YM'){
					createMonthCombox();
				}else{
					createYearCombox();
				}
		},
		onLoadSuccess:function(){
			$('#typeCombox').combobox("select",'YM');
		}
	}); 
	
	
	$("#piepanel").panel({
		title:"321",
		header:"#chartToolbar"
	});
	$("#grid1").datagrid({
		border:0,
		rownumbers:true,
		singleSelect:true,
		scrollbarSize:0,
		method:"get",

	    columns:[[    
	              {field:'ename',title:'员工' ,width:100},    
	              {field:'total',title:'销量(双)',width:100},    
	              {field:'aggregateAmount',title:'销售额(元)',width:100},    
	              {field:'aggregateAmount',title:'百分比',width:100},    
	          ]]
	});
	$(".panel").css({ float:'left'});
})
function createYearCombox(){
	$('#timeCombox').combobox({
		method:'get',
		width:80,
		editable:false,
		url:'getYearReportBoxData',
		valueField:'id',
		textField:'text',
		formatter: function(row){
			var opts = $(this).combobox('options');
			return row[opts.textField];
		},
		onSelect: function(data){
		}
	});
}
function createMonthCombox(){
	$('#timeCombox').combobox({
		method:'get',
		width:80,
		editable:false,
		url:'getMonthReportBoxData',
		valueField:'id',
		textField:'text',
		groupField:'groupField',
		groupFormatter:function(group){
			return '<span style="font-weight:bold">' + group  + '年</span>';
		},
		onSelect: function(data){
		}
	});
}
function loadCharts(type,date){
				var myChart = echarts.init(document.getElementById('main'),'blue');
				myChart.showLoading({
					textStyle : {
						fontSize : 20
					}
				});
				$.ajax({
					type:'get',
					url:'stackBarChart?type='+type+'&date='+date,
					cache:false , 
					dataType:'json',
					success:function(option){
						myChart.hideLoading();
						myChart.setOption(option);
					}
				});
} 
