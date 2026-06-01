package com.ecommerce.aiservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
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
    private final VectorStore vectorStore;

    @Value("classpath:/prompts/SearchSystemPrompt.st")
    private Resource systemResource;

    @Value("classpath:/prompts/VectorSystemPrompt.st")
    private Resource vectorResource;

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

    public String vectorSearch(String query){
        return chatClient
                .prompt()
                .system(vectorResource)
                .advisors(QuestionAnswerAdvisor.builder(vectorStore).build())
                .user(query)
                .call()
                .content();
    }


}
