package dev.community.gdg;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class CatchmetalkHelloController {

	@GetMapping(produces = "text/plain")
	public String hello() {
		return "Hello Controller";
	}

}
