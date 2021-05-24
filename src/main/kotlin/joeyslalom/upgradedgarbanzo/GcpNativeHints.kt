package joeyslalom.upgradedgarbanzo

import com.google.cloud.spring.autoconfigure.datastore.GcpDatastoreEmulatorAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.nativex.hint.TypeHint

@TypeHint(types = [GcpDatastoreEmulatorAutoConfiguration::class])
@Configuration
class GcpDatastoreEmulatorAutoConfigurationHint