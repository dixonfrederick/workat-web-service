package id.ac.ui.cs.advprog.workatwebservice;

import com.netflix.discovery.converters.Auto;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;


@SpringBootApplication
@EnableDiscoveryClient
public class WorkatWebServiceApplication {
	@Autowired
	private WebClient.Builder webClientBuilder;

	public static void main(String[] args) {
		SpringApplication.run(WorkatWebServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public WebClient webClient() {
		return webClientBuilder.build();
	}
}
