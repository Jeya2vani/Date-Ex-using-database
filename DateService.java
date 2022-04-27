package com.example.springmongodb.service;

import com.example.springmongodb.dto.DateResponse;
import com.example.springmongodb.entity.Date;
import com.example.springmongodb.repository.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DateService {
    @Autowired
    private DateRepository dateRepository;

    public List<DateResponse> getDate(){
        List<Date> dates=dateRepository.findAll();
        Date date=getNonOverlap(dates);
        dates=dates.stream().map(x->date).collect(Collectors.toList());
        return dates.stream().map(this::getAll).collect(Collectors.toList());
    }
    public DateResponse getAll(Date date)
    {
        DateResponse dateResponse=new DateResponse();
        dateResponse.setStartDate1(date.getStartDate1());
        dateResponse.setEndDate1(date.getEndDate1());
        dateResponse.setStartDate2(date.getStartDate2());
        dateResponse.setEndDate2(date.getEndDate2());
        return dateResponse;
    }
    public Date getNonOverlap(List<Date> dates){
        //dates= Collections.singletonList((Date) dates.stream().map(this::getAll).collect(Collectors.toList()));
        Date date = (Date) dates.stream().map(this::getAll).collect(Collectors.toList());
        java.util.Date s1= ((Date) dates).getStartDate1();
        java.util.Date e1= ((Date) dates).getEndDate1();
        java.util.Date s2= ((Date) dates).getStartDate2();
        java.util.Date e2= ((Date) dates).getEndDate2();
        java.util.Date e=e1;
        java.util.Date n=s1;
        java.util.Date t=s2;
        java.util.Date v=e2;
        if(s1.before(s2) && e1.after(s2) && e1.before(e2))
        {
            e1=t;
            s2=e;
            date.setStartDate1(s1);
            date.setEndDate1(e1);
            date.setStartDate2(s2);
            date.setEndDate2(e2);
            return date;
        }
        if(s1.before(e2) && e1.after(e2) && s2.before(s1))
        {
            s1=t;
            s2=v;
            e2=e;
            e1=n;
            date.setStartDate1(s1);
            date.setEndDate1(e1);
            date.setStartDate2(s2);
            date.setEndDate2(e2);
            return date;
        }
        if(s1.before(s2) && e1.after(e2))
        {
            s2=v;
            e2=e;
            e1=t;
            date.setStartDate1(s1);
            date.setEndDate1(e1);
            date.setStartDate2(s2);
            date.setEndDate2(e2);
            return date;
        }
        if(s1.after(s2) && e1.before(e2))
        {
            s2=e;
            s1=t;
            e1=n;
            date.setStartDate1(s1);
            date.setEndDate1(e1);
            date.setStartDate2(s2);
            date.setEndDate2(e2);
            return date;
        }
        return date;
        /*if(s1.before(s2) && e1.before(s2) && e1.before(e2) && s1.before(e2))
        {
            date.setStartDate1(s1);
            date.setEndDate1(e1);
            date.setStartDate2(s2);
            date.setEndDate2(e2);
            return date;
        }*/
        /*else {
            return date;
        }*/
    }
}
