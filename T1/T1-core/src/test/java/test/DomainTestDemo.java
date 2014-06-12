package test;

import org.dayatang.domain.EntityRepository;
import org.dayatang.domain.InstanceFactory;
import org.junit.Test;
import org.openkoala.koala.auth.core.domain.User;
import org.openkoala.koala.util.KoalaBaseSpringTestCase;

/**
 * 领域层的测试类，直接继承KoalaBaseSpringTestCase
 * @author lingen
 *
 */
public class DomainTestDemo extends KoalaBaseSpringTestCase {

	@Test
	public void test(){
		//在这里，你可以直接使用InstanceFactory获取各种spring的bean
		EntityRepository repository = InstanceFactory.getInstance(EntityRepository.class);
		//同样，你可以直接调用领域类的领域方法
		//User.addUser("koala");
		
	}
	
}