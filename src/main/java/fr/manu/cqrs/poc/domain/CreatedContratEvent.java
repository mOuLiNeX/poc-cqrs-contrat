package fr.manu.cqrs.poc.domain;

public class CreatedContratEvent extends Event {
	public final String reference;

	public CreatedContratEvent(String reference) {
		this.reference = reference;
	}

	@Override
	public Object getTargetId() {
		return reference;
	}

	@Override
	public String getAction() {
		return "CREATE";
	}

	@Override
	public String getCategory() {
		return "CONTRAT";
	}
}
