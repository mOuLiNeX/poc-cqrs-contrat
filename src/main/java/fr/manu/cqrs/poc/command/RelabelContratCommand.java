package fr.manu.cqrs.poc.command;

public class RelabelContratCommand {
	public final String reference;
	public final String label;

	public RelabelContratCommand(String reference, String label) {
		this.reference = reference;
		this.label = label;
	}
}
