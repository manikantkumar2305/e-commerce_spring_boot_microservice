package com.ecommerce.searchservice.service;

import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import com.ecommerce.searchservice.document.Search;
import com.ecommerce.searchservice.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

//    private final SearchRepository searchRepository;

    /*public List<Search> search(String keyword){
        return searchRepository.findByNameContainingIgnoreCase(keyword);
    }*/

    private final ElasticsearchOperations elasticsearchOperations;

    public List<Search> search(String keyword){
        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q
                        .multiMatch(m -> m
                                .query(keyword)
                                .fields(
                                        "name",
                                        "description",
                                        "category"
                                )
                                .fuzziness("AUTO")
//                                .operator(Operator.Or)
                        )
                ).build();

        return elasticsearchOperations
                .search(query,Search.class)
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }

}
