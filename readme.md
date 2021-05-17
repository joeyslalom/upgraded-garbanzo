
# How I made this
1. [Spring Initializer](htps://start.spring.io): Kotlin, Web, Actuator, GCP and Native [link](https://start.spring.io/#!type=gradle-project&language=kotlin&platformVersion=2.4.5.RELEASE&packaging=jar&jvmVersion=11&groupId=joeyslalom&artifactId=upgraded-garbanzo&name=upgraded-garbanzo&description=Demo%20project%20for%20Spring%20Boot&packageName=joeyslalom.upgraded-garbanzo&dependencies=native,actuator,web,cloud-gcp)
    * [Note](https://github.com/GoogleCloudPlatform/spring-cloud-gcp/issues/379): this results in a HELP.md file that says:
    ```The following dependency is not known to work with Spring Native: 'GCP Support'. As a result, your application may not work as expected.```
2. Build container image via [Buildpack](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#getting-started-buildpacks)
    * `./gradlew bootBuildImage`
    * Encountered [known memory error building on Mac ](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#_out_of_memory_error_when_building_the_native_image)
3. Integrate with [Cloud Build](https://cloud.google.com/build/docs/building/build-containers)     
    * `gcloud builds submit --substitutions REPO_NAME=upgraded-garbanzo,COMMIT_SHA=$(git rev-parse HEAD)`
4. Use Spring Boot Actuators for health and info 
5. Include `spring-cloud-gcp-starter-logging` for structured logging
6. Can't use logback-spring.xml [issue](https://github.com/spring-projects-experimental/spring-native/issues/625)