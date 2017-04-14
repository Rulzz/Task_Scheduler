package Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import DTO.SchedulerInput;
import Domain.Slot;
import Domain.Task;
import Scheduler.SchedulerService;

public class ScheduleInvoker {
	
	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy HH:mm");
	
	public static void main(String[] args) throws ParseException, JsonGenerationException, JsonMappingException, IOException {
		
		SchedulerInput input = getInput();
		
		SchedulerService service = new SchedulerService();
		
		service.createSchedule(input);

	}

	private static SchedulerInput getInput() throws ParseException {
		SchedulerInput input = new SchedulerInput();
		input.setSleepStartTime("22:30");
		input.setSleepEndTime("8:15");
		input.setTravelTime("0:30");
		input.setStartDate(dateFormat.parse("20-03-2017 00:00"));
		input.setEndDate(dateFormat.parse("26-03-2017 23:59"));
		
		// lectures
		Slot lecture1 = new Slot();
		lecture1.setName("DS Algo");
		lecture1.setStartTime(dateFormat.parse("24-03-2017 15:20"));
		lecture1.setEndTime(dateFormat.parse("24-03-2017 16:40"));
		
		Slot lecture2 = new Slot();
		lecture2.setName("Intro to AI");
		lecture2.setStartTime(dateFormat.parse("22-03-2017 18:40"));
		lecture2.setEndTime(dateFormat.parse("22-03-2017 21:30"));
		
		ArrayList<Slot> lectures = new ArrayList<>();
		lectures.add(lecture1);
		lectures.add(lecture2);
		input.setLectures(lectures);
		
		//events
		
		Task lec1Task = new Task();
		lec1Task.setName("DS Algo");
		lec1Task.setTimeToComplete("6");
		lec1Task.setType(Task.TaskName.Exam);
		lec1Task.setTargetTime(dateFormat.parse("24-03-2017 15:20"));
		lec1Task.setStartTime(dateFormat.parse("20-03-2017 00:00"));
		
		Task lec2Extra = new Task();
		lec2Extra.setName("Intro to AI");
		lec2Extra.setTimeToComplete("3:30");
		lec2Extra.setType(Task.TaskName.ExtraEffort);
		lec2Extra.setTargetTime(dateFormat.parse("26-03-2017 23:59"));
		lec2Extra.setStartTime(dateFormat.parse("20-03-2017 00:00"));
		
		Task hobby = new Task();
		hobby.setName("Reading");
		hobby.setTimeToComplete("3");
		hobby.setType(Task.TaskName.General);
		hobby.setTargetTime(dateFormat.parse("26-03-2017 23:59"));
		hobby.setStartTime(dateFormat.parse("20-03-2017 00:00"));
		
		Task test = new Task();
		test.setName("test");
		test.setTimeToComplete("140");
		test.setType(Task.TaskName.General);
		test.setTargetTime(dateFormat.parse("26-03-2017 23:59"));
		test.setStartTime(dateFormat.parse("20-03-2017 00:00"));
		
		Task test2 = new Task();
		test2.setName("test2");
		test2.setTimeToComplete("11");
		test2.setType(Task.TaskName.General);
		test2.setTargetTime(dateFormat.parse("25-03-2017 23:30"));
		test2.setStartTime(dateFormat.parse("25-03-2017 12:30"));
		
		Task test3 = new Task();
		test3.setName("test3");
		test3.setTimeToComplete("2");
		test3.setType(Task.TaskName.General);
		test3.setStartTime(dateFormat.parse("25-03-2017 11:30"));
		test3.setTargetTime(dateFormat.parse("25-03-2017 12:30"));
		
		
		ArrayList<Task> tasks = new ArrayList<>();
		tasks.add(lec1Task);
		tasks.add(lec2Extra);
		tasks.add(hobby);
		
		tasks.add(test);
		tasks.add(test2);
		tasks.add(test3);
		
		input.setTasks(tasks);
		
		return input;
		
	}
	
	

}
