package ca.eloas.restsupport.messages;

import java.util.List;

/**
 * @author JP
 */
public interface MessageList<CONTAINED> {

    List<CONTAINED> getMessages();
    void setMessages(List<CONTAINED> list);
}
