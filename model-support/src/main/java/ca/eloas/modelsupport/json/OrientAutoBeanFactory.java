package ca.eloas.modelsupport.json;

import ca.eloas.modelsupport.KeyType;
import ca.eloas.modelsupport.ObjectIdKey;
import ca.eloas.modelsupport.StringKey;
import ca.eloas.modelsupport.jpa.UUIDKey;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import org.bson.types.ObjectId;

import java.util.UUID;

/**
 * @author JP
 */
@AutoBeanFactory.Category(OrientAutoBeanFactory.StableIdCategory.class)
public interface OrientAutoBeanFactory extends AutoBeanFactory {

    public interface Bye<T> {

        public T boo();
    }

    public class StableIdCategory {

        public static KeyType stableId(AutoBean instance) {

            if (instance.getTag("id") == null) {

                instance.setTag("id", "");
            }

            return new StringKey((String) instance.getTag("id"));
        }

/*
        public static KeyType allo(AutoBean instance) {

            return new KeyType() {

                @Override
                public String asString() {
                    return "goo";
                }

                @Override
                public Object getKey() {
                    return null;
                }
            };
        }
*/

    }
}
