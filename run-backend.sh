#!/bin/sh
cd back
mvn clean compile
JWT_PRIVATE_KEY="`cat ./keys/private.pem`" JWT_PUBLIC_KEY="`cat ./keys/public.pem`" mvn spring-boot:run
