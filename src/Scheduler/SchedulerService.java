package Scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.time.DateUtils;

import DTO.SchedulerInput;
import Domain.Callender;
import Domain.Slot;
import Domain.Task;

public class SchedulerService {
	
	SimpleDateFormat dayFormat = new SimpleDateFormat("dd-M-yyyy");
	SimpleDateFormat dayTimeFormat = new SimpleDateFormat("dd-M-yyyy HH:mm");
	
	private HashMap<Long, SortedSet<Slot>> dailyFreeSlots = new HashMap<>();
	private ArrayList<Slot> schedule = new ArrayList<>();
	private ArrayList<String> messages = new ArrayList<>();
	private Date calenderStartDate;
	private Date calenderEndDate;
	
	private int lunchTimeMin = 12;
	private int lunchTimeMax = 3;
	private int dinnerTimeMin = 20;
	private int dinnerTimeMax = 23;
	private int lunchDuration = 1;
	private int dinnerDuration = 1;
	
	private int revisionTime = 1;
	
	ScheduleHelper helper = new ScheduleHelper();
	
	public ArrayList<Callender> createSchedule(SchedulerInput input) throws ParseException {
		
		calenderStartDate = DateUtils.truncate(input.getStartDate(), Calendar.DATE);
		calenderEndDate = DateUtils.truncate(input.getEndDate(), Calendar.DATE);
		
		createSleepChoresSlots(input);
		addLecturesSlots(input.getLectures(), input.getTravelTime());
		createLunchDinnerSlots(input);
		
		createRevisionSchedule(input.getTasks());
		
		HashMap<Long, HashMap<Integer, ArrayList<Float>>> dailyTaskAllocation = initializeTaskAllocationMap(input.getTasks());
		
		GreedyForward greedyForward = new GreedyForward(getDailyFreeSlotsCopy(true));
		Callender forwardCallender = greedyForward.execute(dailyTaskAllocation, input.getTasks());
		forwardCallender.getSchedule().addAll(schedule);
		forwardCallender.getMessages().addAll(messages);
		
		clearTimeAllocated(input.getTasks());
		GreedyBackward greedyBackward = new GreedyBackward(getDailyFreeSlotsCopy(false));
		Callender backwardCallender = greedyBackward.execute(dailyTaskAllocation, input.getTasks());
		backwardCallender.getSchedule().addAll(schedule);
		backwardCallender.getMessages().addAll(messages);
		
		GreedyAvg greedyAvg = new GreedyAvg(getDailyFreeSlotsCopy(true), dailyTaskAllocation);
		Callender avgCallender = greedyAvg.execute(input.getTasks());
		avgCallender.getSchedule().addAll(schedule);
		avgCallender.getMessages().addAll(messages);
		
		ArrayList<Callender> callenderList = new ArrayList<>();
		callenderList.add(forwardCallender);
		callenderList.add(backwardCallender);
		callenderList.add(avgCallender);
		
		Collections.sort(forwardCallender.getSchedule());
		helper.schedulePrinter(forwardCallender.getSchedule());
		helper.messagePrinter(forwardCallender.getMessages());
		System.out.println("************************************");
		Collections.sort(backwardCallender.getSchedule());
		helper.schedulePrinter(backwardCallender.getSchedule());
		helper.messagePrinter(backwardCallender.getMessages());
		System.out.println("************************************");
		Collections.sort(avgCallender.getSchedule());
		helper.schedulePrinter(avgCallender.getSchedule());
		helper.messagePrinter(avgCallender.getMessages());
		System.out.println("************************************");
		helper.taskTimeAllocationPrinter(dailyTaskAllocation);
		
		return callenderList;
	}

	private void clearTimeAllocated(ArrayList<Task> tasks) {
		for(Task task : tasks) {
			task.setTimeAllocated(0f);
		}
		
	}

	private HashMap<Long, SortedSet<Slot>> getDailyFreeSlotsCopy(boolean isGreedyForward) {
		HashMap<Long, SortedSet<Slot>> dailyFreeSlotsCopy = new HashMap<>();
		SortedSet<Slot> dailySlots;
		for(Long day : dailyFreeSlots.keySet()) {
			if(isGreedyForward) {
				dailySlots = new TreeSet<Slot>(startTimeComparator);
			} else {
				dailySlots = new TreeSet<Slot>(endTimeComparator);
			}
			for(Slot slot : dailyFreeSlots.get(day)) {
				dailySlots.add(slot);
			}
			dailyFreeSlotsCopy.put(day, dailySlots);
		}
		return dailyFreeSlotsCopy;
	}

