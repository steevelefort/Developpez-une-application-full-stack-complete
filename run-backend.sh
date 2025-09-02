#!/bin/sh
cd back
mvn clean compile
# JWT_PRIVATE_KEY="`cat ./keys/private.pem`" JWT_PUBLIC_KEY="`cat ./keys/public.pem`" mvn spring-boot:run
DB_USERNAME=root DB_PASSWORD=root DB_HOST=jdbc:mysql://localhost:3306/mdd JWT_PRIVATE_KEY="`cat ./keys/private.pem`" JWT_PUBLIC_KEY="`cat ./keys/public.pem`" FRONTEND_URL="http://localhost:4200" mvn spring-boot:run
