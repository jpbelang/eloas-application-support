/**
 * BSD License
 * Copyright (c) David Peterson
 * All rights reserved.
 */
package ca.eloas.presenter.swing;

import ca.eloas.eventbus.EventBus;
import ca.eloas.eventbus.HandlerRegistration;
import ca.eloas.presenter.event.PresenterRevealedEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author David Peterson, refactored by Steffen Kaempke
 *
 */
public abstract class PresenterImpl implements Presenter {
    /**
     * The display for the presenter.
     */
    protected final Display display;
    /**
     * The {@link ca.eloas.eventbus.EventBus} for the application.
     */
    protected final EventBus eventBus;
    private Collection<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    private boolean bound = false;

    public PresenterImpl(Display display, EventBus eventBus) {

        this.display = display;
        this.eventBus = eventBus;
    }


    public void bind() {
        onBind();
        bound = true;
    }

    /**
     * Any {@link ca.eloas.eventbus.HandlerRegistration}s added will be removed when
     * {@link #unbind()} is called. This provides a handy way to track event
     * handler registrations when binding and unbinding.
     *
     * @param handlerRegistration The registration.
     */
    protected void registerHandler(HandlerRegistration handlerRegistration) {
        if (null == handlerRegistration) {
            throw new IllegalArgumentException(
                    "null 'handlerRegistration' argument.");
        }
        handlerRegistrations.add(handlerRegistration);
    }

    protected void unregisterHandler(HandlerRegistration handlerRegistration) {
        if (null == handlerRegistration) {
            throw new IllegalArgumentException(
                    "null 'handlerRegistration' argument.");
        }
        handlerRegistrations.remove(handlerRegistration);
        handlerRegistration.removeHandler();
    }

    /**
     * This method is called when unbinding the presenter. Any handler
     * registrations recorded with {@link #registerHandler(ca.eloas.eventbus.HandlerRegistration)}
     * will have already been removed at this point.
     */
    public void unbind() {
        for (HandlerRegistration reg : handlerRegistrations) {
            reg.removeHandler();
        }
        handlerRegistrations.clear();

        onUnbind();
        bound = false;
    }

    /**
     * This method is called when binding the presenter. Any additional bindings
     * should be done here.
     */
    protected void onBind() {

    }
    /**
     * This method is called when unbinding the presenter. Any handler
     * registrations recorded with {@link #registerHandler(ca.eloas.eventbus.HandlerRegistration)}
     * will have already been removed at this point.
     */
    protected  void onUnbind() {

    }

    /**
     * Checks if the presenter has been bound. Will be set to false after a call
     * to {@link #unbind()}.
     *
     * @return The current bound status.
     */
    public boolean isBound() {
        return bound;
    }

    protected void onShow() {

    }

    protected void onHide() {

    }

    /**
     * Returns the display for the presenter.
     *
     * @return The display.
     */
    public Display getDisplay() {
        return display;
    }

    /**
     * Triggers a {@link ca.eloas.presenter.event.PresenterRevealedEvent}. Subclasses should override
     * this method and call <code>super.revealDisplay()</code> if they need to
     * perform extra operations when being revealed.
     */
    public void show() {
        eventBus.fireEvent(new PresenterRevealedEvent(this));
        onShow();
    }

    public void hide() {


        onHide();

    }

    protected void setInSlot(String name, Presenter childPresenter) {

        display.setInSlot(name, childPresenter.getDisplay());
    }
}
