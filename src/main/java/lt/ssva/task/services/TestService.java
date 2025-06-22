package lt.ssva.task.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lt.ssva.task.handlers.UserError;
import lt.ssva.task.pojo.BaseLayerInfo;
import lt.ssva.task.pojo.GetMapInfoResponse;
import lt.ssva.task.pojo.TestResponse;

@Service
public class TestService {

	private static final String URL_EXAMPLE = "https://www.geoportal.lt/mapproxy/nzt_ort10lt_2024_2026/MapServer";
	
	@Autowired
    private ObjectMapper objectMapper;
	
	private final RestTemplate restTemplate = new RestTemplate();

    public TestResponse getMapServerInfo(String url) throws Exception {
    	GetMapInfoResponse mapServerInfoResponse = fetchMapServerInfo(url);
    	
    	TestResponse response = new TestResponse();

    	response.mapName = mapServerInfoResponse.mapName;
    	response.description = mapServerInfoResponse.description;

    	mapServerInfoResponse.layers.forEach(layer -> {
    		BaseLayerInfo info = new BaseLayerInfo();
    		info.id = layer.id;
    		info.name = layer.name;
    		
    		response.layers.add(info);
		});

    	return response;
    }
    
    private GetMapInfoResponse fetchMapServerInfo(String url) {
    	String plainText;
    	
    	try {
    		plainText = restTemplate.getForObject(url + "?f=json", String.class);
    	} catch (Exception e) {
			throw new UserError(String.format("Nepavyko pasiekti '%s': patikrinkite ar pateiktas URL atitinka formatą %s", url, URL_EXAMPLE));
		}
    	
    	try {
    		return objectMapper.readValue(plainText, GetMapInfoResponse.class);
    	} catch (Exception e) {
    		UUID id = UUID.randomUUID();
    		
    		System.out.println(String.format("Failed to parse result of %s\n, ERROR=%s", url, e));
    		
			throw new UserError(String.format("Nepavyko nuskaityti '%s' rezultato. ID=%s", url, id));
		}
    }

}