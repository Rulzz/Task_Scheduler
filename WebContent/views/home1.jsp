<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>

</style>

<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js">
</script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="resources/css/form.css">

<script type="text/javascript">

$(function(){
	$( "#datePick" ).datepicker();
	//Pass the user selected date format
	
	$( "#format" ).change(function() {
		
	$( "#datePick" ).datepicker( "option", "dateFormat", $(this).val() );
	
	});
	});
	
$(function(){
	$( "#dateother" ).datepicker();
	//Pass the user selected date format
	
	$( "#format" ).change(function() {
		
	$( "#dateother" ).datepicker( "option", "dateFormat", $(this).val() );
	
	});
	});
	
	
$(function(){
	$( "#datestart" ).datepicker();
	//Pass the user selected date format
	
	$( "#format" ).change(function() {
		
	$( "#datestart" ).datepicker( "option", "dateFormat", $(this).val() );
	
	});
	});
	
	
	
$(function(){
	$( "#dateend" ).datepicker();
	//Pass the user selected date format
	
	$( "#format" ).change(function() {
		
	$( "#dateend" ).datepicker( "option", "dateFormat", $(this).val() );
	
	});
	});
x=[];
function add(ty)
	{
	if(ty=='ini')
		{}
	else
	x.push(document.getElementById("lecture").value);
	y="";
	for(i=0;i<x.length;i++)
		{
		y+="<option value='1'>"+x[i]+"</option>"
		}
	document.getElementById("list").innerHTML=y;
	document.getElementById("extralist").innerHTML=y;
	
	document.getElementById("lec").setAttribute("style","display:");
	document.getElementById("extra").setAttribute("style","display:");
	document.getElementById("list").setAttribute("style","display:");
	document.getElementById("extralist").setAttribute("style","display:");
	document.getElementById("lecture").value="";
	}

function change(typ)
	{
	
	var r = document.getElementById("list");
	//alert("in  "+r.selectedIndex);
	ty=r.selectedIndex;
	alert(typ);
	if(typ=='lec')
		{
		dates=[];
	if((typeof lec[ty]!='undefined') && lec[ty]!='?')
		{
		tag="";
		tag+="<h4>Events</h4>";
		alert("1111");
		for(i=0;i<event_details.length;i++)
		{
			alert("aaa"+event_details[i].lecture+"...."+x[ty]);
			if(event_details[i].lecture==x[ty])
				{
					tag+=event_details[i].events +" on "+event_details[i].dates+"<br><br>";
					dates.push(event_details[i].dates);
					alert("???....."+dates);
				}
		}
	document.getElementById("added_events").innerHTML=tag;
		
		//alert(lec[ty].split("|")[4]);
	document.getElementById("start_time").value=lec[ty].split("|")[0];
	document.getElementById("end_time").value=lec[ty].split("|")[1];
	document.getElementById("event").selectedIndex =lec[ty].split("|")[2];
	z=lec[ty].split("|")[4];
	y="";
	eventindex=0;
	 for(i=0;i<z.split(",").length;i++) 
		 {
			y+="<option>"+z.split(",")[i]+"</option>";
			if(z.split(",")[i]==lec[ty].split("|")[5])
				eventindex=i;
			}
		document.getElementById("eventdate" ).innerHTML=y;
		
	document.getElementById("eventdate").selectedIndex =eventindex;
	document.getElementById("dates").value =lec[ty].split("|")[4];
	document.getElementById("hr_study").value=lec[ty].split("|")[3];
		}
	else
		{
		document.getElementById("start_time").value="";
		document.getElementById("end_time").value="";
		document.getElementById("event").selectedIndex=0;
		document.getElementById("eventdate").innerHTML="";
		document.getElementById("dates").value="";
		document.getElementById("hr_study").value="";
		}
		}
	else
		{
	//	alert(document.getElementById("extralist").selectedIndex);
	if(typeof extra[document.getElementById("extralist").selectedIndex]!='undefined')
		{
		document.getElementById("extratime").value=extra[document.getElementById("extralist").selectedIndex];
		}
	else
		{
		document.getElementById("extratime").value="";
		}
		}
	}
