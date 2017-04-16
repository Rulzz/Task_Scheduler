package Scheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.SortedSet;

import org.apache.commons.lang.time.DateUtils;

import Domain.Callender;
import Domain.Slot;
import Domain.Task;

public class GreedyForward {
	
	HashMap<Long, SortedSet<Slot>> dailyFreeSlots = new HashMap<>();
	ArrayList<Slot> forwardSchedule = new ArrayList<>();
	
	ScheduleHelper helper = new ScheduleHelper();

	public GreedyForward(HashMap<Long, SortedSet<Slot>> dailyFreeSlots) {
		this.dailyFreeSlots=dailyFreeSlots;
	}

	public Callender execute(HashMap<Long, HashMap<Integer, ArrayList<Float>>> dailyTaskAllocation, ArrayList<Task> taskList) {
		PriorityQueue<Task> taskPriority = new PriorityQueue<Task>(taskList.size());
		taskPriority.addAll(taskList);
		ArrayList<String> messages = new ArrayList<>();
		
		while (!taskPriority.isEmpty()) {
			Task task = taskPriority.poll();
			if(helper.getDuration(task.getTimeToComplete())==0f) {
				continue;
			}
			Date taskStartDay = DateUtils.truncate(task.getStartTime(), Calendar.DATE);
			Date taskEndDay = DateUtils.truncate(task.getTargetTime(), Calendar.DATE);
			Date day = new Date();
			day = taskStartDay;
			
			while(!day.after(taskEndDay) && (helper.getDuration(task.getTimeToComplete())-task.getTimeAllocated())>0) {
				ArrayList<Float> timeAllocation = dailyTaskAllocation.get(day.getTime()).get(task.getIndex()); 
				timeAllocation.add(0, allocateTimeForTaskForDay(day, task));
				Date nextDay = DateUtils.addDays(day, 1);
				day=nextDay;
			}
			Float remainingTime = helper.getDuration(task.getTimeToComplete())-task.getTimeAllocated();
			if(remainingTime>0) {
				Float minutes = (remainingTime- remainingTime.intValue())*60;
				messages.add("Could not allocate " + remainingTime.intValue() + " Hrs and " + minutes.intValue() + " Min for task : " + task.getName());
			}
		}
		
		Callender  forwardCallender = new Callender();
		forwardCallender.setSchedule(forwardSchedule);
		forwardCallender.setMessages(messages);
		return forwardCallender;
	}

