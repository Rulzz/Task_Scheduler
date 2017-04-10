<!doctype html>
<html lang="en" class="no-js">
<script>
	if( !window.jQuery ) document.write('<script src="resources/js/jquery-3.0.0.min.js"><\/script>');
</script>
<script src="resources/js/main.js"></script>
<script type="text/javascript">
function start()
	{
	
	
	/* result='[{"schedule": {'+
		     '"23-3-2017": [{"name": "Sleep","startTime": "06:00","endTime": "08:30"}],'+
		     '"22-3-2017": [{"name": "Sleep","startTime": "00:00","endTime": "08:15"}],'+
		     '"24-3-2017": [{"name": "Sleep","startTime": "00:00","endTime": "08:15"}],'+
		     '"21-3-2017": [{"name": "Sleep","startTime": "00:00","endTime": "08:15"}],'+
		     '"25-3-2017": [{"name": "Sleep","startTime": "00:00","endTime": "08:15"}],'+
		     '"26-3-2017": [{"name": "Sleep","startTime": "00:00","endTime": "08:15"}],'+
		     '"20-3-2017": [{"name": "Sleep","startTime": "00:00","endTime": "08:15"}]},'+
		    '"messages": ["Could not allocate 86 Hrs and 55 Min for task : Reading",'+
		    '"Could not allocate 1 hr(s) to Dinner on day : 22-3-2017"]}]'; */
	result=JSON.parse(localStorage.setItem("data"));
	p=result[0].schedule;
	//alert(result[0].schedule[0]);
	x="";
	for (var key in p) {
  if (p.hasOwnProperty(key)) {
	  x+="<li class='events-group'><div class='top-info'><span>"+key+"</span></div>"+
	    "<ul>";
	  for(i=0;i<p[key].length;i++)
  	{
  	alert(key+"name"+p[key][i].name+"startTime"+p[key][i].startTime+"endTime"+p[key][i].endTime);
  	 x+="<li class='single-event' data-start='"+p[key][i].startTime+"' data-end='"+p[key][i].endTime+"'"+ 
     "data-content='event-abs-circuit' data-event='event-1'>"+
	   "<a href='#0'><em class='event-name'>"+p[key][i].name+"</em></a></li>"; 
  	}
	  x+="</ul></li>"; 
  }
}
	
	
	
	
	alert(x);
	alert(document.getElementById("test").style.top);
	document.getElementById("data").innerHTML=x;
	
	mainLoader();
	}
</script>

<script src="resources/js/modernizr.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>


<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600" rel="stylesheet">
	<link rel="stylesheet" href="resources/css/reset.css"> <!-- CSS reset -->
	<link rel="stylesheet" href="resources/css/style.css"> <!-- Resource style -->
  	
	<title>Schedule Template | CodyHouse</title>
