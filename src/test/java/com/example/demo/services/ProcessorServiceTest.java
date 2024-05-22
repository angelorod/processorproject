package com.example.demo.services;

import com.example.demo.models.Processor;
import com.example.demo.models.User;
import com.example.demo.repositories.ProcessorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DataJpaTest
@Transactional
public class ProcessorServiceTest {

    @Autowired
    private ProcessorRepository processorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ProcessorService processorService;

    @Test
    @Rollback(true)
    void testSaveProcessor() {
        // Arrange
        String userName = "testUser";
        String password = "testPassword";

        // Act
        Processor processor = processorService.saveProcessor(userName, password);

        // Assert
        assertNotNull(processor);
        assertNotNull(processor.getId());
        assertEquals(userName, processor.getName());

        User user = processor.getStaff().get(0);
        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals(userName, user.getUserName());
        assertTrue(passwordEncoder.matches(password, user.getPassword()));

        // Verify that the processor and user are persisted to the database
        Processor persistedProcessor = processorRepository.findById(processor.getId()).orElse(null);
        assertNotNull(persistedProcessor);
        assertEquals(userName, persistedProcessor.getName());

        User persistedUser = persistedProcessor.getStaff().get(0);
        assertNotNull(persistedUser);
        assertEquals(userName, persistedUser.getUserName());
        assertTrue(passwordEncoder.matches(password, persistedUser.getPassword()));
    }

    @Test
    @Rollback(true)
    void testDeleteProcessor() {
        // Arrange
        String userName = "testUser";
        String password = "testPassword";

        Processor processor = processorService.saveProcessor(userName, password);

        // Act
        processorService.deleteProcessor(processor.getId());

        // Assert
        Processor deletedProcessor = processorRepository.findById(processor.getId()).orElse(null);
        assertEquals(null, deletedProcessor);
    }
}