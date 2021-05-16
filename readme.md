
# How I made this
1. [Spring Initializer](htps://start.spring.io): Kotlin, Web, Actuator, GCP and Native [link](https://start.spring.io/#!type=gradle-project&language=kotlin&platformVersion=2.4.5.RELEASE&packaging=jar&jvmVersion=11&groupId=joeyslalom&artifactId=upgraded-garbanzo&name=upgraded-garbanzo&description=Demo%20project%20for%20Spring%20Boot&packageName=joeyslalom.upgraded-garbanzo&dependencies=native,actuator,web,cloud-gcp)
    * [Note](https://github.com/GoogleCloudPlatform/spring-cloud-gcp/issues/379): this results in a HELP.md file that says:
    ```The following dependency is not known to work with Spring Native: 'GCP Support'. As a result, your application may not work as expected.```
2. The [Buildpack](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#getting-started-buildpacks) doesn't work: `./gradlew bootBuildImage` produces this [output](build1.log)
    * Failed with known [status code 145](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#_builder_lifecycle_creator_failed_with_status_code_145)
    * Failed with known [exit status 137](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#_out_of_memory_error_when_building_the_native_image)
    * As this is Docker on Mac, looks like it doesn't have enough memory.  So, running this command on Google Cloud Build.
3.     