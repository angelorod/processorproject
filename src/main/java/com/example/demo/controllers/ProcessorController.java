package com.example.demo.controllers;

import com.example.demo.models.Processor;
import com.example.demo.services.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.NewProcessorRequest;
@RestController
public class ProcessorController {

    private final ProcessorService processorService;

    @Autowired
    public ProcessorController(ProcessorService processorService) {
        this.processorService = processorService;
    }

    @PostMapping("/processors")
    public Processor saveProcessor(@RequestBody NewProcessorRequest request) {
        return processorService.saveProcessor(request.getUserName(), request.getPassword());
    }
}
