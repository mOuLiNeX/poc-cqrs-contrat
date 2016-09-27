package fr.manu.cqrs.poc.domain;

import java.util.ArrayList;
import java.util.List;

import fr.manu.cqrs.poc.utils.ReflectionUtil;

public class Contrat {
	private String reference;
	private String label;
	public final List<Event> uncommitedEvents = new ArrayList<>();

	public static Contrat createNew(String reference, String label) {
		if (reference == null || reference.isEmpty()) {
			throw new IllegalStateException("Impossible de créer un contrat sans reference");
		}
		Contrat c = new Contrat();
		c.addUncommitedEvents(new CreatedContratEvent(reference));
		c.addUncommitedEvents(new ChangedLabelContratEvent(reference, label));
		return c;
	}

	public void setLabel(String label) {
		this.label = label;
		addUncommitedEvents(new ChangedLabelContratEvent(reference, label));
	}

	public void apply(CreatedContratEvent event) {
		this.reference = event.reference;
	}

	public void apply(ChangedLabelContratEvent event) {
		this.label = event.label;
	}

	private void addUncommitedEvents(Event e) {
		ReflectionUtil.invokeApplyMethod(this, e);
		uncommitedEvents.add(e);
	}

}
