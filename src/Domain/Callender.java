package Domain;

import java.util.ArrayList;

public class Callender {
	private ArrayList<Slot> schedule;
	private ArrayList<String> messages;
	public ArrayList<Slot> getSchedule() {
		return schedule;
	}
	public void setSchedule(ArrayList<Slot> schedule) {
		this.schedule = schedule;
	}
	public ArrayList<String> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}
	
}
