package Scheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;

import Domain.Callender;
import Domain.Slot;
import Domain.Task;

public class GreedyAvg {
	ScheduleHelper helper = new ScheduleHelper();
	
	private HashMap<Long, SortedSet<Slot>> dailyFreeSlots = new HashMap<>();
	private HashMap<Long, HashMap<Integer, ArrayList<Float>>> dailyTaskAllocation;
	
	ArrayList<Slot> avgSchedule = new ArrayList<>();

	public GreedyAvg(HashMap<Long, SortedSet<Slot>> dailyFreeSlots, HashMap<Long, HashMap<Integer, ArrayList<Float>>> dailyTaskAllocation) {
		this.dailyFreeSlots = dailyFreeSlots;
		this.dailyTaskAllocation = dailyTaskAllocation;
	}
	
	public Callender execute(ArrayList<Task> taskList) {
		
		HashMap<Integer, Float> taskTimeAllocated = new HashMap<>();
		for(Task task : taskList) {
			taskTimeAllocated.put(task.getIndex(), 0f);
		}
		
		for(Long day : dailyTaskAllocation.keySet()) {
			HashMap<Integer, ArrayList<Float>> dayWiseTaskAllocation = dailyTaskAllocation.get(day);
			for(Integer taskId : dayWiseTaskAllocation.keySet()) {
				ArrayList<Float> timeAllocation = dayWiseTaskAllocation.get(taskId);
				Float avg = (timeAllocation.get(0) + timeAllocation.get(1))/2;
				if(avg>0f) allocateTimeForTaskForDay(day, getTaskById(taskList, taskId), avg);
				timeAllocation.add(2, avg);
				taskTimeAllocated.put(taskId, taskTimeAllocated.get(taskId)+avg);
			}
		}
		
		Callender  forwardCallender = new Callender();
		forwardCallender.setSchedule(avgSchedule);
		
		ArrayList<String> messages = new ArrayList<>();
		for(Task task : taskList) {
			Float remainingTime = helper.getDuration(task.getTimeToComplete())-taskTimeAllocated.get(task.getIndex());
			Float minutes = (remainingTime - remainingTime.intValue())*60;
			if(remainingTime >0) {
				messages.add("Could not allocate " + remainingTime.intValue() + " Hrs and " + minutes.intValue() + " Min for task : " + task.getName());
			}
		}
		forwardCallender.setMessages(messages);
		return forwardCallender;
	}

