package or.damo;

import org.flybug.util.transform.TransForm;
import org.flybug.util.transform.impl.SimpleTransForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "or.damo")
public class SpringEsApplication implements EnvironmentAware{

	private Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment=environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringEsApplication.class, args);
	}

	@Bean(name = "product-id")
	public RedisAtomicLong redisAtomicLong(RedisConnectionFactory redisConnectionFactory){
		RedisAtomicLong redisAtomicLong = new RedisAtomicLong(environment.getProperty("product.id.key","product:id"),redisConnectionFactory);
		return  redisAtomicLong;
	}

	@Bean
	public TransForm transForm(){
		SimpleTransForm transForm = new SimpleTransForm();
		return transForm;
	}
}
