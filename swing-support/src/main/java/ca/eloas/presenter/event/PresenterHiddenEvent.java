/**
 * BSD License
 * Copyright (c) David Peterson
 * All rights reserved.
 */
package ca.eloas.presenter.event;

import ca.eloas.eventbus.Event;
import ca.eloas.eventbus.EventBus;
import ca.eloas.presenter.swing.Presenter;

/**
 * Presenters can send this event to the {@link ca.eloas.eventbus.EventBus} to notify other
 * interested parties when the presenter has been 'revealed' on the screen. This
 * is particularly useful for situations where a presenter contains other
 * presenters and wants needs to reveal itself when a child presenter is
 * revealed.
 *
 * @author David Peterson
 */
public class PresenterHiddenEvent extends Event<PresenterHiddenHandler> {

	private static final Type<PresenterHiddenHandler> TYPE = new Type<PresenterHiddenHandler>();

	public static Type<PresenterHiddenHandler> getType() {
		return TYPE;
	}

	/**
	 * Fires a {@link ca.eloas.presenter.event.PresenterHiddenEvent} into the {@link ca.eloas.eventbus.EventBus},
	 * specifying that it was the originator.
	 *
	 * @param eventBus
	 *            The event bus.
	 * @param presenter
	 *            The presenter.
	 */
	public static void fire(EventBus eventBus, Presenter presenter) {
		fire(eventBus, presenter, true);
	}

	/**
	 * Fires the event into the provided {@link ca.eloas.eventbus.EventBus}.
	 *
	 * @param eventBus
	 *            The event bus.
	 * @param presenter
	 *            The presenter.
	 * @param originator
	 *            If <code>true</code>, this presenter was the originator for
	 *            the request.
	 */
	public static void fire(EventBus eventBus, Presenter presenter,
			boolean originator) {
		eventBus.fireEvent(new PresenterHiddenEvent(presenter, originator));
	}

	private final Presenter presenter;
	private boolean originator;

	/**
	 * Constructs a new revelation event, specifying that it is the originator.
	 *
	 * @param presenter
	 *            The presenter.
	 */
	public PresenterHiddenEvent(Presenter presenter) {
		this(presenter, true);
	}

	/**
	 * Constructs a new revelation event, with the specified 'originator'
	 * status.
	 *
	 * @param presenter
	 *            The presenter that has been revealed.
	 * @param originator
	 *            If <code>true</code>, the presenter is the originator of the
	 *            revelation chain.
	 */
	public PresenterHiddenEvent(Presenter presenter, boolean originator) {
		this.presenter = presenter;
		this.originator = originator;
	}

	public Presenter getPresenter() {
		return presenter;
	}

	/**
	 * Returns <code>true</code> if the presenter in this event originated the
	 * revelation, or <code>false</code> if it is a consequence of being
	 * revealed by a child presenter.
	 *
	 * @return <code>true</code> if the event was the originator.
	 */
	public boolean isOriginator() {
		return originator;
	}

	@Override
	protected void dispatch(PresenterHiddenHandler handler) {
		handler.onPresenterHidden(this);
	}

	@Override
	public Type<PresenterHiddenHandler> getAssociatedType() {
		return getType();
	}

}
