package or.damo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "or.damo.repo")
public class SpringEsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEsApplication.class, args);
	}
}
