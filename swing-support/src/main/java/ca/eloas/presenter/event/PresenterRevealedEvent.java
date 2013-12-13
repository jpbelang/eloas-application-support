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
 * Presenters can send this event to the {@link EventBus} to notify other
 * interested parties when the presenter has been 'revealed' on the screen. This
 * is particularly useful for situations where a presenter contains other
 * presenters and wants needs to reveal itself when a child presenter is
 * revealed.
 * 
 * @author David Peterson
 */
public class PresenterRevealedEvent extends Event<PresenterRevealedHandler> {

	private static final Event.Type<PresenterRevealedHandler> TYPE = new Event.Type<PresenterRevealedHandler>();

	public static Event.Type<PresenterRevealedHandler> getType() {
		return TYPE;
	}

	/**
	 * Fires a {@link PresenterRevealedEvent} into the {@link EventBus},
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
	 * Fires the event into the provided {@link EventBus}.
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
		eventBus.fireEvent(new PresenterRevealedEvent(presenter, originator));
	}

	private final Presenter presenter;
	private boolean originator;

	/**
	 * Constructs a new revelation event, specifying that it is the originator.
	 * 
	 * @param presenter
	 *            The presenter.
	 */
	public PresenterRevealedEvent(Presenter presenter) {
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
	public PresenterRevealedEvent(Presenter presenter, boolean originator) {
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
	protected void dispatch(PresenterRevealedHandler handler) {
		handler.onPresenterRevealed(this);
	}

	@Override
	public Event.Type<PresenterRevealedHandler> getAssociatedType() {
		return getType();
	}

}
