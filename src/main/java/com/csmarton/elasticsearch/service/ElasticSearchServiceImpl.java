package com.csmarton.elasticsearch.service;

import com.csmarton.elasticsearch.model.User;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private final ElasticsearchOperations elasticsearchOperations;

    private static final String USER_INDEX = "user";

    @Override
    public List<User> searchByName(String firstName, String lastName) {
        NativeSearchQuery query = buildSearchQuery(firstName, lastName);
        SearchHits<User> searchHits = elasticsearchOperations.search(query, User.class, IndexCoordinates.of(USER_INDEX));
        if (searchHits == null)
            return List.of();

        return searchHits.map(SearchHit::getContent).toList();
    }

    @Override
    public NativeSearchQuery buildSearchQuery(String firstName, String lastName) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .should(QueryBuilders.wildcardQuery("firstname", "*" + firstName.toLowerCase() + "*"))
                .should(QueryBuilders.wildcardQuery("lastname", "*" + lastName.toLowerCase() + "*"));

        return new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .withPageable(PageRequest.of(0, 10))
                .build();
    }
}
