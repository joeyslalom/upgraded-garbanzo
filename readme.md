# Integrations
* Spring Boot
    * Kotlin
    * Gradle
    * Spring Native
* Google Cloud
    * Cloud Run
    * Cloud Build
    * Cloud SQL - MySQL

# How I made this
1. [Spring Initializer](htps://start.spring.io): Kotlin, Web, Actuator, GCP and Native [link](https://start.spring.io/#!type=gradle-project&language=kotlin&platformVersion=2.4.5.RELEASE&packaging=jar&jvmVersion=11&groupId=joeyslalom&artifactId=upgraded-garbanzo&name=upgraded-garbanzo&description=Demo%20project%20for%20Spring%20Boot&packageName=joeyslalom.upgraded-garbanzo&dependencies=native,actuator,web,cloud-gcp)
    * [Note](https://github.com/GoogleCloudPlatform/spring-cloud-gcp/issues/379): this results in a HELP.md file that says:
    ```The following dependency is not known to work with Spring Native: 'GCP Support'. As a result, your application may not work as expected.```
2. Build container image via [Buildpack](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#getting-started-buildpacks)
    * `./gradlew bootBuildImage`
    * Encountered [known memory error building on Mac ](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#_out_of_memory_error_when_building_the_native_image)
   * `docker run upgraded-garbanzo:0.0.1-SNAPSHOT -p 8080:8080`
3. Integrate with [Cloud Build](https://cloud.google.com/build/docs/building/build-containers)     
    * `gcloud builds submit --substitutions REPO_NAME=upgraded-garbanzo,COMMIT_SHA=$(git rev-parse HEAD)`
4. Use Spring Boot Actuators for health and info 
5. Normally, I'd use `spring-cloud-gcp-starter-logging` for structured logging
     * Currently disabled: [Can't use logback-spring.xml](https://github.com/spring-projects-experimental/spring-native/issues/625)
6. Builds okay, but fails on start on Cloud Run.
     * `BeanDefinitionStoreException` resolved with `@TypeHint`
     * `BeanInstantiationException` resolved with argument to `native-image`
7. Disabled disk health indicator so health check won't return status `DOWN`
8. Cloud Run has an option for "Continuous Deployment", but not with my configuration (iconically, cloudbuild.yaml).
     * Add multiple `steps` in my cloudbuild.yaml
     * Enable [Cloud Run Admin](https://cloud.google.com/build/docs/deploying-builds/deploy-cloud-run#required_iam_permissions)
     * Update an [iam permission](https://cloud.google.com/build/docs/deploying-builds/deploy-cloud-run#continuous-iam)
9. Cloud SQL
    * Create Cloud SQL database
      * Put database password in Secret Manager
    * Update IAM, add Cloud Run service account to roles/cloudsql.client
    * In Cloud Run:
      * Reference Secret Manager password as Environment Variable
      * Expose database and instance connection name as Environment Variables
      * Add "Cloud SQL connection" to instance
    * Add `spring-cloud-gcp-starter-sql-mysql` dependency
    * Consume Environment Variables in application.properties
    * https://github.com/GoogleCloudPlatform/spring-cloud-gcp/tree/main/spring-cloud-gcp-samples/spring-cloud-gcp-sql-mysql-sample