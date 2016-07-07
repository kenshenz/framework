package com.ksn.core.consul;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.CatalogClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.model.ConsulResponse;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.catalog.CatalogService;

public class ConsulClientTest {

	@Test
	public void test() throws NotRegisteredException {
		Consul consul = Consul.builder().build();
		AgentClient agentClient = consul.agentClient();

		ImmutableRegistration regist = ImmutableRegistration.builder().id("1").name("MyService").address("192.168.1.99")
				.port(8888).addTags("mok").build();
		
		ImmutableRegistration regist2 = ImmutableRegistration.builder().id("2").name("MyService").address("192.168.1.100")
				.port(8888).addTags("mok").build();

		consul.agentClient().register(regist);

		agentClient.register(regist2);
	}

	@Test
	public void test2() {
		Consul consul = Consul.builder().build();
		CatalogClient catalogClient = consul.catalogClient();
		
		ConsulResponse<Map<String, List<String>>> services = catalogClient.getServices();
		Map<String, List<String>> map = services.getResponse();
		for (String serviceName : map.keySet()) {
			System.out.println("服务：" + serviceName);
			
			ConsulResponse<List<CatalogService>> service = catalogClient.getService(serviceName);
			for (CatalogService s : service.getResponse()) {
				System.out.println("id=" + s.getServiceId() + ", name=" + s.getServiceName() + ", ip=" + s.getServiceAddress() + ", addr=" + s.getAddress() + ", port=" + s.getServicePort());
			}
		}
	}

}
