package jakkins.automdorg.amdo.configurations

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DataSourceConfig {
    private var logger: Logger = LoggerFactory.getLogger(DataSourceConfig::class.java)

    @Bean
    fun dataSource(): DataSource {
        val currentWorkingDirectory = System.getProperty("user.dir")
        logger.info(currentWorkingDirectory)
        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.driverClassName("org.sqlite.JDBC")
        dataSourceBuilder.url("jdbc:sqlite:$currentWorkingDirectory/data.db")
        return dataSourceBuilder.build()
    }
}