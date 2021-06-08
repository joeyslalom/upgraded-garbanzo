package joeyslalom.upgradedgarbanzo

import com.google.cloud.spring.autoconfigure.datastore.GcpDatastoreEmulatorAutoConfiguration
import com.google.cloud.spring.autoconfigure.sql.CloudSqlEnvironmentPostProcessor
import com.google.cloud.sql.CredentialFactory
import com.google.cloud.sql.core.CoreSocketFactory
import com.google.cloud.sql.mysql.SocketFactory
import com.mysql.cj.jdbc.Driver
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.nativex.hint.TypeHint
import javax.sql.DataSource


@TypeHint(types = [GcpDatastoreEmulatorAutoConfiguration::class])
@Configuration
class GcpAutoConfigurationHint

@TypeHint(
    types = [CloudSqlEnvironmentPostProcessor::class, DataSource::class, EmbeddedDatabaseType::class,
        CredentialFactory::class, SocketFactory::class, Driver::class, CoreSocketFactory::class
    ]
)
@Configuration
class GcpCloudSqlHint