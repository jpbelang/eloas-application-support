package ca.eloas.orientdb;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.iterator.ORecordIteratorClass;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * @author JP
 */
public class Fun {

    public static void main(String[] args) throws Exception {

        ODatabaseDocumentTx db = new ODatabaseDocumentTx("memory:foo").create();
/*
        OClass c = db.getMetadata().getSchema().createClass("Person");
        c.createProperty("name", OType.STRING);
        c.createProperty("surname", OType.STRING);

*/
        ODocument doc = new ODocument("Person");

        doc.field("name", "Luke");
        doc.field("surname", "Skywalker");
        doc.field("city", new ODocument("City").field("name", "Rome").field("country", "Italy"));

        doc.save();

        System.err.println("Id is " + doc.getIdentity());


        ORecordIteratorClass<ODocument> it = db.browseClass("Person");
        for (ODocument entries : it) {
            System.err.println("all " + entries.toJSON("rid,version,class,type,attribSameRow,keepTypes,alwaysFetchEmbedded,fetchPlan:city.name:0"));
            System.err.println("hmmm " + entries.field("city"));
        }
    }
}
