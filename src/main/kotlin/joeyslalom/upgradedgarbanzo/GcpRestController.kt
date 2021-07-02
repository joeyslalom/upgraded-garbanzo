package joeyslalom.upgradedgarbanzo

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.info.GitProperties
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.ResultSet

@RestController
class UserRestController(private val repo: UserRepo, private val jdbcTemplate: JdbcTemplate) {
    private val log = LoggerFactory.getLogger(UserRestController::class.java)

    @GetMapping("/users")
    fun users(): List<User> = repo.findAll().toList()

    @GetMapping("/jdbcUsers")
    fun jdbcUsers(): List<User> = jdbcTemplate.query("SELECT email, first_name, last_name FROM users", userMapper)
}

@Table("users")
data class User(val email: String, val firstName: String, val lastName: String)

@Repository
interface UserRepo : CrudRepository<User, String>

val userMapper = RowMapper<User> { rs: ResultSet, _ ->
    User(rs.getString("email"), rs.getString("first_name"), rs.getString("last_name"))
}

//@Repository
//class UserJdbcRepo(private val jdbcTemplate: JdbcTemplate) {
//
//    fun findAll(): List<User> {
//        return jdbcTemplate.query("SELECT email, first_name, last_name FROM users", userMapper)
//    }
//}

@Component
class LogVersion(private val gitProperties: GitProperties) : InitializingBean {
    private val log = LoggerFactory.getLogger(LogVersion::class.java)

    override fun afterPropertiesSet() {
        log.info("initialized with git commit sha=${gitProperties.shortCommitId}")
    }
}