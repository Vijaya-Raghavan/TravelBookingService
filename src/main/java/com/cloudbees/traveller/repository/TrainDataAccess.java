package com.cloudbees.traveller.repository;

import com.cloudbees.traveller.repository.entity.Train;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainDataAccess extends CrudRepository<Train, Integer> {


}
