package ru.karelin.tmserver.util;

import lombok.SneakyThrows;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import ru.karelin.tmserver.repository.ProjectRepositoryBatis;
import ru.karelin.tmserver.repository.TaskRepositoryBatis;

import javax.sql.DataSource;
import java.util.Properties;

public class BatisInit {
    @SneakyThrows
    public static SqlSessionFactory getSqlSessionFactory() {
        Properties properties = new Properties();
        properties.load(BatisInit.class.getResourceAsStream("/db.properties"));
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        final DataSource dataSource =
                new PooledDataSource(driver, url, properties);
        final TransactionFactory transactionFactory =
                new JdbcTransactionFactory();
        final Environment environment =
                new Environment("development", transactionFactory, dataSource);
        final Configuration configuration = new Configuration(environment);
        configuration.addMapper(ProjectRepositoryBatis.class);
        configuration.addMapper(TaskRepositoryBatis.class);
        return new SqlSessionFactoryBuilder().build(configuration);

    }


}
