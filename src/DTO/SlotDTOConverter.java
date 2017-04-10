package DTO;

import java.text.SimpleDateFormat;

import Domain.Slot;

public class SlotDTOConverter {
	SimpleDateFormat hrFormat = new SimpleDateFormat("HH:mm");
	public SlotDTO convert(Slot slot) {
		SlotDTO slotDto = new SlotDTO();
		slotDto.setName(slot.getName());
		slotDto.setStartTime(hrFormat.format(slot.getStartTime()));
		slotDto.setEndTime(hrFormat.format(slot.getEndTime()));
		return slotDto;
	}
}
