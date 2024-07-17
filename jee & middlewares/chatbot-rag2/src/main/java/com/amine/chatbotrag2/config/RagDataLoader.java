package com.amine.chatbotrag2.config;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.List;

// @Component
public class RagDataLoader {
    @Value("classpath:/pdfs/cv.pdf")
    private Resource resource;

    @Value("storedata.json")
     private String storeDataFile;

    private JdbcClient jdbcClient;

    private VectorStore vectorStore;
    public RagDataLoader(JdbcClient jdbcClient
                         //, VectorStore vectorStore
    ) {
        this.jdbcClient = jdbcClient;
       this.vectorStore = vectorStore;
    }


    // @Bean

//    public SimpleVectorStore simpleVectorStore(EmbeddingModel model){
//       SimpleVectorStore simpleVectorStore = new SimpleVectorStore(model);
//
//        String storeDataFile = String.valueOf(Path.of("src","main","resources","store").toAbsolutePath()+"/"+this.storeDataFile);
//        File storeFile = new File(storeDataFile);
//        if(!storeFile.exists()){
//            PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(resource);
//            List<Document> documents = pdfReader.get();
//            TextSplitter splitter=new TokenTextSplitter();
//            List<Document> chunks = splitter.split(documents);
//           // simpleVectorStore.accept(chunks);
//          //  simpleVsimpleVectorStore.save(chunks);
////
//        }else {
//            simpleVectorStore.load(storeFile);
//       }
//        return simpleVectorStore;
//
//
//    }

    @PostConstruct
    public void  initStore() {
       Integer count = jdbcClient.sql("select count(*) from vector_store ").query(Integer.class).single();
        if (count==0){
            PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(resource);
            List<Document> documents = pdfReader.get();
            TextSplitter splitter=new TokenTextSplitter();
            List<Document> chunks = splitter.split(documents);
            vectorStore.add(chunks);


        }

    }

}