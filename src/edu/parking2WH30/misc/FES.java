package edu.parking2WH30.misc;

import java.util.ArrayList;

public class FES {

	/* Sorted list of events in the queue, ordered by time */
	protected ArrayList<Event> events;

	/**
	 * Constructs an empty event queue
	 */

	public FES() {
		events = new ArrayList<Event>();
	}

	/**
	 * Adds an event to this queue
	 * @param newEvent the new event
	 */

	public void addEvent(Event newEvent) {
		int insertIndex = 0;
		while (insertIndex < events.size()) {
			Event e = events.get(insertIndex);
			if (e.getTime() > newEvent.getTime()) break;
			insertIndex++;
		}
		events.add(insertIndex, newEvent);
	}

	/**
	 * Returns and removes the first event from this queue
	 * @return the event that is removed from this queue.
	 */
	public Event nextEvent() {
		return events.remove(0);
	}

}