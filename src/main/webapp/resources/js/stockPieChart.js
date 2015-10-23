function showChart(ssss){
	 $('#container').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        title: {
	            text: '威加海内兮，归四方！'
	        },
	        tooltip: {
	    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    color: '#000000',
	                    connectorColor: '#000000',
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
	                }
	            }
	        },
	        series:ssss
	    });
}
$(function(){
	$.ajax({
		type:'get',
		url:'salesReport/gethcd',
		cache:false , 
		success:function(dd){
		showChart(dd);
		},
		error: function(){
			alert(arguments[1]);
		}
	});
});