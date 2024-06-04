showcasing the usage of JDK21-VirtualThreads from Kotlin

Notice that the Kotlin version used is 2.0.0. We need at least version 1.9.20-Beta1 since that was the first
version with a JvmTarget for the JDK 21, which is in turn the first JDK version with support for virtual threads.

You need JDK21 installed on your machine to run this code, since I deactivated auto-provisioning by gradle.

### How to build and execute to project

```shell
./gradlew clean build & java -jar ./build/libs/vthreads_with_kotlin_demo-1.0-SNAPSHOT.jar
```
make sure the local JVM has a JDK version >=21.