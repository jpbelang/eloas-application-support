package ca.eloas.restsupport.operations.model;

import ca.eloas.modelsupport.DBObject;
import ca.eloas.modelsupport.ModelObjectFactory;
import ca.eloas.restsupport.ObjectFactory;
import ca.eloas.restsupport.ToMessageOperation;
import com.google.inject.assistedinject.AssistedInject;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.shared.AutoBeanVisitor;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;

import javax.inject.Inject;
import java.lang.reflect.Method;

/**
 * @author JP
 */
public class CopyToMessage implements ToMessageOperation<DBObject, Object> {

    final private ObjectFactory objectFactory;

    @Inject
    public CopyToMessage(ObjectFactory of) {

        this.objectFactory = of;
    }

    public void run(final DBObject object, final Object message) {

        AutoBean<Object> bean = AutoBeanUtils.getAutoBean(message);

        bean.accept(new AutoBeanVisitor() {
            @Override
            public boolean visitValueProperty(String propertyName, Object value, PropertyContext ctx) {

                if ( propertyName.equals("empty")) {

                    return true;
                }

                try {
                    Method m = object.getClass().getMethod("get" + Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1));
                    ctx.set(m.invoke(object));

                    return super.visitValueProperty(propertyName,value, ctx);
                } catch (NoSuchMethodException e) {

                    return super.visitValueProperty(propertyName,value, ctx);
                } catch (Exception e) {

                    throw new RuntimeException(e);
                }
            }
        });
    }
}
