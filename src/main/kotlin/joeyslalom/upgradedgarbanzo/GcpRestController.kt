package joeyslalom.upgradedgarbanzo

import com.google.api.gax.core.CredentialsProvider
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.info.GitProperties
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GcpRestController(private val credentialsProvider: CredentialsProvider) {
    private val log = LoggerFactory.getLogger(GcpRestController::class.java)

    @GetMapping("/gcp-creds")
    fun gcpCreds(): GcpCreds {
        with(credentialsProvider.credentials) {
            log.info("authType=${this.authenticationType}")
            log.info("requestMetadataKeys=${this.requestMetadata.keys}")
            log.info("requestMetadata=${this.requestMetadata}")
            return GcpCreds(authenticationType, emptyMap())
        }
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