package com.github.jenkinsx.quickstarts.springbootrestprometheus;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
@Controller
public class SpringBootRestPrometheusApplication {

	public static void main(String[] args) {
		MeterRegistry registry = SpringApplication.run(SpringBootRestPrometheusApplication.class, args).getBean(MeterRegistry.class);
	}

	@Autowired
	private MeterRegistry registry;

	@GetMapping(path = "/", produces = "application/json")
	@ResponseBody
	public Map<String, Object> landingPage() {
		Counter.builder("mymetric").tag("foo", "bar").register(registry).increment();
		return Collections.singletonMap("hello", "world");
	}

}
