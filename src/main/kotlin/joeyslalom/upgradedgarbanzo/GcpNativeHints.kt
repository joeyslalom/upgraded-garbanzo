package joeyslalom.upgradedgarbanzo

import com.google.api.client.googleapis.json.GoogleJsonError
import com.google.api.client.util.GenericData
import com.google.auth.oauth2.ServiceAccountCredentials
import com.google.auth.oauth2.ServiceAccountJwtAccessCredentials
import com.google.cloud.spring.autoconfigure.core.GcpProperties
import com.google.cloud.spring.autoconfigure.datastore.GcpDatastoreEmulatorAutoConfiguration
import com.google.cloud.spring.autoconfigure.sql.CloudSqlEnvironmentPostProcessor
import com.google.cloud.spring.autoconfigure.sql.GcpCloudSqlProperties
import com.google.cloud.sql.CredentialFactory
import com.google.cloud.sql.core.CoreSocketFactory
import com.google.cloud.sql.mysql.SocketFactory
import com.mysql.cj.jdbc.Driver
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.nativex.hint.NativeHint
import org.springframework.nativex.hint.ResourceHint
import org.springframework.nativex.hint.TypeHint
import javax.sql.DataSource


@TypeHint(types = [GcpDatastoreEmulatorAutoConfiguration::class])
@Configuration
class GcpAutoConfigurationHint

@TypeHint(
    types = [CloudSqlEnvironmentPostProcessor::class, DataSource::class, EmbeddedDatabaseType::class,
        CredentialFactory::class, SocketFactory::class, Driver::class, CoreSocketFactory::class,
        GcpCloudSqlProperties::class]
)
@Configuration
class GcpCloudSqlHint

@TypeHint(
    types = [GenericData::class, GoogleJsonError.ErrorInfo::class, GoogleJsonError::class,
        ServiceAccountCredentials::class, ServiceAccountJwtAccessCredentials::class]
)
@Configuration
class GcpApiClientHint

@NativeHint(resources = [ResourceHint(patterns = ["com/google/api/client/googleapis/google.jks"])])
@Configuration
class GcpResourcesHint