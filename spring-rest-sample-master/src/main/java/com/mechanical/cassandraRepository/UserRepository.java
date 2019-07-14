package com.mechanical.cassandraRepository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {
/*
    @AllowFiltering
    List<User> findByAgeGreaterThan(int age);
*/
}
