package eafc.peruwelz.miniprojet.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("eafc.peruwelz.miniprojet.domain")
@EnableJpaRepositories("eafc.peruwelz.miniprojet.repos")
@EnableTransactionManagement
public class DomainConfig {
}
