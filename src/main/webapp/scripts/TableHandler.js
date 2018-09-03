
function addToTable(tablename,func,format,args,hover,lev){
  if(tablename){
	  var t = document.getElementById(tablename);
	  var tr = document.createElement("tr");
	  var last;
	  for(var i = 0; i < format.length;i++){
		if(args[format[i]]!= undefined && args[format[i]]!= null){
				var td= document.createElement("td");
		    	var tx = document.createTextNode(args[format[i]]);
		    	td.appendChild(tx);
		    	tr.appendChild(td);
		}
  	}
	  if(last!=null){
		  var td= document.createElement("td");
		  var tx = document.createTextNode(args[last]);
		  td.appendChild(tx);
		  tr.appendChild(td);
	  }
	  tr.name = "unselected";
	  tr.onclick = func;
	  tr.onmouseenter = hover;
	  tr.onmouseleave = lev;
	  tr.classList.add("unselected");
	  tr.setAttribute("info",JSON.stringify(args));
	  t.appendChild(tr);
	  var allPlayers = JSON.parse(t.getAttribute("players"));
	  if(allPlayers == null){
		  allPlayers = {};
	  }
	  if(args.playerId != null){
		  allPlayers[args.playerId] = args;
	  }
	  t.setAttribute("players",JSON.stringify(allPlayers));
  }
};



function sel(x){
	    if(x.getAttribute("selected") === "true"){
	      x.setAttribute("selected","false");
	      x.setAttribute("recentlyselected","true");
	      x.setAttribute("hover","true");
	      x.classList.remove("selected");
	      x.classList.add("unselected");
	      x.classList.add("hover");
	    }
	    else{
	      x.setAttribute("selected","true");
	      x.classList.add("selected");
	      x.classList.remove("unselected")
	      //x.style = "background-color:#dc9bff";
	    }
	
};
function hov(x){
    if(x.getAttribute("hover") === "true"){
      x.setAttribute("hover","false");
      x.classList.remove("hover");
      x.classList.add("nothover");
    }
    else{
    	if(x.getAttribute("selected")!= "true" && x.getAttribute("recentlyselected")!="true"){
	      x.setAttribute("hover","true");
	      x.classList.add("hover");
	      x.classList.remove("nothover");	
    	}
    }

};
function leave(x){
	x.setAttribute("recentlyselected","false");
}
function getSelectedFromTable(tablename){
  var ret = [];
  var t = document.getElementById(tablename);
  var tr = t.rows;
  for(i = 1; i < tr.length;i++){
    if(tr[i].getAttribute("selected") === "true" && tr[i].parentElement.id === tablename){
      ret.push(tr[i]);
    }
  }
  return ret;
};

function AjaxGet(url, func){
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function (){
    if(this.readyState==4 && this.status == 200){
      func(this);
    }
  }
  xhr.open("GET",url,true);
  xhr.send();
};

function AjaxPost(url,body, func){
	  var xhr = new XMLHttpRequest();
	  xhr.onreadystatechange = function (){
	    if(this.readyState==4 && this.status == 200){
	      func(this);
	    }
	  }
	  xhr.open("POST",url,true);
	  xhr.send(body);
	};



function cycleActivePlayer(){
	var t = document.getElementById("PlayerTable");
	if(t.rows.length === 0){
		return null;
	}
	var act = t.getAttribute("activeplayer");
	if(act === null &&t.rows.length > 0){
		var r0info = JSON.parse(t.rows[0].getAttribute("info"));
		var actinfo = {"row":0,"playerId":r0info.playerId,"moves":5};
		t.setAttribute("activeplayer", JSON.stringify(actinfo));
		return true;
	}
	var actinfo = JSON.parse(act);
	var rinfo = JSON.parse(t.rows[(actinfo.row + 1) % t.rows.length].getAttribute("info"));
	var newact = {"row":(actinfo.row + 1) % t.rows.length, "playerId":rinfo.playerId,"moves":5};
	t.setAttribute("activeplayer", JSON.stringify(newact));
	t.rows[newact.row].onclick();
	return true;
}

//addToTable("PlayerTable",PlayerOnSelect,["username","color","playerId"], {"username":"landon","color":"red","playerId":"43"},PlayerOnHover,PlayerOnLeave);
//addToTable("PlayerTable",PlayerOnSelect,["username","color","playerId"], {"username":"jimmy","color":"green","playerId":"24"},PlayerOnHover,PlayerOnLeave);

function load(){
	AjaxGet("playerinfo",function(xhr){
		console.log(JSON.parse(xhr.responseText));
		var p = JSON.parse(xhr.responseText);
		p.forEach(function(element){
			element.password = null;
			addToTable("PlayerTable",PlayerOnSelect,["username","color","playerId"], element,PlayerOnHover,PlayerOnLeave);
		});
	});
}

window.onload = load;