lec=[];
extra=[];
function save(ty)
	{
	alert(ty);
	if(ty=='lec')
		{
		
	lecture=document.getElementById("list").selectedIndex;
	var start_time=document.getElementById("start_time").value;
	var end_time=document.getElementById("end_time").value;
	var event=document.getElementById("event").selectedIndex;
	var study_time=document.getElementById("hr_study").value;
	var e = document.getElementById("eventdate");
	
	var eventdate=e.options[e.selectedIndex].value;
	if(start_time==''||end_time==''||event==''||study_time=='')
		{alert("Please fill all the feilds");}
	else if(start_time==end_time)
	{
			alert("start and end time cannot be same");
	}
	
	else
			{
	details=start_time+"|"+end_time+"|"+event+"|"+study_time+"|"+dates+"|"+eventdate;
	alert(lecture);
	if(typeof lec[lecture]=='undefined')
		{
		alert(lec.length);
		if(parseInt(lecture)==lec.length)
		lec.push(details);
		else
			{
			alert("in")
			for(i=0;i<parseInt(lecture);i++)
				{
				lec.push("?");
				}
		lec.push(details);
			}
		}
			else 
				{
				lec[lecture]= details;
				}
			}
		}
	else
		{
		alert("in....");
		if(typeof extra[document.getElementById("extralist").selectedIndex]=='undefined')
		extra.push(document.getElementById("extratime").value);
		else 
		{
		
		extra[document.getElementById("extralist").selectedIndex]= document.getElementById("extratime").value;
		}
		}
	alert(lec);
	}

