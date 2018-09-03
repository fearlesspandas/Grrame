/**
 * 
 */

function applyRect(a,b,c,d){
	var x = Math.min(a.x,b.x,c.x,d.x);
	var y = Math.min(a.y,b.y,c.y,d.y);
	var width = Math.max(a.x,b.x,c.x,d.x) - x;
	var height = Math.max(a.y,b.y,c.y,d.y)-y;
	var Gtx = document.getElementById("grameBoard").getContext("2d");
	Gtx.rect(x,y,width,height);
	Gtx.stroke();
}


