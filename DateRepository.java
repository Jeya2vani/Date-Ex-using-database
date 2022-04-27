package com.example.springmongodb.repository;

import com.example.springmongodb.entity.Date;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("dateRepository")
public interface DateRepository extends MongoRepository<Date,String> {

}
