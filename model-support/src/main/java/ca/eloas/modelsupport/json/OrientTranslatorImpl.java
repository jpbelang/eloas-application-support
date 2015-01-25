package ca.eloas.modelsupport.json;

import ca.eloas.modelsupport.DBObject;
import ca.eloas.modelsupport.ObjectTranslator;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.shared.Splittable;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author JP
 */
public class OrientTranslatorImpl implements ObjectTranslator<String> {


    @Override
    public <T extends DBObject> T fromExternalToDomain(Class<T> type, String stringObject) {
        if (stringObject == null) {

            return null;
        }

        try {
            JSONObject jo = new JSONObject(stringObject);
            AutoBean<T> t = AutoBeanCodex.decode(AutoBeanFactorySource.create(OrientAutoBeanFactory.class), type, stringObject);
            t.setTag("id", jo.getString("@rid"));
            return t.as();
        } catch (JSONException e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public <T extends String> T fromDomainToExternal(Class<T> type, DBObject dbObject) {
        AutoBean ab = AutoBeanUtils.getAutoBean(dbObject);
        Splittable spl = AutoBeanCodex.encode(ab);

        return (T) spl.getPayload();
    }
}
