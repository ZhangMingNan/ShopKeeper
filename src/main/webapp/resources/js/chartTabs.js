$(function(){
	addTabFrame("员工销售情况统计","salesChart");
})

function addTabFrame(title,url){
	$('#tt').tabs('add',{    
	    title:title,    
	    content:'<iframe frameborder=0 style="width:100%;height:100%" src='+ url +' ></iframe>',    
	    closable:false,    
	    tools:[{    
	        iconCls:'icon-mini-refresh',    
	        handler:function(){    
	            alert('refresh');    
	        }    
	    }]    
	}); 
}