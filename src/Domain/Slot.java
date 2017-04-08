package Domain;

import java.util.Date;

public class Slot  implements Comparable<Slot> {
	private String name;
	private Date startTime;
	private Date endTime;
	
	public Slot() {}
	
	public Slot(String name, Date startTime, Date endTime) {
		this.startTime=startTime;
		this.endTime=endTime;
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public int compareTo(Slot slot) {
		if (this.getStartTime()==slot.getStartTime())
			return 0;
		else if(this.getStartTime().before(slot.getStartTime()))
			return -1;
		else
			return 1;
	}
}
