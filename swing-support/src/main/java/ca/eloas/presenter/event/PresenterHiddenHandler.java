/**
 * BSD License
 * Copyright (c) David Peterson
 * All rights reserved.
 */
package ca.eloas.presenter.event;

import ca.eloas.eventbus.EventHandler;

public interface PresenterHiddenHandler extends EventHandler{
	void onPresenterHidden(PresenterHiddenEvent event);
}
