/**
 * BSD License
 * Copyright (c) David Peterson
 * All rights reserved.
 */
package ca.eloas.eventbus;

/**
 * An interface providing minimal access to an {@link EventHandler} manager.
 * 
 * Rather than being attached to a single object, an EventBus provides a central
 * pathway to send events across the whole application.
 * 
 * @author David Peterson
 * 
 */
public interface EventBus {
	<H extends EventHandler> HandlerRegistration addHandler(Event.Type<H> type,
			H handler);

	void fireEvent(Event<?> event);

	<H extends EventHandler> H getHandler(Event.Type<H> type, int index);

	int getHandlerCount(Event.Type<?> type);

	boolean isEventHandled(Event.Type<?> e);
}
