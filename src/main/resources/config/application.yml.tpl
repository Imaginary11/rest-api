datasource:
  primary:
    host: {mysql_host}
    username: {mysql_username}
    password: {mysql_password}
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://${datasource.primary.host}:{mysql_port}/account?characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true
  second:
    host: ${datasource.primary.host}
    username: ${datasource.primary.username}
    password: ${datasource.primary.password}
    driver-class-name: ${datasource.primary.driver-class-name}
    type: ${datasource.primary.type}
    url: jdbc:mysql://${datasource.primary.host}:{mysql_port}/order?characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true

redis:
  cache:
    host: {cache_host}
    port: {cache_port}
    maxAwait: 10000
    maxIdle: 8
    timeout: 5000
    testOnBorrow: true