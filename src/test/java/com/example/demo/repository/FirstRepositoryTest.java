package com.example.demo.repository;

import com.example.demo.connection.FirstConnection;
import com.example.demo.domain.Member;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
public class FirstRepositoryTest {

    FirstRepository repository;

    @BeforeEach
    void beforeEach() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(FirstConnection.URL);
        dataSource.setUsername(FirstConnection.USERNAME);
        dataSource.setPassword(FirstConnection.PASSWORD);

        repository = new FirstRepository(dataSource);
    }

    @Test
    void select() {
        Member select = repository.findAll();
        Assertions.assertThat(select.getName()).isEqualTo("first");
    }

}
