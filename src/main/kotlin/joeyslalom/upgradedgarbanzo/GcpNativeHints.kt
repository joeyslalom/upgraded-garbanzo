package joeyslalom.upgradedgarbanzo

import com.google.cloud.spring.autoconfigure.datastore.GcpDatastoreEmulatorAutoConfiguration
import com.google.cloud.spring.autoconfigure.sql.CloudSqlEnvironmentPostProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.nativex.hint.TypeHint


@TypeHint(types = [CloudSqlEnvironmentPostProcessor::class, GcpDatastoreEmulatorAutoConfiguration::class])
@Configuration
class GcpAutoConfigurationHint {
    @Bean
    fun cloudSql() = CloudSqlEnvironmentPostProcessor()
}