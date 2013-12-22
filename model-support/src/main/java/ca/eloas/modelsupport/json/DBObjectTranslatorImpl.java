package ca.eloas.modelsupport.json;

import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.shared.Splittable;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;


/**
 * @author JP
 */
public class DBObjectTranslatorImpl implements DBObjectTranslator {

    @Override
    public DBObject toDBObject(Object o) {

        Splittable s = AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(o));
        return (DBObject) JSON.parse(s.getPayload());
    }

    @Override
    public <T> T toObject(Class<T> type, DBObject dbObject) {

        if ( dbObject == null ) {

            return null;
        }

        return AutoBeanCodex.decode(AutoBeanFactorySource.create(DBObjectAutoBeanFactory.class), type, dbObject.toString()).as();
    }
}
