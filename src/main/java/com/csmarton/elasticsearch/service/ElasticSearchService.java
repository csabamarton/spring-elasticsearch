package com.csmarton.elasticsearch.service;

import com.csmarton.elasticsearch.model.User;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

import java.util.List;

public interface ElasticSearchService {
    List<User> searchByName(String firstName, String lastName);

    NativeSearchQuery buildSearchQuery(String firstName, String lastName);
}
