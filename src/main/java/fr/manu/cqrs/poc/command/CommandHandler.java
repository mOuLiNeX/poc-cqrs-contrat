package fr.manu.cqrs.poc.command;

import fr.manu.cqrs.poc.domain.Contrat;
import fr.manu.cqrs.poc.repository.ContratRepository;

public class CommandHandler {
	public static Contrat execute(CreateContratCommand command) {
		ContratRepository repository = new ContratRepository();
		Contrat contrat = Contrat.createNew(command.reference, command.label);
		if (repository.exists(command.reference)) {
			throw new IllegalStateException("Contrat " + command.reference + " deja present");
		}
		repository.persist(contrat);
		return contrat;
	}

	public static Contrat execute(RelabelContratCommand command) {
		ContratRepository repository = new ContratRepository();
		Contrat contrat = repository.findByReference(command.reference);
		contrat.setLabel(command.label);
		repository.persist(contrat);
		return contrat;
	}
}
