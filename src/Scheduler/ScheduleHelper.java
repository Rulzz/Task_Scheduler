package Scheduler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.DateUtils;

import Domain.Slot;

public class ScheduleHelper {
	public Date addStringTime(Date day, String time) {
		return DateUtils.addMinutes(DateUtils.addHours(day, getHours(time)), getMinutes(time));
	}
	public Date addFloatTime(Date day, Float time) {
		Float minutes = (time - time.intValue())*60;
		return DateUtils.addMinutes(DateUtils.addHours(day, time.intValue()), minutes.intValue());
	}
	public Date subFloatTime(Date day, Float time) {
		Float minutes = (time - time.intValue())*60;
		return DateUtils.addMinutes(DateUtils.addHours(day, -time.intValue()), -minutes.intValue());
	}
	
	public Date subtractStringTime(Date day, String time) {
		return DateUtils.addMinutes(DateUtils.addHours(day, -getHours(time)), -getMinutes(time));
	}

	public int getHours(String time) {
		return Integer.parseInt(time.split(":")[0]);
	}
	public int getMinutes(String time) {
		if(time.split(":").length>1) {
			return Integer.parseInt(time.split(":")[1]);
		}
		return 0;
	}
	public Float getDuration(String time) {
		return getHours(time)+Integer.valueOf(getMinutes(time)).floatValue()/60;
	}
	public Float getDifference(Date startTime, Date endTime) {
		Long duration  = endTime.getTime() - startTime.getTime();
		Float diffInMinutes = Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(duration)%60).floatValue()/60;
		long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
		return diffInHours+diffInMinutes;
	}
	
	
	public void freeSlotPrinter(HashMap<Long, SortedSet<Slot>> myCallenderDayFreeSlots) {
		ArrayList<Long> days = new ArrayList<>();
		for(Long timestamp : myCallenderDayFreeSlots.keySet()) {
			days.add(timestamp);
		}
		Collections.sort(days);
		
		for(Long day : days) {
			SortedSet<Slot> freeSlots = myCallenderDayFreeSlots.get(day);
			System.out.println(new Timestamp(day));
			for(Slot free : freeSlots) {
				System.out.println("Free Slot Start time : " + free.getStartTime() + " ; Free Slot End Time : " + free.getEndTime());
			}
		}
		
	}

	
	public void schedulePrinter(ArrayList<Slot> schedule) {
		System.out.println("------------------------------------------");
		for(Slot slot : schedule) {
			System.out.println(slot.getName() + " ; Start time : " + slot.getStartTime() + " ; End time : " + slot.getEndTime() );
		}
			
		
	}
	public void messagePrinter(ArrayList<String> messages) {
		System.out.println("------------------------------------------");
		for(String message : messages) {
			System.out.println(message);
		}
		
	}
	public void taskTimeAllocationPrinter(HashMap<Long, HashMap<Integer, ArrayList<Float>>> dailyTaskAllocation) {
		System.out.println("------------------------------------------");
		for(Long day : dailyTaskAllocation.keySet()) {
			Date today = new Date(day);
			System.out.println("Day : " + today);
			for(Integer taskId : dailyTaskAllocation.get(day).keySet()) {
				System.out.println(taskId + " - " + dailyTaskAllocation.get(day).get(taskId).toString());
			}
			
		}
		
	}

	
}
