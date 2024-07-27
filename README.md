# Springboot WebMVC Sample Project

---------------------

## Description
Made to figure out how to use java, springboot mvc.

## Environment
- java 11
- intellij ultimate
- windows 10
- docker desktop
- springboot 2.5.8
- gradle 7.3
- mysql 8.0.28

## Sampling What?
- JPA
- QueryDSL
- SQL with META-INF/orm.xml
- AOP with Annotation
- Handle static resource
- Basic Api Response
- ThreadLocal
- Handle custom exception with @ExceptionHandler
- Log4j2
- Utils
- Test with TestContainers
- Docker build
    ```bash
  docker build -t {imageName}:{version} .
  docker run -d -p 8080:8080 {imageName}:{version}
  docker ps
    ```
- Etc...

## Tips
- if you want to set max heap size and min heap size
  ```bash
  # if using intellij, open edit configuration and set VM Option
  # default is 1/4 of the physical memory. (default min is 1/64)
  # if your pc memory is 32GB, default xms is 8GB
  -Xmx512m 
  -Xms256m
  ```
- if you check heap size
  ```bash
  # windows
  java -XX:+PrintFlagsFinal -version | select-string -pattern heapsize
  # linux
  java -XX:+PrintFlagsFinal -version | grep heapsize
  ````  
- if you add java HttpClient logging option
  ```bash
  java -Djdk.httpclient.HttpClient.log=errors,requests,headers,frames[:control:data:window:all],content,ssl,trace,channel,all -jar 
  ```