</head>
<body onload="start()">
<div class="cd-schedule loading">
	<div class="timeline" id="test">
		<ul>
			<li ><span >00:00</span></li>
			<li><span>00:30</span></li>
			<li><span>01:00</span></li>
			<li><span>01:30</span></li>
			<li><span>02:00</span></li>
			<li><span>02:30</span></li>
			<li><span>03:00</span></li>
			<li><span>03:30</span></li>
			<li><span>04:00</span></li>
			<li><span>04:30</span></li>
			<li><span>05:00</span></li>
			<li><span>05:30</span></li>
			<li><span>06:00</span></li>
			<li><span>06:30</span></li>
			<li><span>07:00</span></li>
			<li><span>07:30</span></li>
			<li><span>08:00</span></li>
			<li><span>08:30</span></li>
			<li><span>09:00</span></li>
			<li><span>09:30</span></li>
			<li><span>10:00</span></li>
			<li><span>10:30</span></li>
			<li><span>11:00</span></li>
			<li><span>11:30</span></li>
			<li><span>12:00</span></li>
			<li><span>12:30</span></li>
			<li><span>13:00</span></li>
			<li><span>13:30</span></li>
			<li><span>14:00</span></li>
			<li><span>14:30</span></li>
			<li><span>15:00</span></li>
			<li><span>15:30</span></li>
			<li><span>16:00</span></li>
			<li><span>16:30</span></li>
			<li><span>17:00</span></li>
			<li><span>17:30</span></li>
			<li><span>18:00</span></li>
			<li><span>18:30</span></li>
			<li><span>19:00</span></li>
			<li><span>19:30</span></li>
			<li><span>20:00</span></li>
			<li><span>20:30</span></li>
			<li><span>21:00</span></li>
			<li><span>21:30</span></li>
			<li><span>22:00</span></li>
			<li><span>22:30</span></li>
			<li><span>23:00</span></li>
			<li><span>23:30</span></li>
		</ul>
	</div> <!-- .timeline -->

	<div class="events">
		<ul id="data">
		
			  <li class="events-group">
				<div class="top-info"><span>Sunday</span></div>

				<ul>
					<li class="single-event" data-start="09:30" data-end="10:30" data-content="event-abs-circuit" data-event="event-1">
						<a href="#0">
							<em class="event-name">TASK1</em>
						</a>
					</li>

					<li class="single-event" data-start="11:00" data-end="12:30" data-content="event-rowing-workout" data-event="event-2">
						<a href="#0">
							<em class="event-name">TASK2</em>
						</a>
					</li>

					<li class="single-event" data-start="14:00" data-end="15:15"  data-content="event-yoga-1" data-event="event-3">
						<a href="#0">
							<em class="event-name">TASK3</em>
						</a>
					</li>
				</ul>
			</li> 
			
			<li class="events-group">
				<div class="top-info"><span>Monday</span></div>

				<ul>
					<li class="single-event" data-start="00:00" data-end="08:30" data-content="event-abs-circuit" data-event="event-1">
						<a href="#0">
							<em class="event-name">TASK1</em>
						</a>
					</li>

					
				</ul>
			</li>

			<li class="events-group">
				<div class="top-info"><span>Tuesday</span></div>

				<ul>
					<li class="single-event" data-start="10:00" data-end="11:00"  data-content="event-rowing-workout" data-event="event-2">
						<a href="#0">
							<em class="event-name">TASK2</em>
						</a>
					</li>

					<li class="single-event" data-start="11:30" data-end="13:00"  data-content="event-restorative-yoga" data-event="event-4">
						<a href="#0">
							<em class="event-name">TASK4</em>
						</a>
					</li>

					<li class="single-event" data-start="13:30" data-end="15:00" data-content="event-abs-circuit" data-event="event-1">
						<a href="#0">
							<em class="event-name">TASK1</em>
						</a>
					</li>

					<li class="single-event" data-start="15:45" data-end="16:45"  data-content="event-yoga-1" data-event="event-3">
						<a href="#0">
							<em class="event-name">TASK3</em>
						</a>
					</li>
				</ul>
			</li>

			<li class="events-group">
				<div class="top-info"><span>Wednesday</span></div>

				<ul>
					<li class="single-event" data-start="09:00" data-end="10:15" data-content="event-restorative-yoga" data-event="event-4">
						<a href="#0">
							<em class="event-name">TASK4</em>
						</a>
					</li>

					<li class="single-event" data-start="10:45" data-end="11:45" data-content="event-yoga-1" data-event="event-3">
						<a href="#0">
							<em class="event-name">TASK3</em>
						</a>
					</li>

					<li class="single-event" data-start="12:00" data-end="13:45"  data-content="event-rowing-workout" data-event="event-2">
						<a href="#0">
							<em class="event-name">TASK2</em>
						</a>
					</li>

					<li class="single-event" data-start="13:45" data-end="15:00" data-content="event-yoga-1" data-event="event-3">
						<a href="#0">
							<em class="event-name">TASK3</em>
						</a>
					</li>
				</ul>
			</li>

			<li class="events-group">
				<div class="top-info"><span>Thursday</span></div>

				<ul>
					<li class="single-event" data-start="09:30" data-end="10:30" data-content="event-abs-circuit" data-event="event-1">
						<a href="#0">
							<em class="event-name">TASK1</em>
						</a>
					</li>

					<li class="single-event" data-start="12:00" data-end="13:45" data-content="event-restorative-yoga" data-event="event-4">
						<a href="#0">
							<em class="event-name">TASK4</em>
						</a>
					</li>

					<li class="single-event" data-start="15:30" data-end="16:30" data-content="event-abs-circuit" data-event="event-1">
						<a href="#0">
							<em class="event-name">TASK1</em>
						</a>
					</li>

					<li class="single-event" data-start="17:00" data-end="18:30"  data-content="event-rowing-workout" data-event="event-2">
						<a href="#0">
							<em class="event-name">TASK2</em>
						</a>
					</li>
				</ul>
			</li>

			<li class="events-group">
				<div class="top-info"><span>Friday</span></div>

				<ul>
					<li class="single-event" data-start="10:00" data-end="11:00"  data-content="event-rowing-workout" data-event="event-2">
						<a href="#0">
							<em class="event-name">TASK2</em>
						</a>
					</li>

					<li class="single-event" data-start="12:30" data-end="14:00" data-content="event-abs-circuit" data-event="event-1">
						<a href="#0">
							<em class="event-name">TASK1</em>
						</a>
					</li>

					<li class="single-event" data-start="15:45" data-end="16:45"  data-content="event-yoga-1" data-event="event-3">
						<a href="#0">
							<em class="event-name">TASK3</em>
						</a>
					</li>
				</ul>
			</li>
			
			<li class="events-group">
				<div class="top-info"><span>Saturday</span></div>

				<ul>
					<li class="single-event" data-start="09:30" data-end="10:30" data-content="event-abs-circuit" data-event="event-1">
						<a href="#0">
							<em class="event-name">TASK1</em>
						</a>
					</li>

					<li class="single-event" data-start="11:00" data-end="12:30" data-content="event-rowing-workout" data-event="event-2">
						<a href="#0">
							<em class="event-name">TASK2</em>
						</a>
					</li>

					<li class="single-event" data-start="14:00" data-end="15:15"  data-content="event-yoga-1" data-event="event-3">
						<a href="#0">
							<em class="event-name">TASK3</em>
						</a>
					</li>
				</ul>
			</li>
			
		</ul>
	</div>

	<div class="event-modal">
		<header class="header">
			<div class="content">
				<span class="event-date"></span>
				<h3 class="event-name"></h3>
			</div>

			<div class="header-bg"></div>
		</header>

		<div class="body">
			<div class="event-info"></div>
			<div class="body-bg"></div>
		</div>

		<a href="#0" class="close">Close</a>
	</div>

	<div class="cover-layer"></div>
</div> <!-- .cd-schedule -->
 <!-- Resource jQuery -->
</body>
</html>