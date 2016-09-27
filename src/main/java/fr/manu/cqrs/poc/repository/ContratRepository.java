package fr.manu.cqrs.poc.repository;

import java.util.NoSuchElementException;

import fr.manu.cqrs.poc.domain.Contrat;

public class ContratRepository {

	static final EventStore STORE = new EventStore();
	private static final InMemoryContratProjection IN_MEM_DB = new InMemoryContratProjection();

	private static InMemoryContratProjection getInMemoryDb() {
		if (IN_MEM_DB.isEmpty() && !STORE.isEmpty()) {
			IN_MEM_DB.loadInMemoryDb();
		}
		return IN_MEM_DB;
	}

	public static void close() {
		IN_MEM_DB.clear();
	}

	public ContratRepository() {
		super();
		STORE.addObserver(IN_MEM_DB);
	}

	public Contrat findByReference(String reference) {
		if (!getInMemoryDb().containsKey(reference)) {
			throw new NoSuchElementException("Contrat " + reference + " inexistant en base");
		}
		return getInMemoryDb().get(reference);
	}

	public void persist(Contrat contrat) {
		STORE.publish(contrat.uncommitedEvents);
	}

	public boolean exists(String reference) {
		return getInMemoryDb().containsKey(reference);
	}

}
