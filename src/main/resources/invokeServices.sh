#!/bin/bash
export MALLOC_ARENA_MAX=2
java -server $JAVA_OPTS -XX:NewRatio=3 -XX:+UseParallelGC -XX:+AggressiveHeap -XX:SoftRefLRUPolicyMSPerMB=10000 -Djava.util.concurrent.ForkJoinPool.common.parallelism=20 -Djava.security.egd=file:/dev/./urandom -jar /app/bin/edi-receiver-service.jar --spring.config.location=/opt/edi-receiver-service/conf/application.yml
