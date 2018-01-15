package crud.dao.hibernate;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ImportResource({ "classpath:hibernate.cfg.xml" })
public class HibernateConfig {
   
} 
