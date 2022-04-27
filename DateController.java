package com.example.springmongodb.controller;

import com.example.springmongodb.dto.DateResponse;
import com.example.springmongodb.repository.DateRepository;
import com.example.springmongodb.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("DateDetails")
public class DateController {
    @Autowired
    public DateService dateService;

    @GetMapping("/findAll")
    public List<DateResponse> getAns()
    {
        return (List<DateResponse>) dateService.getDate();
    }
}
