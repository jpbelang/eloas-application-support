package ca.eloas.modelsupport.json;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.shared.Splittable;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;


/**
 * @author JP
 */
public class DBObjectTranslatorImpl implements ObjectTranslator<DBObject> {

    @Override
    public DBObject toDBObject(ca.eloas.modelsupport.DBObject o) {

        AutoBean ab = AutoBeanUtils.getAutoBean(o);
        Splittable s = AutoBeanCodex.encode(ab);

        DBObject parsed = (DBObject) JSON.parse(s.getPayload());
        parsed.put("_id", ab.getTag("id"));
        return parsed;
    }

    @Override
    public <T extends ca.eloas.modelsupport.DBObject> T toDomainObject(Class<T> type, DBObject dbObject) {

        if (dbObject == null) {

            return null;
        }

        AutoBean<T> t = AutoBeanCodex.decode(AutoBeanFactorySource.create(DBObjectAutoBeanFactory.class), type, dbObject.toString());
        t.setTag("id", dbObject.get("_id"));
        return t.as();
    }
}
