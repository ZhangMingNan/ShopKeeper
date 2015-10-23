$(function(){
	$('#menutree').tree({    
	    url:'menuTree'   ,
	    animate:true,
	    lines:true,
	    onlyLeafCheck:true,
	    onLoadSuccess : function(node,data){
	    	var defnode = $('#menutree').tree('find',49);
	    	$('#menutree').tree('select', defnode.target);
	    },
	    onSelect: function(node){
			var src = node.attributes.url;
			var title = node.text;
			if($('#tt').tabs('exists' ,title)){
				$('#tt').tabs('select',title);
			} else {
				$('#tt').tabs('add',{   
					title:title, 
					cache:false, 
					border:false,
					content:'<iframe frameborder=0 scrolling=false style=width:100%;height:100% src='+ src +' ></iframe>',   
					closable:true  
				});  
			}
			
		}
	}); 
});