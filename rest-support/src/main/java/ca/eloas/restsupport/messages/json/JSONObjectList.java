package ca.eloas.restsupport.messages.json;

import ca.eloas.restsupport.messages.MessageList;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JP
 */
public class JSONObjectList extends JSONObject implements MessageList<JSONObject> {

    private List<JSONObject> objects = new ArrayList<JSONObject>();

    @Override
    public List<JSONObject> getMessages() {
        return objects;
    }

    @Override
    public void setMessages(List<JSONObject> list) {

        this.objects = list;
        try {
            this.put("messages", new JSONArray(objects));
        } catch(Exception e) {

            throw new RuntimeException(e);
        }
    }

    public JSONObject asJSONObject() throws JSONException {

        return new JSONObject(this.toString());
    }
}
