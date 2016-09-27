package fr.manu.cqrs.poc.command;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Test;

import fr.manu.cqrs.poc.repository.ContratRepository;
import fr.manu.cqrs.poc.repository.EventStore;

public class CommandHandlerTest {
	@After
	public void tearDown() {
		EventStore.clear();
		ContratRepository.close();
	}

	@Test(expected = IllegalStateException.class)
	public void cannotCreateSameReferenceTwice() {
		// GIVEN
		CreateContratCommand command = new CreateContratCommand("reference", "label");
		CommandHandler.execute(command);

		// WHEN
		CommandHandler.execute(command);

		// THEN, cf expect
	}

	@Test(expected = NoSuchElementException.class)
	public void cannotChangeLabelOnUnknownContrat() {
		// GIVEN
		RelabelContratCommand command = new RelabelContratCommand("reference", "label");

		// WHEN
		CommandHandler.execute(command);

		// THEN, cf expect
	}

	@Test
	public void canRelabelExistingContrat() {
		// GIVEN
		CommandHandler.execute(new CreateContratCommand("reference", "label"));
		CommandHandler.execute(new CreateContratCommand("reference2", "label"));

		// WHEN
		CommandHandler.execute(new RelabelContratCommand("reference2", "labe2"));

		// THEN, cf expect
	}

}
