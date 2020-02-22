package com.mechanical.cassandraRepository.repository;

import com.mechanical.cassandraRepository.model.WorkerCassandraModel;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkerRepository extends CassandraRepository<WorkerCassandraModel, UUID> {

    @AllowFiltering
    @Nullable
    WorkerCassandraModel findByEmail(String email);

/*
    @AllowFiltering
    List<WorkerSession> findByAgeGreaterThan(int age);
*/
}
