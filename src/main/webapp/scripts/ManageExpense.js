/**
 * 
 */
console.log("loaded");
function PlayerOnSelect(){
	var expid = JSON.parse(this.getAttribute("info"))["expenseId"];
	var url = 'image?expid=' + expid;
	sel(this);
	if(this.getAttribute("selected") === "true"){
		showPlayerImageFromTable(this.parentElement.id,expid,url);
	}else{
		var imm = this.childNodes[5];
		var im;
		if(imm){
			im = imm.childNodes[0];
		}
		if(this.parentElement.id.substring("all")){
			imm = this.childNodes[6];
			if(imm){
				im = imm.childNodes[0];
			}
		}
		if(im){
			if(!(im.getAttribute("hover")=="true")){
				hidePlayerImageFromTable(this.parentElement.id,expid,url);
			}else{
				sel(this);
				im.setAttribute("hover",null);
			}
			
		}else{
			hidePlayerImageFromTable(this.parentElement.id,expid,url);
		}
	}
	
}
function PlayerOnHover(){
	hov(this);
}
function PlayerOnLeave(){
	leave(this);
	hov(this);
}

function showPlayerImageFromTable(tablename,expenseid,url){
	console.log("modifying expense" + expenseid);
	if(tablename && expenseid){
		var t = document.getElementById(tablename);
		var r = t.rows;
		var ret = {};
		for(i = 1; i < r.length; i ++){
			var ch = r[i].childNodes;
			var rowInfo = JSON.parse(r[i].getAttribute("info"));
			if(rowInfo["expenseId"] == expenseid){
				if(ch.length<6 || (tablename.substring("all")&&ch.length<7)){
				var td  = document.createElement("td");
				var imgg = document.createElement("img");
				imgg.src = url;
				imgg.name = "rxpt"
				imgg.style = "width:50px;height:auto";
				function openImage(){
					window.open(url,'_blank');
				};
				function hovering(){
					this.setAttribute("hover","true");
				};
				function nothovering(){
					this.setAttribute("hover",null);
				}
				imgg.onmouseleave = nothovering;
				imgg.onmouseover = hovering;
				imgg.onclick = openImage;
				console.log("image source:"+imgg.src);
				td.appendChild(imgg);
				r[i].appendChild(td);
				break;
				}
			}
		}
	}
}
function hidePlayerImageFromTable(tablename,expenseid){
	if(tablename && expenseid){
		var t = document.getElementById(tablename);
		var r = t.rows;
		for(i = 1; i < r.length;i++){
			var ch = r[i].childNodes;
			var rowInfo = JSON.parse(r[i].getAttribute("info"));
			if(rowInfo["playerId"] == expenseid){
				if(ch.length>5){
					r[i].removeChild(ch[ch.length - 1]);
				}
			}
		}
	}
};

