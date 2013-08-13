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
public class JSONObjectList implements MessageList<JSONObject> {

    private List<JSONObject> objects = new ArrayList<JSONObject>();

    @Override
    public List<JSONObject> getMessages() {
        return objects;
    }

    @Override
    public void setMessages(List<JSONObject> list) {

        this.objects = list;
    }

    public JSONObject asJSONArray() throws JSONException {

        JSONObject jo = new JSONObject();
        jo.put("messages", new JSONArray(objects));
        jo.put("links", new JSONArray());

        return jo;
    }
}
