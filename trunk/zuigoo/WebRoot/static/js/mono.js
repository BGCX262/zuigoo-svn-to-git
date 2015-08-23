function $(id){//灰常大众化的方法呐...
	return 	document.getElementById(id);
}
function bandDsTable(id){
	var tab=$(id);//TABLE到手
	for(var i=0;i<tab.rows.length;i++){//按行来循环,不存在兼容问题
		if(i%2==0){//这个不用我解释了吧...
			tab.rows[i].className="alt";//间隔行的样式
			tab.rows[i].claName="alt";//另外用个属性把这个样式名保存
		}
		
		tab.rows[i].onmouseover=function(){//添加鼠标事件
			this.className="over";//变成高亮样式
		}
		tab.rows[i].onmouseout=function(){//鼠标移走咯....
			this.className=this.claName;//把保存的属性还原
		}
		
	}
}