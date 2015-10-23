$(function(){

	$('#sbtn').linkbutton({    
	    iconCls: 'icon-search'   ,
	    height:22,
	    plain:false,
	    onClick:function(){
	    	var type = $("#typeCombox").combobox('getValue');
	    	var time = $("#timeCombox").combobox('getValue');
	    	loadCharts(type,time);
	    }
	}); 
	
	
	$('#typeCombox').combobox({  
		width:80,
		panelHeight:60,
		editable:false,
		data: [{
			id: 'year',
			text: '年销售'
		},{
			id: 'month',
			text: '月销售'
		}], 
		valueField:'id',    
		textField:'text',
		onChange:function(newValue,oldValue){
				if(newValue == 'month'){
					createMonthCombox();
				}else{
					createYearCombox();
				}
		},
		onLoadSuccess:function(){
			$('#typeCombox').combobox("select",'month');
		}
	}); 

})


require.config({
	paths: {
		echarts: 'http://echarts.baidu.com/build/dist'
	}
});
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
function loadCharts(type,time){
	require(
			[
			 'echarts',
			 'echarts/chart/line',
			 'echarts/chart/bar'
			 ],
			 function (ec) {
				var myChart = ec.init(document.getElementById('main'),'blue');
				myChart.showLoading({
					textStyle : {
						fontSize : 20
					}
				});

				$.ajax({
					type:'get',
					url:'monthData',
					cache:false , 
					dataType:'json',
					data:{type:type,time:time},
					success:function(option){
						myChart.hideLoading();
						myChart.setOption(option);
					}
				});
			}
	)
} 
