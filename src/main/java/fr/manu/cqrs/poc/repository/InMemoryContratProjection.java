package fr.manu.cqrs.poc.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import fr.manu.cqrs.poc.domain.Contrat;
import fr.manu.cqrs.poc.domain.Event;
import fr.manu.cqrs.poc.utils.ReflectionUtil;

class InMemoryContratProjection implements Observer {
	private final Map<Object, Contrat> storage = new HashMap<>();

	void feedWithEvents(Iterable<Event> evts) {
		for (Event e : evts) {
			Contrat c = "CREATE".equals(e.getAction()) ? new Contrat() : storage.get(e.getTargetId());
			ReflectionUtil.invokeApplyMethod(c, e);
			storage.put(e.getTargetId(), c);
		}
	}

	public Contrat get(String reference) {
		return storage.get(reference);
	}

	public boolean containsKey(String reference) {
		return storage.containsKey(reference);
	}

	public void clear() {
		storage.clear();
	}

	boolean isEmpty() {
		return storage.isEmpty();
	}

	void loadInMemoryDb() {
		feedWithEvents(ContratRepository.STORE.loadEventsByCategory("CONTRAT"));
	}

	@Override
	public void update(Observable o, Object arg) {
		List<Event> lastCommitedEvents = (List<Event>) arg;
		feedWithEvents(lastCommitedEvents);
	}
}