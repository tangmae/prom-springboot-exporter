package rakuten.rpp.prototype.prometheus.exporter;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
// import the Prometheus packages.
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
// Prometheus counter package.
import io.prometheus.client.Counter;
// Prometheus Histogram package.
import io.prometheus.client.Histogram;

@SpringBootApplication
@RestController
// Add a Prometheus metrics enpoint to the route `/prometheus`. `/metrics` is already taken by Actuator.
@EnablePrometheusEndpoint
// Pull all metrics from Actuator and expose them as Prometheus metrics. Need to disable security feature in properties file.
@EnableSpringBootMetricsCollector
public class App 
{
	static final Counter vote = Counter.build().name("government_vote").help("Total vote for current government.").register();
	
	@RequestMapping("/")
	public String home() {
		vote.inc();
		
		return "Thank you for your vote !";
	}
	
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
