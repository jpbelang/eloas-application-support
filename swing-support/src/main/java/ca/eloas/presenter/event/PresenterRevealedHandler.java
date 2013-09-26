/**
 * BSD License
 * Copyright (c) David Peterson
 * All rights reserved.
 */
package ca.eloas.presenter.event;

import ca.eloas.eventbus.EventHandler;

public interface PresenterRevealedHandler extends EventHandler{
	void onPresenterRevealed(PresenterRevealedEvent event);
}
