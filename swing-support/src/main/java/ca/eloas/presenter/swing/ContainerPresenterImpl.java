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

import javax.swing.*;

/**
 * 
 * @author David Peterson, refactored by Steffen Kaempke
 *
 */
public abstract class ContainerPresenterImpl<T extends ContainerDisplay> extends PresenterImpl<T> {
	private final List<PresenterImpl<?>> presenters;
    private PresenterImpl<?> currentPresenter;

    /**
     *
     * @param display
     * @param eventBus
     * @param presenters
     */
    public ContainerPresenterImpl(T display, EventBus eventBus,
                                  PresenterImpl<?>... presenters) {
        super(display, eventBus);
        this.presenters = new java.util.ArrayList<PresenterImpl<?>>();
        Collections.addAll(this.presenters, presenters);
    }

    /**
     * Adds the presenter, if the current presenter is unbound.
     * 
     * @param presenter
     *            The presenter to add.
     * @return If added, returns <code>true</code>.
     */
    protected boolean addPresenter(PresenterImpl<?> presenter) {
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
                    showPresenter((PresenterImpl<?>) event.getPresenter());
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
    protected PresenterImpl<?> getCurrentPresenter() {
        return currentPresenter;
    }

    /**
     *
     * @param presenter
     * @return
     */
    protected int indexOf(PresenterImpl<?> presenter) {
        return presenters.indexOf(presenter);
    }

    /**
     *
     * @param presenter
     */
    protected void showPresenter(PresenterImpl<?> presenter) {
        if (indexOf(presenter) >= 0) {
            currentPresenter = presenter;
            display.showComponent(presenter.getDisplay().asComponent());
        }
    }

    public void insertIn(JFrame mainFrame) {

        mainFrame.add(this.getDisplay().asComponent());
        //mainFrame.pack();
    }
}