function remove()
	{
	y="";
	var r = document.getElementById("list");
	var e= document.getElementById("extralist");
	document.getElementById("extralist").selectedIndex=r.selectedIndex;
	//alert(r.selectedIndex);
	x.splice(r.selectedIndex, 1);
	lec.splice(r.selectedIndex, 1);
	extra.splice(r.selectedIndex, 1);
	alert(lec+" ??? "+extra);
	//alert(x.indexOf(r.selectedIndex));
    r.remove(r.selectedIndex);
    e.remove(e.selectedIndex);
	//alert("rem"+x.length);

	if(x.length==0)
		{
		alert("in");
		document.getElementById("lec").setAttribute("style","display:none");
	document.getElementById("extra").setAttribute("style","display:none");
		}
	//document.getElementById("extra").setAttribute("style","display:");
	}
	
	
	function submit()
	{
		alert("sub");
		localStorage.setItem("data","data");
		name=document.getElementById("name").value;
		
		title=document.getElementById("title").value;
		lec_count=x.length;
		 sl_start=document.getElementById("slstart_time").value;
		sl_end=document.getElementById("slend_time").value;
		other=document.getElementById("other").value;
		other_time=document.getElementById("other_time").value;  
		trstart_time=document.getElementById("trstart_time").value;
		other_end=document.getElementById("dateother").value;
	//	trend_time=document.getElementById("trend_time").value;
		var dateObj = new Date();
var month = dateObj.getUTCMonth() + 1; //months from 1-12
var day = dateObj.getUTCDate();
var year = dateObj.getUTCFullYear();
sub="";
task="";
for(m=0;m<lec.length;m++)
	{
	
z=lec[m].split("|")[4];
		for(n=0;n<z.split(",").length;n++)
			{
			alert(x[m]+"....."+event_details[n].lecture);
			var e = document.getElementById("event");
			var event = e.options[parseInt(lec[m].split("|")[2])].value;
			
		    var dateObjstart = new Date(z.split(",")[n] + ' ' + lec[m].split("|")[0]);
		    var dateObjend = new Date(z.split(",")[n] + ' ' + lec[m].split("|")[0]);
		    
			sub+="{'name': '"+x[m]+"','startTime':'"+ dateObjstart.getTime()+"','endTime': '"+dateObjend.getTime()+"'},";
			/* if(z.split(",")[n]==lec[m].split("|")[5])
			task+="{'name': '"+x[m]+"','type':'"+event+"','targetTime':'"+ lec[m].split("|")[5]+"','timeToComplete':'"+lec[m].split("|")[3]+"'},";
			else
				task+="{'name': "+x[m]+",'type':'Regular','targetTime':'"+ lec[m].split("|")[5]+"','timeToComplete':'"+lec[m].split("|")[3]+"'},";
			 */			 
			}
		alert(JSON.stringify(event_details));
		for(n=0;n<event_details.length;n++)
			{
			eventend= new Date(event_details[n].dates + ' ' + lec[m].split("|")[0]); 
			if(x[m]==event_details[n].lecture)
				task+="{'name': '"+x[m]+"','type':'"+event_details[n].events+"','startTime':'"+event_details[n].start+"','targetTime':'"+ eventend.getTime()+"','timeToComplete':'"+event_details[n].time+"'},";
			}
/* lect="name": "DS Algo","startTime": 1490383200000,"endTime": 1490388000000 */
	}
	for(l=0;l<extra.length;l++)
		{
		task+="{'name': '"+x[l]+"','type':'ExtraEffort','targetTime':'"+ lec[l].split("|")[5]+"','timeToComplete':'"+extra[l]+"'},";
		}
	task+="{'name': '"+other+"','type':'General','targetTime':'"+ other_end+"','timeToComplete':'"+other_time+"'},";
startdate = new Date(document.getElementById("datestart").value+' '+"00:00");
enddate = new Date(document.getElementById("dateend").value+' '+"23:59");
		 pass="{'sleepStartTime': "+sl_start+",'sleepEndTime':"+sl_end+",'travelTime':"+trstart_time+
			",'startDate':"+ startdate.getTime()+",'endDate':"+ enddate.getTime()+",'lectures': [" +sub+"],'tasks':"+task+"]}"; 
			
			alert(pass);
			misc=[];
			misc.push({
				
				sleepStartTime: sl_start,
				sleepEndTime: sl_end,
				travelTime: trstart_time,
				othern: other,
				date: other_end,
				time: other_time,
				name: name,
				title: title
		    });
			localStorage.setItem("events",JSON.stringify(event_details));
			localStorage.setItem("misc",JSON.stringify(misc));
			localStorage.setItem("lec",JSON.stringify(lec));
			localStorage.setItem("x",JSON.stringify(x));
			localStorage.setItem("extra",JSON.stringify(extra));
			
		 	url="http://192.168.1.5:8080/Task_Scheduler/rest/CallenderResource/schedule";
			
			$.ajax({
				
				url: url,
				type: 'POST',
				data: pass,
				contentType: "application/json; charsef=utf-8",
				datatype: "json",
				
				success: function(result)
				{
					alert("in");
					localStorage.setItem("data",JSON.stringify(result));
					document.getElementById("sch").submit();
					
				},
				error: function(XMLHttpRequest, textStatus, errorThrown)
				{
					alert("error");
				},
				
			}); 
		
		//	
			
		
	}
		
	dates=[];
	function calend()
	{
		subject_id=document.getElementById("list");
		subject=subject_id.options[subject_id.selectedIndex].innerHTML;
		
		tempdate=document.getElementById("datePick" ).value;
		index=dates.indexOf(tempdate);
		alert(dates);
		if(index==(-1))
			{
		dates.push(document.getElementById("datePick" ).value);
		
		event_details.push({
	        lecture: subject,
	        events: 'Regular',
	        dates: document.getElementById("datePick" ).value,
	        start: '00:00',
	        time: '00:00'
	    });
		
		tag="";
		tag+="<h4>Events</h4>";
		for(i=0;i<event_details.length;i++)
		{
			if(event_details[i].lecture==subject)
				{
					tag+=event_details[i].events +" on "+event_details[i].dates+"<br><br>";
				}
		}
		document.getElementById("added_events").innerHTML=tag;
		
		document.getElementById("dates" ).value=dates;
		y="<optiondisabled selected>Date</option>";
		for(i=0;i<dates.length;i++)
			{
		y+="<option>"+dates[i]+"</option>"
			}
		document.getElementById("eventdate" ).innerHTML=y;
			}
		else
			{
			dates.splice(index, 1);
			document.getElementById("dates" ).value=dates;
			y="<optiondisabled selected>Date</option>";
			for(i=0;i<dates.length;i++)
				{
			y+="<option>"+dates[i]+"</option>"
				}
			document.getElementById("eventdate" ).innerHTML=y;
			
			
			del=0;
			for(i=0;i<event_details.length;i++)
			{
				if(event_details[i].lecture==subject&&event_details[i].dates==tempdate)
					{
						del=i;
					}
			}
			
			event_details.splice(del, 1);
			tag="";
			tag+="<h4>Events</h4>";
			for(i=0;i<event_details.length;i++)
			{
				if(event_details[i].lecture==subject)
					{
						tag+=event_details[i].events +" on "+event_details[i].dates+"<br><br>";
					}
			}
			document.getElementById("added_events").innerHTML=tag;
			}
	}
	
	event_details=[];
