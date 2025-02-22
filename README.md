# 适配达梦数据库V8

修改application.properties下的数据库配置

```properties
### xxl-job, datasource
spring.datasource.url=jdbc:dm://127.0.0.1:5236?schema=XXL_JOB
spring.datasource.username=SYSDBA
spring.datasource.password=SYSDBA
spring.datasource.driver-class-name=dm.jdbc.driver.DmDriver
```

# Documentation

- [中文文档](https://www.xuxueli.com/xxl-job/)
- [English Documentation](https://www.xuxueli.com/xxl-job/en/)

