package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.FirstRepository;
import com.example.demo.repository.SecondRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
@Component
public class Service {

    private final FirstRepository firstRepository;
    private final SecondRepository secondRepository;

    public void logic(){
        try {
            Member first = firstRepository.findAll();
            firstRepository.update(first.getName() + "New", first.getId());
            Member second = secondRepository.findAll();
            secondRepository.update(second.getName()+"New", second.getId());

            firstRepository.update(first.getName() + "New2", first.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