function addevent()
	{
	tag="";
	event_id=document.getElementById("event");
	date_id=document.getElementById("eventdate");
	
	event=event_id.options[event_id.selectedIndex].value;
	
	date=date_id.options[date_id.selectedIndex].value;
	subject_id=document.getElementById("list");
	subject=subject_id.options[subject_id.selectedIndex].innerHTML;
	
	counter=0;
	
	
	for(i=0;i<event_details.length;i++)
	{
		if(event_details[i].lecture==subject&&event_details[i].dates==date)
			{
			event_details[i].events=event;
			event_details[i].time=document.getElementById("hr_study").value;
			
			j=(document.getElementById("myLocalDate").value).replace("T", "' '");
			alert(j);
			j=new Date(j)
			alert(j.getTime());
			event_details[i].start=document.getElementById("myLocalDate").value;
			counter++
			}		
	}
	
	if(counter==0)
	{
	event_details.push({
        lecture: subject,
        events: event,
        dates: date,
        start: document.getElementById("myLocalDate").value, 
        time: document.getElementById("hr_study").value
    });
	}
	alert(event+"    "+date+"   "+event_details.length);
	tag+="<h4>Events</h4>";
	for(i=0;i<event_details.length;i++)
		{
			if(event_details[i].lecture==subject)
				{
					tag+=event_details[i].events +" on "+event_details[i].dates+"<br><br>";
				}
		}
	document.getElementById("added_events").innerHTML=tag;
	}
misc=[];

function load()
	{
	//localStorage.clear();
	misc=(JSON.parse(localStorage.getItem("misc")));
	l_events=JSON.parse(localStorage.getItem("events"));
	l_lec=JSON.parse(localStorage.getItem("lec"));
	l_x=JSON.parse(localStorage.getItem("x"));
	l_extra=JSON.parse(localStorage.getItem("extra"));
	alert(localStorage.getItem("extra"));
	if(l_x.length!=0)
		{
		x=l_x;
		add('ini');
		}
	if(l_lec.length!=0)
		{
		lec=l_lec;
		if(l_events.length!=0)
			{
			event_details=l_events;
			change('lec');			
			}
		
		}
	if(l_extra.length!=0)
		{
		extra=l_extra;
		alert(extra);
		change('extra');
		}
	if(misc.length!=0)
		{
		document.getElementById("name").value=misc[0].name;
		document.getElementById("title").value=misc[0].title;
		document.getElementById("slstart_time").value=misc[0].sleepStartTime;
		document.getElementById("slend_time").value=misc[0].sleepEndTime;
		document.getElementById("other").value=misc[0].othern;
		document.getElementById("other_time").value=misc[0].time;  
		document.getElementById("trstart_time").value=misc[0].travelTime;
		document.getElementById("dateother").value=misc[0].date;
		}
	}

</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scheduler</title>
</head>
<body onload="load()">
	
	
	<div class="form-style-5">
<form id='sch' action="table" method="post">
<fieldset>
<legend><span class="number">1</span>Info</legend>
<input type="text" id='name' name="field1" placeholder="Your Name *" required>
<input type="text" id='title' name="field2" placeholder="Title *">


<input id="datestart" type="text"  placeholder="Calender Start Date">


<input id="dateend" type="text" placeholder="Calender End Date" >


<div style="display: inline-flex;">
<input id='lecture' type="text" name="field2" placeholder="Lecture Name" style="width: 410px;">
<input onclick="add()" type="button" value="Add" style="width: 10px; height: 10px; padding: 6px 35px 22px 5px; margin-left: 20px;" />
</div>

<!-- <textarea name="field3" placeholder="About yourself"></textarea> -->
<div style="display: none;" id='lec'>
<label style="display: none;" >Lectures</label>
<div style="display: inline-flex;">
<select onchange="change('lec')" style="display: none;" id='list' style="width: 410px;">

</select>
<input onclick="remove()" type="button" value="Remove" style="width: 10px; height: 10px; padding: 8px 71px 22px 8px; margin-left: 20px;" />

</div>
<div class="clearfix"></div>

