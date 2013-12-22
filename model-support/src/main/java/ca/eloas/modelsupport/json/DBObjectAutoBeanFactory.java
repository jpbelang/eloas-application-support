package ca.eloas.modelsupport.json;

import ca.eloas.modelsupport.ObjectIdKey;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import org.bson.types.ObjectId;

/**
 * @author JP
 */
@AutoBeanFactory.Category(DBObjectAutoBeanFactory.StableIdCategory.class)
public interface DBObjectAutoBeanFactory extends AutoBeanFactory {

    public class StableIdCategory {

        public static ObjectIdKey stableId(AutoBean instance) {

            if ( instance.getTag("id") == null ) {

                instance.setTag("id", new ObjectId());
            }

            return new ObjectIdKey((ObjectId) instance.getTag("id"));
        }
    }
}
