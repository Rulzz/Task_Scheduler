package DTO;

import java.util.ArrayList;
import java.util.HashMap;

public class CallenderDTO {
	private HashMap<String, ArrayList<SlotDTO>> schedule;
	private ArrayList<String> messages;
	public HashMap<String, ArrayList<SlotDTO>> getSchedule() {
		return schedule;
	}
	public void setSchedule(HashMap<String, ArrayList<SlotDTO>> schedule) {
		this.schedule = schedule;
	}
	public ArrayList<String> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}
	
}
