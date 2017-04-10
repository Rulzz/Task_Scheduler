package Scheduler;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import DTO.CallenderDTOConverter;
import DTO.SchedulerInput;
import Domain.Callender;

@Provider
@Path("/CallenderResource") 
public class CallenderResource {
	ObjectMapper mapper = new ObjectMapper();
	CallenderDTOConverter converter = new CallenderDTOConverter();
	SchedulerService service = new SchedulerService();

	@POST
	@Path("/schedule") 
	@Produces(MediaType.APPLICATION_JSON) 
	public Response schedule(String inputString) throws JsonParseException, JsonMappingException, IOException, ParseException  { 
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		SchedulerInput input = mapper.readValue(inputString, SchedulerInput.class);
		ArrayList<Callender> callenderList = service.createSchedule(input);
		String jsonInString = mapper.writeValueAsString(converter.convertCallender(callenderList));
		System.out.println("************************************************************");
		System.out.println(jsonInString);
		System.out.println("************************************************************");
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600").entity(jsonInString).build();
	}
}
