package com.ecommerce.aiservice.service;

import com.ecommerce.aiservice.kafka.ProductCreatedEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VectorStoreService {

    private final VectorStore vectorStore;

    public void store(ProductCreatedEventDTO product){
        String content = """
            Product Name: %s
            
            This product is called %s.
            
            Description:
            %s
            
            Category:
            %s
            
            The product belongs to the %s category and is available for purchase at a price of ₹%s.
            
            Customers looking for %s products may find this item useful.
            
            Key Information:
            - Product Name: %s
            - Category: %s
            - Price: ₹%s
            """.formatted(
                            product.getName(),
                            product.getName(),
                            product.getDescription(),
                            product.getCategory(),
                            product.getCategory(),
                            product.getPrice(),
                            product.getCategory(),
                            product.getName(),
                            product.getCategory(),
                            product.getPrice()
                    );

        Document document = new Document(content , Map.of(
                "productId", product.getId().toString(),
                "name", product.getName(),
                "category", product.getCategory(),
                "price", product.getPrice().toString()
        ));

        vectorStore.add(List.of(document));

    }

}
