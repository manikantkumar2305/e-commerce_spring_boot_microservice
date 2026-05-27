package com.ecommerce.searchservice.tool;

import com.ecommerce.searchservice.DTO.SearchResponse;
import com.ecommerce.searchservice.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class SearchTools {

    private final SearchService searchService;

    @Tool(description = "Use this tool to search for product")
    public List<SearchResponse> searchProduct(@ToolParam(description = "keyword use to search for the product") String keyword){
        log.info("MCP tool called with Query {}" , keyword);
        return searchService.search(keyword).stream()
                .map(SearchResponse::from)
                .toList();

    }
}
