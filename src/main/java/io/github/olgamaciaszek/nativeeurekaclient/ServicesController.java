package io.github.olgamaciaszek.nativeeurekaclient;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olga Maciaszek-Sharma
 */
@RestController
public class ServicesController {

	private final DiscoveryClient discoveryClient;

	public ServicesController(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	@GetMapping("/services")
	public List<String> getServices() {
		return discoveryClient.getServices();
	}

	@GetMapping("/instances/{serviceId}")
	public List<ServiceInstance> getInstances(@PathVariable("serviceId") String serviceId){
		return discoveryClient.getInstances(serviceId);
	}
}
