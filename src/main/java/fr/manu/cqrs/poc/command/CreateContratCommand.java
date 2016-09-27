package fr.manu.cqrs.poc.command;

public class CreateContratCommand {
	public final String reference;
	public final String label;

	public CreateContratCommand(String reference, String label) {
		this.reference = reference;
		this.label = label;
	}
}
