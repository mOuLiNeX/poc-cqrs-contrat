package fr.manu.cqrs.poc.domain;

import java.time.Instant;
import java.util.UUID;

public abstract class Event {
	public static final int VERSION = 1;
	protected UUID id;
	protected Instant time;

	public abstract Object getTargetId();

	public abstract String getAction();

	public abstract String getCategory();

	public Event() {
		id = UUID.randomUUID();
		time = Instant.now();
	}

}
