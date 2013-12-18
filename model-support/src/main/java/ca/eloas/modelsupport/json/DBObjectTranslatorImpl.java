package ca.eloas.modelsupport.json;

import ca.eloas.restsupport.ObjectFactory;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.shared.Splittable;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import javax.inject.Inject;

/**
 * @author JP
 */
public class DBObjectTranslatorImpl implements DBObjectTranslator {

    @Override
    public DBObject toDBObject(Object o) {

        Splittable s = AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(o));
        return (DBObject) JSON.parse(s.asString());
    }

    @Override
    public <T> T toObject(Class<T> type, DBObject dbObject) {
        return (T) AutoBeanCodex.decode(AutoBeanFactorySource.create(DBObjectAutoBeanFactory.class), type, dbObject.toString()).as();
    }
}
