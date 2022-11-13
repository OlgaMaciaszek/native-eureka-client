# Build setup

We assume you have an [Eureka Server](https://spring.io/guides/gs/service-registration-and-discovery/#initial) running
on port 8761

Now setup is available from [start.spring.io](https://start.spring.io/) with buildpacks support

# Build the image

Use GraalVM with SDKMan:
`sdk install java 22.3.r17-nik`
`sdk use java 22.3.r17-nik`
`./mvnw native:compile -Pnative`
(not working)

`mvn spring-boot:build-image -Pnative`

# Run the executable

`./target/native-eureka-client`

With young generation setup:
`./target/native-eureka-client -Xmn6M`

A native image, when being executed, does not run on the Java HotSpot VM but on the runtime system provided with
GraalVM. That runtime includes all necessary components, and one of them is the memory management. Java objects that a
native image allocates at run time reside in the area called “the Java heap”.
-Xmn - the size of the young generation in bytes

# Test the application

`http GET :8080/services/native-client/instances`

`http GET :8080/services`

# Sample measurements

These were the measurements on my machine

Memory: 196.7 MB -> 85.6 MB -> 12.3 MB (with -Xmn6M)
Startup time: 2.4 s -> 0.261 s

Try it yourself and check on your machine.
