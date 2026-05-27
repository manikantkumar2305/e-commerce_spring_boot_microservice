package com.ecommerce.searchservice.config;

import com.ecommerce.searchservice.tool.SearchTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MCPConfig {

//    no use off these bean

    @Bean
    ToolCallbackProvider toolCallbackProvider(SearchTools searchTools){
        return MethodToolCallbackProvider.builder()
                .toolObjects(searchTools)
                .build();
    }

}
