package or.damo;

import or.damo.entity.Product;
import or.damo.es.ProductESRepository;
import or.damo.vo.ProductVo;
import org.flybug.util.transform.TransForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringEsApplication.class)
public class SpringEsApplicationTests {


	@Autowired
	protected  TransForm transForm;
	@Autowired
	protected ProductESRepository esRepository;


	@Test
	public void contextLoads() {
		Optional<Product> byId = esRepository.findById(1);
		Product product = byId.get();

		ProductVo productVo= new ProductVo();
		 transForm.fill(byId, productVo);
		System.out.println(product);
		System.out.println(productVo);
	}


}
