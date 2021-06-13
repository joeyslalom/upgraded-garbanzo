package joeyslalom.upgradedgarbanzo

import com.google.api.client.googleapis.json.GoogleJsonError
import com.google.api.client.json.GenericJson
import com.google.api.client.util.ClassInfo
import com.google.api.client.util.Data
import com.google.api.client.util.GenericData
import com.google.api.services.sqladmin.SQLAdmin
import com.google.api.services.sqladmin.SQLAdminRequest
import com.google.api.services.sqladmin.model.AclEntry
import com.google.api.services.sqladmin.model.DatabaseFlags
import com.google.api.services.sqladmin.model.DatabaseInstance
import com.google.api.services.sqladmin.model.IpConfiguration
import com.google.api.services.sqladmin.model.Operation
import com.google.api.services.sqladmin.model.Settings
import com.google.api.services.sqladmin.model.SslCert
import com.google.api.services.sqladmin.model.SslCertsCreateEphemeralRequest
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
        CredentialFactory::class, SocketFactory::class, Driver::class,
        CoreSocketFactory::class, GcpCloudSqlProperties::class]
)
@Configuration
class GcpCloudSqlHint

@TypeHint(
    types = [SQLAdmin::class, SQLAdmin.Operations.Get::class, SQLAdmin.Instances.Get::class,
        Operation::class, DatabaseInstance::class, SQLAdminRequest::class,
        DatabaseFlags::class, Settings::class, SslCert::class,
        AclEntry::class, IpConfiguration::class, SslCertsCreateEphemeralRequest::class]
)
@Configuration
class GcpSqlAdminHint

@TypeHint(
    types = [GenericData::class, GoogleJsonError.ErrorInfo::class, GoogleJsonError::class,
        GenericJson::class, Data::class, ClassInfo::class],
    typeNames = ["com.google.api.client.util.DataMap"]
)
@Configuration
class GcpApiClientHint

@NativeHint(resources = [ResourceHint(patterns = ["com/google/api/client/googleapis/google.jks"])])
@Configuration
class GcpResourcesHint