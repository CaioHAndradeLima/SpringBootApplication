package com.mechanical.cassandraRepository.repository;

import com.mechanical.cassandraRepository.model.UserCassandraModel;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<UserCassandraModel, UUID> {

    @AllowFiltering
    @Nullable
    UserCassandraModel findByEmail(String email);

    @AllowFiltering
    @Nullable
    UserCassandraModel findBycpf(String cpf);
/*
    @AllowFiltering
    List<User> findByAgeGreaterThan(int age);
*/
}
