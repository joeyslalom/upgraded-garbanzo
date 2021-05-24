package joeyslalom.upgradedgarbanzo

import com.google.api.gax.core.CredentialsProvider
import com.google.cloud.spring.core.GcpEnvironmentProvider
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.info.GitProperties
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GcpRestController(
    private val credentialsProvider: CredentialsProvider,
    private val environmentProvider: GcpEnvironmentProvider
) {

    @GetMapping("/gcp-creds")
    fun gcpCreds(): GcpCreds {
        with(credentialsProvider.credentials) {
            return GcpCreds(authenticationType, requestMetadata)
        }
    }

    @GetMapping("/gcp-env")
    fun gcpEnv(): String {
        return environmentProvider.currentEnvironment.name
    }
}

data class GcpCreds(val authType: String, val metadata: Map<String, List<String>>)

@Component
class LogVersion(private val gitProperties: GitProperties) : InitializingBean {
    private val log = LoggerFactory.getLogger(LogVersion::class.java)

    override fun afterPropertiesSet() {
        log.info("initialized with git commit sha=${gitProperties.shortCommitId}")
    }
}