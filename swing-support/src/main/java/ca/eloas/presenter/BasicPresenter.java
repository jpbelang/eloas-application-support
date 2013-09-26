/**
 * BSD License
 * Copyright (c) David Peterson
 * All rights reserved.
 */
package ca.eloas.presenter;

import java.util.ArrayList;
import java.util.Collection;

import ca.eloas.presenter.event.PresenterRevealedEvent;
import ca.eloas.eventbus.EventBus;
import ca.eloas.eventbus.HandlerRegistration;

public abstract class BasicPresenter <D extends Display> implements Presenter  {
	/**
	 * The display for the presenter.
	 */
	protected final D display;
	/**
	 * The {@link EventBus} for the application.
	 */
	protected final EventBus eventBus;
	private Collection<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
	private boolean bound = false;

	public BasicPresenter(D display, EventBus eventBus) {
		this.display = display;
		this.eventBus = eventBus;
	}

	public void bind() {
		onBind();
		bound = true;
	}

	/**
	 * Any {@link HandlerRegistration}s added will be removed when
	 * {@link #unbind()} is called. This provides a handy way to track event
	 * handler registrations when binding and unbinding.
	 * 
	 * @param handlerRegistration
	 *            The registration.
	 */
	protected void registerHandler(HandlerRegistration handlerRegistration) {
		if (null == handlerRegistration) {
			throw new IllegalArgumentException(
					"null 'handlerRegistration' argument.");
		}
		handlerRegistrations.add(handlerRegistration);
	}

	protected void unRegisterHandler(HandlerRegistration handlerRegistration) {
		if (null == handlerRegistration) {
			throw new IllegalArgumentException(
					"null 'handlerRegistration' argument.");
		}
		handlerRegistrations.remove(handlerRegistration);
		handlerRegistration.removeHandler();
	}

	/**
	 * This method is called when unbinding the presenter. Any handler
	 * registrations recorded with {@link #registerHandler(HandlerRegistration)}
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
	protected abstract void onBind();

	/**
	 * This method is called when unbinding the presenter. Any handler
	 * registrations recorded with {@link #registerHandler(HandlerRegistration)}
	 * will have already been removed at this point.
	 */
	protected abstract void onUnbind();

	/**
	 * Checks if the presenter has been bound. Will be set to false after a call
	 * to {@link #unbind()}.
	 * 
	 * @return The current bound status.
	 */
	public boolean isBound() {
		return bound;
	}

	/**
	 * Returns the display for the presenter.
	 * 
	 * @return The display.
	 */
	public D getDisplay() {
		return display;
	}

	/**
	 * Triggers a {@link ca.eloas.presenter.event.PresenterRevealedEvent}. Subclasses should override
	 * this method and call <code>super.revealDisplay()</code> if they need to
	 * perform extra operations when being revealed.
	 */
	public void revealDisplay() {
		eventBus.fireEvent(new PresenterRevealedEvent(this));
	}
}
