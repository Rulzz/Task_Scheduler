package DTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;

import org.apache.commons.lang.time.DateUtils;

import Domain.Callender;
import Domain.Slot;
import Scheduler.ScheduleHelper;

public class CallenderDTOConverter {
	
	ScheduleHelper helper = new ScheduleHelper();
	SlotDTOConverter slotDTOConverter = new SlotDTOConverter();
	SimpleDateFormat dayFormat = new SimpleDateFormat("dd-M-yyyy");
	
	public HashMap<String, ArrayList<SlotDTO>> convert(ArrayList<Slot> schedule) {
		HashMap<String, ArrayList<SlotDTO>> callender = new HashMap<>();
		Collections.sort(schedule);
		for(Slot slot : schedule) {
			String slotDay = dayFormat.format(DateUtils.truncate(slot.getStartTime(), Calendar.DATE));
			if(callender.get(slotDay)==null) {
				ArrayList<SlotDTO> slots = new ArrayList<>();
				slots.add(slotDTOConverter.convert(slot));
				callender.put(slotDay, slots);
			} else {
				callender.get(slotDay).add(slotDTOConverter.convert(slot));
			}
		}
		return callender;
	}
	public ArrayList<CallenderDTO> convertCallender(ArrayList<Callender> callenderList) {
		ArrayList<CallenderDTO> callenderDtoList = new ArrayList<>();
		for(Callender callender : callenderList) {
			CallenderDTO callenderDto = new CallenderDTO();
			callenderDto.setSchedule(convert(callender.getSchedule()));
			callenderDto.setMessages(callender.getMessages());
			callenderDtoList.add(callenderDto);
		}
		return callenderDtoList;
	}
}