	private HashMap<Long, HashMap<Integer, ArrayList<Float>>> initializeTaskAllocationMap(ArrayList<Task> tasks) {
		HashMap<Long, HashMap<Integer, ArrayList<Float>>> dailyTaskAllocation = new HashMap<>();
		int index = 0;
		for(Task task : tasks) {
			task.setIndex(index);
			index++;
			
			Date taskStartDay = DateUtils.truncate(task.getStartTime(), Calendar.DATE);
			Date taskEndDay = DateUtils.truncate(task.getTargetTime(), Calendar.DATE);
			Date day = new Date();
			day = taskStartDay;
			
			while(!day.after(taskEndDay)) {
				if(dailyTaskAllocation.get(day.getTime())==null) {
					dailyTaskAllocation.put(day.getTime(), new HashMap<Integer, ArrayList<Float>>());
				}
				if(dailyTaskAllocation.get(day.getTime()).get(task.getIndex())==null) {
					HashMap<Integer, ArrayList<Float>> taskTimeAllocationMap = dailyTaskAllocation.get(day.getTime());
					ArrayList<Float> taskTime = new ArrayList<>();
					taskTime.add(0f);
					taskTime.add(0f);
					taskTime.add(0f);
					taskTimeAllocationMap.put(task.getIndex(), taskTime);
				}
				Date nextDay = DateUtils.addDays(day, 1);
				day=nextDay;
			}
		}
		
		return dailyTaskAllocation;
	}

	private void createRevisionSchedule(ArrayList<Task> taskPriority) {
		
		for(Task task : taskPriority) {
			if(task.getType().equals(Task.TaskName.Exam)) {
				Date examDay = DateUtils.truncate(task.getTargetTime(), Calendar.DATE);
				SortedSet<Slot> examDayFreeSlot = dailyFreeSlots.get(examDay.getTime());
				boolean revisionSlotFound = false;
				Slot toSplit = new Slot();
				for(Slot slot : examDayFreeSlot) {
					if((slot.getEndTime().before(task.getTargetTime()) || slot.getEndTime().getTime()==task.getTargetTime().getTime())
							&& (slot.getStartTime().before(DateUtils.addHours( slot.getEndTime(), -revisionTime)) ||  slot.getStartTime().getTime()==DateUtils.addHours( slot.getEndTime(), -revisionTime).getTime())) {
						revisionSlotFound = true;
						toSplit=slot;
					}
				}
				if(revisionSlotFound) {
					examDayFreeSlot.remove(toSplit);
					examDayFreeSlot.add(new Slot("", toSplit.getStartTime(), DateUtils.addHours( toSplit.getEndTime(), -revisionTime)));
					schedule.add(new Slot(Task.TaskName.Revision.name(), DateUtils.addHours( toSplit.getEndTime(), -revisionTime), toSplit.getEndTime()));
					
				} else {
					messages.add("Could not allocated " + revisionTime + " hr(s) Revision task for date : " + dayFormat.format(task.getTargetTime()));
				}
			}
		}
		
	}

	private void createLunchDinnerSlots(SchedulerInput input) {
		Date day = calenderStartDate;
		while(!day.after(calenderEndDate)) {
			Date nextDay = DateUtils.addDays(day, 1);
			
			Date lunchMin = DateUtils.addHours(day, lunchTimeMin);
			Date lunchMax = DateUtils.addHours(day, lunchTimeMax);
			
			createScheduleOfDurationBetweenTimeGiven(dailyFreeSlots.get(DateUtils.truncate(day, Calendar.DATE).getTime()), lunchMin, lunchMax, lunchDuration, Task.TaskName.Lunch.name());
			
			Date dinnerMin = DateUtils.addHours(day, dinnerTimeMin);
			Date dinnerMax = DateUtils.addHours(day, dinnerTimeMax);
			
			createScheduleOfDurationBetweenTimeGiven(dailyFreeSlots.get(DateUtils.truncate(day, Calendar.DATE).getTime()), dinnerMin, dinnerMax, dinnerDuration, Task.TaskName.Dinner.name());
			
			day=nextDay;
		}
		
	}
	
