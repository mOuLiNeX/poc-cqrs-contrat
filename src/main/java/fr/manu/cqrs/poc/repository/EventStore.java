package fr.manu.cqrs.poc.repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

import fr.manu.cqrs.poc.domain.Event;

public class EventStore extends Observable {

	private static final Collection<Event> STORE = new LinkedList<>();

	public Iterable<Event> loadEventsByCategory(String category) {
		return STORE.stream().filter(evt -> category.equals(evt.getCategory())).collect(Collectors.toList());
	}

	public void publish(Event uncommitedEvent) {
		STORE.add(uncommitedEvent);
		notifyObservers(Arrays.asList(uncommitedEvent));
	}

	public void publish(List<Event> uncommitedEvents) {
		STORE.addAll(uncommitedEvents);
		setChanged();
		notifyObservers(uncommitedEvents);
	}

	public boolean isEmpty() {
		return STORE.isEmpty();
	}

	public static void clear() {
		STORE.clear();
	}

}
