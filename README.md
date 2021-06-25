# Build setup
We assume you have an [Eureka Server](https://spring.io/guides/gs/service-registration-and-discovery/#initial) running on port 8761
- Spring Native Dependency - mandatory classes + configuration API
- Spring AOT Plugin - improve native image compatibility and footprint;  automatically generating configuration files
- Graal Maven Plugin
- Spring Boot Maven Plugin `classifier` - avoid clash with Graal Maven Plugin during `package` phase
- `<buildArgs>--enable-http --enable-https</buildArgs>`

Now setup is available from [start.spring.io](https://start.spring.io/) with buildpacks support

# Build the image

Use GraalVM:
`sdk use java 21.0.0.2.r11-grl`
`gu install native-image`
`./mvnw package -Pnative-image`

# Run the executable

`./target/io.github.olgamaciaszek.nativeeurekaclient.nativeeurekaclientapplication`

With young generation setup:
`./target/io.github.olgamaciaszek.nativeeurekaclient.nativeeurekaclientapplication -Xmn6M`

A native image, when being executed, does not run on the Java HotSpot VM but on the runtime system provided with GraalVM. That runtime includes all necessary components, and one of them is the memory management. Java objects that a native image allocates at run time reside in the area called “the Java heap”.
 -Xmn - the size of the young generation in bytes

# Test the application

`http GET :8080/services/native-client/instances`

`http GET :8080/services`

# Sample measurements

These were the measurements on my machine

Memory: 185.3 M -> 80.5 M -> 11.7 M (with -Xmn6M)
Startup time: 2.7 s -> 0.326 s

Try it yourself and check on your machine.
