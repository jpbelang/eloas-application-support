/**
 * BSD License
 * Copyright (c) David Peterson
 * All rights reserved.
 */
package ca.eloas.presenter.swing;

import java.util.Collections;
import java.util.List;

import ca.eloas.eventbus.EventBus;
import ca.eloas.presenter.event.PresenterRevealedEvent;
import ca.eloas.presenter.event.PresenterRevealedHandler;

/**
 * 
 * @author David Peterson, refactored by Steffen Kaempke
 *
 */
public abstract class SwingContainerPresenter <T extends SwingContainerDisplay> extends SwingPresenter<T> {
	private final List<SwingPresenter<?>> presenters;
    private SwingPresenter<?> currentPresenter;

    /**
     *
     * @param display
     * @param eventBus
     * @param presenters
     */
    public SwingContainerPresenter(T display, EventBus eventBus,
            SwingPresenter<?>... presenters) {
        super(display, eventBus);
        this.presenters = new java.util.ArrayList<SwingPresenter<?>>();
        Collections.addAll(this.presenters, presenters);
    }

    /**
     * Adds the presenter, if the current presenter is unbound.
     * 
     * @param presenter
     *            The presenter to add.
     * @return If added, returns <code>true</code>.
     */
    protected boolean addPresenter(SwingPresenter<?> presenter) {
        if (!isBound()) {
            presenters.add(presenter);
            return true;
        }
        return false;
    }

    @Override
    protected void onBind() {

        registerHandler(eventBus.addHandler(PresenterRevealedEvent.getType(), new PresenterRevealedHandler() {

            public void onPresenterRevealed(PresenterRevealedEvent event) {
                if (presenters.contains(event.getPresenter())) {
                    showPresenter((SwingPresenter<?>) event.getPresenter());
                    revealDisplay();
                }
            }
        }));
    }

    @Override
    protected void onUnbind() {
        currentPresenter = null;
    }

    /**
     *
     * @return
     */
    protected SwingPresenter<?> getCurrentPresenter() {
        return currentPresenter;
    }

    /**
     *
     * @param presenter
     * @return
     */
    protected int indexOf(SwingPresenter<?> presenter) {
        return presenters.indexOf(presenter);
    }

    /**
     *
     * @param presenter
     */
    protected void showPresenter(SwingPresenter<?> presenter) {
        if (indexOf(presenter) >= 0) {
            currentPresenter = presenter;
            display.showComponent(presenter.getDisplay().asComponent());
        }
    }
}
