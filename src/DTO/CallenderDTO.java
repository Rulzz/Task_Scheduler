package DTO;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CallenderDTO {
	private LinkedHashMap<String, ArrayList<SlotDTO>> schedule;
	private ArrayList<String> messages;
	public LinkedHashMap<String, ArrayList<SlotDTO>> getSchedule() {
		return schedule;
	}
	public void setSchedule(LinkedHashMap<String, ArrayList<SlotDTO>> schedule) {
		this.schedule = schedule;
	}
	public ArrayList<String> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}
	
}
