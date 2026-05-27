package com.ecommerce.aiservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ChatClient chatClient;
    private final ToolCallbackProvider tools;

    @Value("classpath:/prompts/SearchSystemPrompt.st")
    private Resource systemResource;

    public String searchResponse(String query){
//        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemResource);

        return chatClient
                .prompt()
                .system(systemResource)
                .user(query)
                .toolCallbacks(tools)
//                .advisors(new SimpleLoggerAdvisor())
                .call()
                .content();
    }

}
