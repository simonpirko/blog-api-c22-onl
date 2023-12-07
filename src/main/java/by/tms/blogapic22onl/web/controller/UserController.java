package by.tms.blogapic22onl.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Simon Pirko on 7.12.23
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	public ResponseEntity<Void> create(){
		log.info("Test");
		return ResponseEntity.ok().build();
	}
}
