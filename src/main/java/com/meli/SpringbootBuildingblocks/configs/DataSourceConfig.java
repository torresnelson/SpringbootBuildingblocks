package com.meli.SpringbootBuildingblocks.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Data source Configuration.
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

  private static final Integer MAX_POOL_SIZE = 20;

  private static final Integer MIN_IDLE = 2;

  private static final String DB_HOST = "db.host";

  private static final String DB_NAME = "db.name";

  private static final String DB_USERNAME = "db.username";

  private static final String DB_PASSWORD = "db.password";

  private static final String DB_DRIVER_NAME = "db.driver.name";

  private static final String DB_URL = "db.url";

  private final Environment env;

  @Autowired
  public DataSourceConfig(Environment env) {
    this.env = env;
  }

  @Bean
  @Primary
  @Profile({"local"})
  public DataSource localDataSource() {
    final String host = env.getProperty(DB_HOST);
    final String db = env.getProperty(DB_NAME);
    final String username = env.getProperty(DB_USERNAME);
    final String password = env.getProperty(DB_PASSWORD);
    final String driverName = env.getProperty(DB_DRIVER_NAME);
    final String dbUrl = String.format(env.getProperty(DB_URL), host, db);

    return getDataSource(username, password, driverName, dbUrl);
  }

  private DataSource getDataSource(String username, String password, String driverName, String dbUrl) {
    HikariConfig config = new HikariConfig();
    config.setMaximumPoolSize(MAX_POOL_SIZE);
    config.setMinimumIdle(MIN_IDLE);
    config.setUsername(username);
    config.setPassword(password);
    config.setJdbcUrl(dbUrl);
    config.setDriverClassName(driverName);

    return new HikariDataSource(config);
  }
}
