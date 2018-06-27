package or.damo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "or.damo.po")
public class SpringEsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEsApplication.class, args);

	}

	@Bean
	public TransportClientFactoryBean transportClientFactoryBean(){
		TransportClientFactoryBean bean = new TransportClientFactoryBean();

		bean.setClientTransportSniff(false);
		bean.setClusterName("elasticsearch");
		bean.setClusterNodes("xdp.com:9300");
		bean.setClientIgnoreClusterName(true);
		bean.setClientPingTimeout("20s");
		return  bean;
	}
}
