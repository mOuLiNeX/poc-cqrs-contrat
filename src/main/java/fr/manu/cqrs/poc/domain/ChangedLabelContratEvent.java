package fr.manu.cqrs.poc.domain;

public class ChangedLabelContratEvent extends Event {
	public final String reference;
	public final String label;

	public ChangedLabelContratEvent(String reference, String label) {
		this.reference = reference;
		this.label = label;

	}

	@Override
	public Object getTargetId() {
		return reference;
	}

	@Override
	public String getAction() {
		return "UPDATE";
	}

	@Override
	public String getCategory() {
		return "CONTRAT";
	}
}
