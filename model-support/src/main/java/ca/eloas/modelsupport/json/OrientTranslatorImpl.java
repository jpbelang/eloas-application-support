package ca.eloas.modelsupport.json;

import ca.eloas.modelsupport.DBObject;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.shared.Splittable;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import com.mongodb.util.JSON;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author JP
 */
public class OrientTranslatorImpl implements ObjectTranslator<String> {
    @Override
    public String toDBObject(DBObject o) {
        AutoBean ab = AutoBeanUtils.getAutoBean(o);
        Splittable s = AutoBeanCodex.encode(ab);

        return s.getPayload();
    }

    @Override
    public <T extends DBObject> T toDomainObject(Class<T> type, String dbObject) {
        if (dbObject == null) {

            return null;
        }

        try {
            JSONObject jo = new JSONObject(dbObject);
            AutoBean<T> t = AutoBeanCodex.decode(AutoBeanFactorySource.create(OrientAutoBeanFactory.class), type, dbObject);
            t.setTag("id", jo.getString("@rid"));
            return t.as();
        } catch (JSONException e) {

            throw new RuntimeException(e);
        }
    }
}