	private void allocateTimeForTaskForDay(Long day, Task task, Float avgTimeAllocation) {
		Float timeAllocated = 0f;
		SortedSet<Slot> freeSlots = dailyFreeSlots.get(day);

		ArrayList<Slot> toRemove = new ArrayList<>();
		ArrayList<Slot> toAdd = new ArrayList<>();
		for(Slot slot : freeSlots) {
			Float remainingTaskTime = avgTimeAllocation-timeAllocated;
			if(slot.getEndTime().getTime() <= task.getStartTime().getTime() || slot.getStartTime().getTime() >= task.getTargetTime().getTime()) {
				continue;
			} else if (slot.getStartTime().getTime() < task.getStartTime().getTime() && slot.getEndTime().getTime() > task.getStartTime().getTime() && slot.getEndTime().getTime() < task.getTargetTime().getTime()) {
				Float availableSlotTime = helper.getDifference(task.getStartTime(), slot.getEndTime());
				if(availableSlotTime >= avgTimeAllocation) {
					timeAllocated=timeAllocated+remainingTaskTime;
					task.setTimeAllocated(helper.getDuration(task.getTimeToComplete()));
					toRemove.add(slot);
					toAdd.add(new Slot("", slot.getStartTime(), task.getStartTime()));
					if(availableSlotTime > remainingTaskTime) toAdd.add(new Slot("", helper.addFloatTime(task.getStartTime(), remainingTaskTime), slot.getEndTime()));
					avgSchedule.add(new Slot(task.getName() + "-" + task.getType().name(), task.getStartTime(), helper.addFloatTime(task.getStartTime(), remainingTaskTime)));
					break;
				} else {
					timeAllocated=timeAllocated+availableSlotTime;
					task.setTimeAllocated(task.getTimeAllocated()+availableSlotTime);
					toRemove.add(slot);
					toAdd.add(new Slot("", slot.getStartTime(), task.getStartTime()));
					avgSchedule.add(new Slot(task.getName() + "-" + task.getType().name(),task.getStartTime(), slot.getEndTime()));
				}
			} else if (slot.getStartTime().getTime() < task.getTargetTime().getTime() && slot.getEndTime().getTime() > task.getTargetTime().getTime() && slot.getStartTime().getTime() > task.getStartTime().getTime()) {
				Float availableSlotTime = helper.getDifference(slot.getStartTime(), task.getTargetTime());
				if(availableSlotTime >= remainingTaskTime) {
					timeAllocated=timeAllocated+remainingTaskTime;
					task.setTimeAllocated(helper.getDuration(task.getTimeToComplete()));
					toRemove.add(slot);
					toAdd.add(new Slot("", helper.addFloatTime(slot.getStartTime(), remainingTaskTime), slot.getEndTime()));
					avgSchedule.add(new Slot(task.getName() + "-" + task.getType().name(), slot.getStartTime(), helper.addFloatTime(slot.getStartTime(), remainingTaskTime)));
					break;
				} else {
					timeAllocated=timeAllocated+availableSlotTime;
					task.setTimeAllocated(task.getTimeAllocated()+availableSlotTime);
					toRemove.add(slot);
					toAdd.add(new Slot("", task.getTargetTime(), slot.getEndTime()));
					avgSchedule.add(new Slot(task.getName() + "-" + task.getType().name(), slot.getStartTime(), task.getTargetTime()));
				}
			} else if (slot.getStartTime().getTime() <= task.getStartTime().getTime() && slot.getEndTime().getTime() >= task.getTargetTime().getTime() ) {
				Float availableSlotTime = helper.getDifference(task.getStartTime(), task.getTargetTime());
				timeAllocated=timeAllocated+availableSlotTime;
				task.setTimeAllocated(helper.getDuration(task.getTimeToComplete()));
				toRemove.add(slot);
				if(slot.getStartTime().getTime() < task.getStartTime().getTime()) toAdd.add(new Slot("", slot.getStartTime(), task.getStartTime()));
				if(slot.getEndTime().getTime() > task.getTargetTime().getTime()) toAdd.add(new Slot("", task.getTargetTime(), slot.getEndTime()));
				avgSchedule.add(new Slot(task.getName() + "-" + task.getType().name(), task.getStartTime(), task.getTargetTime()));
				break;
			} else {
				Float slotTime = helper.getDifference(slot.getStartTime(), slot.getEndTime());
				
				if(slotTime >= remainingTaskTime) {
					timeAllocated=timeAllocated+remainingTaskTime;
					task.setTimeAllocated(helper.getDuration(task.getTimeToComplete()));
					toRemove.add(slot);
					if(slotTime > remainingTaskTime) toAdd.add(new Slot("", helper.addFloatTime(slot.getStartTime(), remainingTaskTime), slot.getEndTime()));
					avgSchedule.add(new Slot(task.getName() + "-" + task.getType().name(),slot.getStartTime(), helper.addFloatTime(slot.getStartTime(), remainingTaskTime)));
					break;
				}  else {
					timeAllocated=timeAllocated+slotTime;
					task.setTimeAllocated(task.getTimeAllocated()+slotTime);
					toRemove.add(slot);
					avgSchedule.add(new Slot(task.getName() + "-" + task.getType().name(),slot.getStartTime(), slot.getEndTime()));
				}
			}
		}
		
		freeSlots.removeAll(toRemove);
		freeSlots.addAll(toAdd);
	}

	private Task getTaskById(ArrayList<Task> taskList, Integer taskId) {
		for(Task task : taskList) {
			if(task.getIndex()==taskId) return task;
		}
		return null;
	}

}
