/**
 * BSD License
 * Copyright (c) David Peterson
 * All rights reserved.
 */
package ca.eloas.presenter.event;

import ca.eloas.eventbus.HandlerManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ca.eloas.eventbus.EventBus;

@Singleton
public class DefaultEventBus extends HandlerManager implements EventBus {

    @Inject
    public DefaultEventBus() {
        super(null);
    }
}
