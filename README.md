showcasing the usage of JDK21-VirtualThreads from Kotlin

Notice that the Kotlin version used is 1.9.20. We need this high of a version since Kotlin 1.9.20-Beta1 was the first
version with a JvmTarget for the JDK 21, so older Kotlin versions do not run on the JDK 21 backend.

You need JDK21 installed on your machine to run this code, since I deactivated auto-provisioning by gradle.

### How to build and execute to project

```shell
./gradlew clean build & java -jar ./build/libs/vthreads_with_kotlin_demo-1.0-SNAPSHOT.jar
```
make sure the local JVM has a JDK version >=21.