package ca.eloas.modelsupport.json;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author JP
 */
public class DBObjectTranslatorImplTest {

    public interface Foo extends ca.eloas.modelsupport.DBObject {

        int getInteger();
        void setInteger(int a);
    }

    @Test
    public void testToObject() throws Exception {


        DBObjectTranslatorImpl tr = new DBObjectTranslatorImpl();
        DBObject o = BasicDBObjectBuilder.start("integer", 3).get();

        Foo f = tr.fromExternalToDomain(Foo.class, o);
        assertThat(f.getInteger(), is(equalTo(3)));
    }
}
