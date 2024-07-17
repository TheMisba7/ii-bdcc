package com.amine.chatbotrag2.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@BrowserCallable
@AnonymousAllowed
public class ChatAiServirce {
    private ChatClient chatClient;

   private VectorStore vectorStore;

    @Value("classpath:/prompts/prompt-template.st")
    private Resource resource;


    public ChatAiServirce(ChatClient.Builder chatClient, VectorStore vectorStore) {

        this.chatClient = chatClient.build();
        this.vectorStore=vectorStore;

    }

    public String regChat(String question)
    {
        List<Document> documents = vectorStore.similaritySearch(question);
        List<String> context = documents.stream().map(Document::getContent).toList();
        PromptTemplate template = new PromptTemplate(resource);
        Prompt prompt = template.create(Map.of("context", context, "question", question));


        return chatClient.prompt(prompt).call().content();
    }
}
