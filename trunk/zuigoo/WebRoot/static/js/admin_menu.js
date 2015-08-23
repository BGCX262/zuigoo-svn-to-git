var onlyOne = true;//一次只能打开一个菜单，布尔值；
var menuId = "soshopmenu";//伸缩菜单ID
var trigFlag = false;

function t$() 
{ 
  var elements = new Array(); 
  for (var i = 0; i < arguments.length; i++) 
  { 
    var element = arguments[i]; 
    if (typeof element == 'string') 
      element = document.getElementById(element); 
    if (arguments.length == 1) 
      return element; 
    elements.push(element); 
  } 
  return elements; 
}

//筛选指定ID下的所有子标签
function getChildObj(parname,childtype){
	var tagchild=t$(parname).childNodes;
	var liarray = [];//建立数组
	for(var i = 1; i <= tagchild.length; i ++){				
		var liins = tagchild[i-1];//取得parname下面所有标签
		if(childtype == liins.nodeName){//筛选出childtype标签,childtype必须大写
			liarray.push(liins);
		}
	}
	return liarray;
	
}  

//伸缩菜单显示状态切换
function menuShow(id){
		var myLI = getChildObj(menuId,'LI');
		//alert(linum.length);
		//一次打开一个菜单为真，则关闭所有菜单，ID相同则不作为；为假则不作为
		if(onlyOne){
			for(var i=1;i <= myLI.length; i++){
				if(myLI[i-1].id == id){
					continue;
				}
				myLI[i-1].className ='menuoff';
			}
		}
		//切换菜单闭合状态
 		if(t$(id).className=='menuoff'){
			t$(id).className='menuon';
		}else{
			t$(id).className='menuoff';
		}	 
	    
}

function menuShowtop(id){
	var myLI = getChildObj(menuId,'LI');
	for(var i=1;i <= myLI.length; i++){
		if(myLI[i-1].id == id){
			continue;
		}
		myLI[i-1].className ='menuoff';
	}
	if(t$(id).className=='menuoff'){
		t$(id).className='menuon';
	}else{
		//t$(id).className='menuoff';
	}
}

//双击标题栏展开或收缩全部菜单
function trigAll(){
		var myLI = getChildObj(menuId,'LI');
		//只有全部打开才能收缩
		for(var i=1;i <= myLI.length; i++){
			if(myLI[i-1].className=="menuon"){
				trigFlag=true;
			}else{
				trigFlag=false;//没有全部展开
			}
		}
		//trigFlag为真，说明已经全部展开，没有全部展开，则先展开
		if(trigFlag){
			for(var i=1;i <= myLI.length; i++){
				myLI[i-1].className = "menuoff";
				trigFlag=false;
			}
		}else{
			for(var i=1;i <= myLI.length; i++){
				myLI[i-1].className = "menuon";
				trigFlag=true;
			}			
		}
}