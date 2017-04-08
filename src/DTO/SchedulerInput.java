package DTO;

import java.util.ArrayList;
import java.util.Date;

import Domain.Slot;
import Domain.Task;

public class SchedulerInput {
	private String sleepStartTime;
	private String sleepEndTime;
	private String travelTime;
	private Date startDate;
	private Date endDate;
	
	private ArrayList<Slot> lectures;
	private ArrayList<Task> tasks;
	
	
	public String getSleepStartTime() {
		return sleepStartTime;
	}
	public void setSleepStartTime(String sleepStartTime) {
		this.sleepStartTime = sleepStartTime;
	}
	public String getSleepEndTime() {
		return sleepEndTime;
	}
	public void setSleepEndTime(String sleepEndTime) {
		this.sleepEndTime = sleepEndTime;
	}
	public String getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}
	public ArrayList<Slot> getLectures() {
		return lectures;
	}
	public void setLectures(ArrayList<Slot> lectures) {
		this.lectures = lectures;
	}
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
