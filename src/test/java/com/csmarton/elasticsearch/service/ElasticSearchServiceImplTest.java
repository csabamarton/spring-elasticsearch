package com.csmarton.elasticsearch.service;

import com.csmarton.elasticsearch.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchHitsImpl;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class ElasticSearchServiceImplTest {
    @Mock
    private ElasticsearchOperations elasticsearchOperations;

    private ElasticSearchServiceImpl elasticSearchService;

    @BeforeEach
    public void setup() {
        elasticsearchOperations = mock(ElasticsearchOperations.class);
        elasticSearchService = Mockito.spy(new ElasticSearchServiceImpl(elasticsearchOperations));
    }

    @Test
    public void testSearchByName() {
        // Prepare test data
        String firstName = "John";
        String lastName = "Doe";

        NativeSearchQuery expectedQuery = new NativeSearchQueryBuilder()
                .withPageable(PageRequest.of(0, 10))
                .build();
        doReturn(expectedQuery).when(elasticSearchService).buildSearchQuery(firstName, lastName);

        User user = new User();
        SearchHit<User> searchHit = new SearchHit<>(null, 0f, null, null, user);
        List<SearchHit<User>> searchHitsList = Collections.singletonList(searchHit);
        SearchHits<User> searchHits = new SearchHitsImpl<>(1L, null, 0f, null, searchHitsList, null);
        when(elasticsearchOperations.search(ArgumentMatchers.<org.springframework.data.elasticsearch.core.query.Query>any(), eq(User.class), eq(IndexCoordinates.of("user"))))
                .thenReturn(searchHits);

        // Perform search
        List<User> result = elasticSearchService.searchByName(firstName, lastName);

        // Verify interactions and assertions
        verify(elasticsearchOperations, times(1)).search((Query)any(), eq(User.class), ArgumentMatchers.<IndexCoordinates>any());
    }
}