/**
 * BSD License
 * Copyright (c) David Peterson
 * All rights reserved.
 */
package ca.eloas.presenter.swing;

import ca.eloas.eventbus.EventBus;
import ca.eloas.presenter.BasicPresenter;

/**
 * 
 * @author David Peterson, refactored by Steffen Kaempke
 *
 */
public abstract class SwingPresenter <D extends SwingDisplay> extends BasicPresenter<D> {
	public SwingPresenter(D display, EventBus eventBus) {
        super(display, eventBus);
    }
}
