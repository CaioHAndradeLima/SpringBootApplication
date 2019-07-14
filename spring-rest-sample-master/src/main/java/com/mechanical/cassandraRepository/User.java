package com.mechanical.cassandraRepository;

import com.datastax.driver.core.DataType;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table(value = "user")
public class User  {

    @PrimaryKeyColumn(
            name = "uuid",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED,
            ordering = Ordering.DESCENDING
    )
    @CassandraType(type = DataType.Name.UUID)
    private UUID uuid;

    private String name;

    private Integer age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        uuid = UUID.randomUUID();
    }
    public User(UUID uuid, String name, int age) {
        this(name,age);
        this.uuid = uuid;
    }

    public User(){}


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}