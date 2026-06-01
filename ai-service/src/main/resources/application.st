spring.application.name=ai-service
server.port=7070

spring.config.import=optional:configserver:http://localhost:8888

eureka.client.service-url.defaultZone=http://localhost:8761/eureka
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.instance.ip-address=127.0.0.1

spring.ai.chat.client.enabled=true
spring.ai.mcp.client.name=search-client
spring.ai.mcp.client.streamable-http.connections.search-service.url=http://127.0.0.1:9099
spring.ai.mcp.client.streamable-http.connections.search-service.endpoint=/mcp

spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.chat.model=llama3.2:3b
spring.ai.retry.max-attempts=1
spring.ai.ollama.init.timeout=5m