package com.csmarton.elasticsearch.repository;

import com.csmarton.elasticsearch.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {

    List<User> findByFirstNameLikeAndLastNameLike(String firstName, String lastName);
}