<div style="display: inline-flex;">
<input style="width: 450px;" id="dates" type="text"/ placeholder="Selected Dates">
<input style="background: url(resources/image/date.png) no-repeat right center; background-size: 45px; width:40px; color: transparent; cursor:pointer" 
onchange="calend()" id="datePick" type="text"/ >
</div>
<!-- <select id='days' style="width: 150px;">
<option value="" disabled selected>Days</option>
<option value="Exam">Monday</option>
<option value="HW">Tuesday</option>
<option value="Regular">Wednesday</option>
<option value="Regular">Thursday</option>
<option value="Regular">Friday</option>
</select> -->
<div style="display: inline-flex;">
<a style="display: inline-table;    padding-top: 10px;">Start time:</a>
  <input type="time" name="start_time" id='start_time'>
<a style="display: inline-table;  margin-left:30px;   padding-top: 10px;">End time:</a>
  <input type="time" name="end_time" id='end_time'>
  </div>

<h3>Tasks</h3>
<div style="display: inline-flex;"> 
<select id='event' style="width: 150px;">
<option value="" disabled selected>Event</option>
<option value="Exam">Exam</option>
<option value="HW">HW</option>
<option value="Regular">Regular</option>
</select>
<select id='eventdate' style="width: 150px; margin-left: 5px;">

</select>
</div>

<input style="font-family: Georgia, 'Times New Roman', Times, serif;
    background: rgba(255,255,255,.1);
    border: none;
    border-radius: 4px;
    font-size: 16px;
    margin: 0;
    outline: 0;
    padding: 7px;
    width: 100%;
    box-sizing: border-box; 
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box; 
    background-color: #e8eeef;
    color:#8a97a0;
    -webkit-box-shadow: 0 1px 0 rgba(0,0,0,0.03) inset;
    box-shadow: 0 1px 0 rgba(0,0,0,0.03) inset;
    margin-bottom: 30px;"type="datetime-local" id="myLocalDate" value="yyyy-mm-ddThh:mm">

<div style="display: inline-flex;">
 <a style="display: inline-table;    padding-top: 10px;">Hours of Study</a>
<input id='hr_study' style="width: 150px;" type="time" placeholder="Hours of Study">
<input onclick="addevent()" type="button" value="Change" style="width: 10px; height: 10px; padding: 6px 35px 22px 5px; margin-left: 20px;" />
 </div>
 
 <div id="added_events">
 
 </div>
 
 <div style="display: inline-flex;">
<input onclick="save('lec')" type="button" value="Save" style="width: 10px; height: 10px; padding: 8px 71px 22px 8px; margin-left: 20px;" />
</div>

</div>
  
</fieldset>
<fieldset>
<legend><span class="number">2</span> Additional Info</legend>
<h3>Sleep</h3>
<div style="display: inline-flex;">

<a style="display: inline-table;    padding-top: 10px;">Start time:</a>
  <input type="time" id="slstart_time">
<a style="display: inline-table;  margin-left:30px;   padding-top: 10px;">End time:</a>
  <input type="time" id="slend_time">
  </div>
  
  <h3>Travel Time</h3>
<div style="display: inline-flex;">

<a style="display: inline-table;    padding-top: 10px;">Time:</a>
  <input type="time" id="trstart_time">
<!-- <a style="display: inline-table;  margin-left:30px;   padding-top: 10px;">End time:</a>
  <input type="time" id="trend_time"> -->
  </div>
  
  
  <div style="display: none;">
  <div style="display: none;" id='extra'>
  <h3>Extra Effort</h3>
  <select onchange='change(extra)'style="display: none;" id='extralist' style="width: 410px;">

</select>
<a style="display: inline-table; padding-top: 10px;">Hours Of Study:</a>
<div style="display: inline-flex;">
  <input type="time" id="extratime">
  <input onclick="save(extra)" type="button" value="Save" style="width: 10px; height: 10px; padding: 8px 71px 22px 8px; margin-left: 20px;" />
</div>
</div>

<div>
  <h3>Other Task</h3>
  <input type="text" id="other">
  <input  id="dateother" type="text"/ placeholder="Date">
<a style="display: inline-table; padding-top: 10px;">Hours</a>
  <input type="time" id="other_time">
</div>
</div>
<!-- <textarea name="field3" placeholder="About Your School"></textarea> -->
</fieldset>

</form>
<input onclick="submit()" type="button" value="Apply" />
</div>
	
		
</body>
</html>