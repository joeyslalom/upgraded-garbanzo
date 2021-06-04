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
    fun gcpCreds() {
        log.info("credentials=${credentialsProvider.credentials}")
    }
}

@Component
class LogVersion(private val gitProperties: GitProperties) : InitializingBean {
    private val log = LoggerFactory.getLogger(LogVersion::class.java)

    override fun afterPropertiesSet() {
        log.info("initialized with git commit sha=${gitProperties.shortCommitId}")
    }
}