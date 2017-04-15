package Domain;

import java.util.Date;

public class Task implements Comparable<Task>{
	private String name;
	private TaskName type;
	private Date targetTime;
	private Date startTime;
	private String timeToComplete;
	private Float timeAllocated=0f;
	private int index;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getTargetTime() {
		return targetTime;
	}
	public void setTargetTime(Date targetTime) {
		this.targetTime = targetTime;
	}
	public String getTimeToComplete() {
		return timeToComplete;
	}
	public void setTimeToComplete(String timeToComplete) {
		this.timeToComplete = timeToComplete;
	}
	
	public TaskName getType() {
		return type;
	}
	public void setType(TaskName type) {
		this.type = type;
	}
	

	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	public Float getTimeAllocated() {
		return timeAllocated;
	}
	public void setTimeAllocated(Float timeAllocated) {
		this.timeAllocated = timeAllocated;
	}
	@Override
	public int compareTo(Task task) {
		if(this.getType().getPriority() > task.getType().getPriority()) {
			return 1;
		} else if (this.getType().getPriority() < task.getType().getPriority()) {
			return -1;
		} else {
			if (this.getTargetTime()==task.getTargetTime())
				return 0;
			else if(this.getTargetTime().before(task.getTargetTime()))
				return -1;
			else
				return 1;
		}
	}

	public enum TaskName {
		Lecture(0), Sleep(0), Travel(0), Exam(4), Assignment(3), HW(3), Extra(2), Revision(2), General(1), Chores(0), Lunch(0), Dinner(0), Regular(0), Other(1);
		private final int priority;
		TaskName(int priority) { this.priority = priority; }
	    public int getPriority() { return priority; }
	}
	
	
	
}
