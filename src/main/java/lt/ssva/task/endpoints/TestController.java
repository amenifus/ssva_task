package lt.ssva.task.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lt.ssva.task.pojo.TestResponse;
import lt.ssva.task.services.TestService;
import lt.ssva.task.validators.ValidUrl;

@RestController
@RequestMapping("/test")
@Validated
public class TestController {

	public TestService testService;

	@Autowired
	public TestController(TestService testService) {
		this.testService = testService;
	}

    @GetMapping("/getMapServerInfo")
    public TestResponse getMapServerInfo(@RequestParam(name="url", required = true) @ValidUrl String mapServerInfoURL) throws Exception {
    	return testService.getMapServerInfo(mapServerInfoURL);
    }

}