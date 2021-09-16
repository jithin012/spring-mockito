package com.mclabs.spring.mongo.dbref;

import java.lang.annotation.Annotation;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.mongodb.DBRef;

/**
 * 
 * Lifte-cycle events
 * 
 * Events that application can respond to by registering special beans in the
 * spring application context
 * 
 * Before : onBeforeConvert, onBeforeSave, onBeforeDelete
 * 
 * After: onAfterConvert, onAfterSave, onAfterLoad, onAfterDelete
 * 
 * onBeforeConvert: before the Java object os converted to a document by
 * mongoConverter
 * 
 * onBeforeSave: before inserting or saving the document in db
 * 
 * onAfterSave: after the document was inserted or saved on the db
 * 
 * onAfterLoad: after the document has been retrieved from the db
 * 
 * onAfterConvert: after the document has been converted onto s POJO
 * 
 * 
 * Event Behaviour
 * 
 * Life-cycle events are emitted only for root level types. Sub-document are not
 * subjected to event publication unless they are annotated with @DBRef. it is
 * all happening in an async fashion. We have no guarantees to when an event is
 * processed.
 * 
 * Use - Case
 * 
 * 1. Implement cascade on save
 * 
 * 2. Trigger some job/action in different s/m
 * 
 * 3. Security audit
 *
 *
 */
@Component
public class GenericCascadeListener extends AbstractMongoEventListener<Object> {
	private MongoTemplate mongoTemplate;

	public GenericCascadeListener(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void onBeforeConvert(BeforeConvertEvent event) {
		Object document = event.getSource();
		ReflectionUtils.doWithFields(document.getClass(), docField -> {
			ReflectionUtils.makeAccessible(docField);

			if (docField.isAnnotationPresent((Class<? extends Annotation>) DBRef.class)) {
				final Object fieldValue = docField.get(document);
				System.out.println("fieldValue --->>>>> " + fieldValue);
				// Save child
				this.mongoTemplate.save(fieldValue);
			}
		});
	}
}
