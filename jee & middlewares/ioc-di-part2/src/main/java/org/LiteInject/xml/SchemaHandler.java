package org.LiteInject.xml;

import generated.Beans;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

;

public class SchemaHandler {
    JAXBContext jaxbContext;

    {
        try {
            jaxbContext = JAXBContext.newInstance(SchemaHandler.class);
            Beans beans = new Beans();

            try {
                jaxbContext.generateSchema(new MySchemaOutputResolver());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }


    private static class MySchemaOutputResolver extends SchemaOutputResolver {
        @Override
        public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
            File file = new File(suggestedFileName);
            StreamResult result = new StreamResult(file);
            result.setSystemId(file.toURI().toURL().toString());
            return result;
        }
    }

}
