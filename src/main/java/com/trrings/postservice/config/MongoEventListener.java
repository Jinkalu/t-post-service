package com.trrings.postservice.config;

import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoEventListener extends AbstractMongoEventListener<Object> {

    @Override
    public void onBeforeSave(BeforeSaveEvent<Object> event) {
        Document document = event.getDocument();
        if (document != null) {
            document.remove("_class");
        }
        super.onBeforeSave(event);
    }
}
