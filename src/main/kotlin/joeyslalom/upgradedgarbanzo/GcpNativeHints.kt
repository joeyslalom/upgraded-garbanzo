package joeyslalom.upgradedgarbanzo

import com.google.cloud.spring.autoconfigure.sql.CloudSqlEnvironmentPostProcessor
import org.springframework.context.annotation.Configuration
import org.springframework.nativex.hint.TypeHint

@TypeHint(types = [CloudSqlEnvironmentPostProcessor::class])
@Configuration
class GcpAutoConfigurationHint