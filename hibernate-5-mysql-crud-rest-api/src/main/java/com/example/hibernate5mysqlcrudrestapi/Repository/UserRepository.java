package com.example.hibernate5mysqlcrudrestapi.Repository;

import com.example.hibernate5mysqlcrudrestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
