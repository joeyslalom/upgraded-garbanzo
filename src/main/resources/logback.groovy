import com.google.cloud.spring.logging.StackdriverJsonLayout

//def projectId = "${projectId:-${SPRING_CLOUD_GCP_LOGGING_PROJECT_ID}}"
appender("CONSOLE_JSON", ConsoleAppender) {
    encoder(LayoutWrappingEncoder) {
        layout(StackdriverJsonLayout) {
            //projectId = "${projectId}"
        }
    }
}
root(INFO, ["CONSOLE_JSON"])