	private void createScheduleOfDurationBetweenTimeGiven(SortedSet<Slot> freeSlots, Date minTime, Date maxTime, int duration, String taskName) {
		Slot toSplit = new Slot();
		boolean containingMin=false;
		boolean containingMax=false;
		boolean containingMinMax=false;
		for(Slot slot: freeSlots) {
			if(slot.getStartTime().getTime() >= minTime.getTime() && slot.getStartTime().getTime() < maxTime.getTime()){
				if(helper.getDifference(slot.getStartTime(), slot.getEndTime()) >= duration) {
					toSplit=slot;
					containingMax=true;
					break;
				} 
			} else if(slot.getEndTime().getTime() > minTime.getTime() && slot.getEndTime().getTime() <= maxTime.getTime()) {
				if(helper.getDifference(minTime, slot.getEndTime()) >= duration) {
					toSplit=slot;
					containingMin=true;
					break;
				} 
			} else if(slot.getStartTime().before(minTime) && slot.getEndTime().after(maxTime)) {
				toSplit=slot;
				containingMinMax=true;
				break;
			}
		}
		if(containingMin) {
			freeSlots.remove(toSplit);
			freeSlots.add(new Slot("", toSplit.getStartTime(), DateUtils.addHours( toSplit.getEndTime(), -duration)));
			
			schedule.add(new Slot(taskName, DateUtils.addHours( toSplit.getEndTime(), -duration), toSplit.getEndTime()));
			
		} else if(containingMax){
			freeSlots.remove(toSplit);
			freeSlots.add(new Slot("", DateUtils.addHours( toSplit.getStartTime(), duration), toSplit.getEndTime()));
			
			schedule.add(new Slot(taskName, toSplit.getStartTime(), DateUtils.addHours( toSplit.getStartTime(), duration)));
		} else if (containingMinMax) {
			freeSlots.remove(toSplit);
			freeSlots.add(new Slot("", toSplit.getStartTime(), minTime));
			freeSlots.add(new Slot("", DateUtils.addHours( minTime, duration), toSplit.getEndTime()));
			
			schedule.add(new Slot(taskName, minTime, DateUtils.addHours( minTime, duration)));
		} else {
			messages.add("Could not allocate " + duration + " hr(s) to " + taskName + " on day : " + dayFormat.format(minTime));
		}
		
	}

	private void addLecturesSlots(ArrayList<Slot> lectures, String travelTime) {
		for(Slot lecture : lectures) {
			SortedSet<Slot> freeSlots = dailyFreeSlots.get(DateUtils.truncate(lecture.getStartTime(), Calendar.DATE).getTime());
			Slot toRemove = new Slot();
			
			for(Slot freeSlot : freeSlots) {
				if(freeSlot.getStartTime().before(lecture.getStartTime()) && freeSlot.getEndTime().after(lecture.getEndTime())) {
					toRemove=freeSlot;
					break;
				}
			}
			freeSlots.remove(toRemove);
			freeSlots.add(new Slot("", toRemove.getStartTime(), helper.subtractStringTime(lecture.getStartTime(), travelTime)));
			freeSlots.add(new Slot("", helper.addStringTime(lecture.getEndTime(), travelTime), toRemove.getEndTime()));
			
			schedule.add(lecture);
			schedule.add(new Slot(Task.TaskName.Travel.name(), helper.subtractStringTime(lecture.getStartTime(), travelTime), lecture.getStartTime()));
			schedule.add(new Slot(Task.TaskName.Travel.name(), lecture.getEndTime(), helper.addStringTime(lecture.getEndTime(), travelTime)));
			
		}
		
	}

	private void createSleepChoresSlots(SchedulerInput input) throws ParseException {
		
		Date day = calenderStartDate;
		while(!day.after(calenderEndDate)) {
			Date nextDay = DateUtils.addDays(day, 1);
			
			//Sleep Slots
			Slot sleepMorningSlot = new Slot(Task.TaskName.Sleep.name(), day, helper.addStringTime(day, input.getSleepEndTime()));
			Slot sleepNightSlot = new Slot(Task.TaskName.Sleep.name(), helper.addStringTime(day, input.getSleepStartTime()), DateUtils.addMinutes(nextDay, -1));
			//daily chores
			Slot dailyChoresSlot = new Slot(Task.TaskName.Chores.name(), sleepMorningSlot.getEndTime(), DateUtils.addHours(sleepMorningSlot.getEndTime(), 2));
			schedule.add(sleepMorningSlot);
			schedule.add(sleepNightSlot);
			schedule.add(dailyChoresSlot);
			
			//create empty free slots
			SortedSet<Slot> sortedSlots = new TreeSet<Slot>();
			Slot newSlot = new Slot("", dailyChoresSlot.getEndTime(), sleepNightSlot.getStartTime());
			sortedSlots.add(newSlot);
			dailyFreeSlots.put(day.getTime(), sortedSlots);
			day=nextDay;
		}
	}
	
	public Comparator<Slot> startTimeComparator = new Comparator<Slot>() {
		@Override
		public int compare(Slot a, Slot b) {
			if (a.getStartTime()==b.getStartTime())
				return 0;
			else if(a.getStartTime().before(b.getStartTime()))
				return -1;
			else
				return 1;
		}
	};
	
	public Comparator<Slot> endTimeComparator = new Comparator<Slot>() {
		@Override
		public int compare(Slot a, Slot b) {
			if (a.getEndTime()==b.getEndTime())
				return 0;
			else if(a.getEndTime().after(b.getEndTime()))
				return -1;
			else
				return 1;
		}
	};
}