	private Float allocateTimeForTaskForDay(Date day, Task task) {
		Float timeAllocated = 0f;
		ArrayList<Slot> toRemove = new ArrayList<>();
		ArrayList<Slot> toAdd = new ArrayList<>();
		
		for(Slot slot : dailyFreeSlots.get(day.getTime())) {
			Float remainingTaskTime = helper.getDuration(task.getTimeToComplete())-task.getTimeAllocated();
			if(remainingTaskTime<=0) {
				break;
			}
			if(slot.getEndTime().getTime() <= task.getStartTime().getTime() || slot.getStartTime().getTime() >= task.getTargetTime().getTime()) {
				continue;
			} else if (slot.getStartTime().getTime() < task.getStartTime().getTime() && slot.getEndTime().getTime() > task.getStartTime().getTime() && slot.getEndTime().getTime() < task.getTargetTime().getTime()) {
				Float availableSlotTime = helper.getDifference(task.getStartTime(), slot.getEndTime());
				if(availableSlotTime >= remainingTaskTime) {
					timeAllocated=timeAllocated+remainingTaskTime;
					task.setTimeAllocated(helper.getDuration(task.getTimeToComplete()));
					toRemove.add(slot);
					toAdd.add(new Slot("", slot.getStartTime(), task.getStartTime()));
					if(availableSlotTime > remainingTaskTime) toAdd.add(new Slot("", helper.addFloatTime(task.getStartTime(), remainingTaskTime), slot.getEndTime()));
					forwardSchedule.add(new Slot(task.getName(), task.getStartTime(), helper.addFloatTime(task.getStartTime(), remainingTaskTime)));
					break;
				} else {
					timeAllocated=timeAllocated+availableSlotTime;
					task.setTimeAllocated(task.getTimeAllocated()+availableSlotTime);
					toRemove.add(slot);
					toAdd.add(new Slot("", slot.getStartTime(), task.getStartTime()));
					forwardSchedule.add(new Slot(task.getName(),task.getStartTime(), slot.getEndTime()));
				}
			} else if (slot.getStartTime().getTime() < task.getTargetTime().getTime() && slot.getEndTime().getTime() > task.getTargetTime().getTime() && slot.getStartTime().getTime() > task.getStartTime().getTime()) {
				Float availableSlotTime = helper.getDifference(slot.getStartTime(), task.getTargetTime());
				if(availableSlotTime >= remainingTaskTime) {
					timeAllocated=timeAllocated+remainingTaskTime;
					task.setTimeAllocated(helper.getDuration(task.getTimeToComplete()));
					toRemove.add(slot);
					toAdd.add(new Slot("", helper.addFloatTime(slot.getStartTime(), remainingTaskTime), slot.getEndTime()));
					forwardSchedule.add(new Slot(task.getName(), slot.getStartTime(), helper.addFloatTime(slot.getStartTime(), remainingTaskTime)));
					break;
				} else {
					timeAllocated=timeAllocated+availableSlotTime;
					task.setTimeAllocated(task.getTimeAllocated()+availableSlotTime);
					toRemove.add(slot);
					toAdd.add(new Slot("", task.getTargetTime(), slot.getEndTime()));
					forwardSchedule.add(new Slot(task.getName(), slot.getStartTime(), task.getTargetTime()));
				}
			} else if (slot.getStartTime().getTime() <= task.getStartTime().getTime() && slot.getEndTime().getTime() >= task.getTargetTime().getTime() ) {
				Float availableSlotTime = helper.getDifference(task.getStartTime(), task.getTargetTime());
				timeAllocated=timeAllocated+availableSlotTime;
				task.setTimeAllocated(helper.getDuration(task.getTimeToComplete()));
				toRemove.add(slot);
				if(slot.getStartTime().getTime() < task.getStartTime().getTime()) toAdd.add(new Slot("", slot.getStartTime(), task.getStartTime()));
				if(slot.getEndTime().getTime() > task.getTargetTime().getTime()) toAdd.add(new Slot("", task.getTargetTime(), slot.getEndTime()));
				forwardSchedule.add(new Slot(task.getName(), task.getStartTime(), task.getTargetTime()));
				break;
			} else {
				Float slotTime = helper.getDifference(slot.getStartTime(), slot.getEndTime());
				
				if(slotTime >= remainingTaskTime) {
					timeAllocated=timeAllocated+remainingTaskTime;
					task.setTimeAllocated(helper.getDuration(task.getTimeToComplete()));
					toRemove.add(slot);
					if(slotTime > remainingTaskTime) toAdd.add(new Slot("", helper.addFloatTime(slot.getStartTime(), remainingTaskTime), slot.getEndTime()));
					forwardSchedule.add(new Slot(task.getName(),slot.getStartTime(), helper.addFloatTime(slot.getStartTime(), remainingTaskTime)));
					break;
				}  else {
					timeAllocated=timeAllocated+slotTime;
					task.setTimeAllocated(task.getTimeAllocated()+slotTime);
					toRemove.add(slot);
					forwardSchedule.add(new Slot(task.getName(),slot.getStartTime(), slot.getEndTime()));
				}
			}
		}
		dailyFreeSlots.get(day.getTime()).removeAll(toRemove);
		dailyFreeSlots.get(day.getTime()).addAll(toAdd);
		return timeAllocated;
		
	}

	

}
