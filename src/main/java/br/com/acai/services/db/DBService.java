package br.com.acai.services.db;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.acai.entities.Flavor;
import br.com.acai.entities.Personalize;
import br.com.acai.entities.Size;
import br.com.acai.repositories.FlavorRepository;
import br.com.acai.repositories.PersonalizeRepository;
import br.com.acai.repositories.SizeRepository;

@Service
public class DBService {

	@Autowired
	private FlavorRepository flavorRepository;

	@Autowired
	private SizeRepository sizeRepository;

	@Autowired
	private PersonalizeRepository personalizeRepository;

	public void instantiateTestDatabase() {

		this.prepareFlavor();
		this.prepareSize();
		this.preparePersonalize();

		System.out.println(" -- Database has been initialized");

	}

	public void prepareSize() {

		Size size1 = new Size();
		size1.setDescription("Pequeno - (300 ml)");
		size1.setPrice(new BigDecimal(10));
		size1.setPreparationTime(5);

		Size size2 = new Size();
		size2.setDescription("Médio - (500 ml)");
		size2.setPrice(new BigDecimal(13));
		size2.setPreparationTime(7);

		Size size3 = new Size();
		size3.setDescription("Grande - (700 ml)");
		size3.setPrice(new BigDecimal(15));
		size3.setPreparationTime(10);

		this.sizeRepository.save(size1);
		this.sizeRepository.save(size2);
		this.sizeRepository.save(size3);

	}

	public void preparePersonalize() {

		Personalize personalize1 = new Personalize();
		personalize1.setDescription("Granola");
		personalize1.setPreparationTime(0);
		personalize1.setPrice(new BigDecimal(0));

		Personalize personalize2 = new Personalize();
		personalize2.setDescription("Paçoca");
		personalize2.setPreparationTime(3);
		personalize2.setPrice(new BigDecimal(3));

		Personalize personalize3 = new Personalize();
		personalize3.setDescription("Leite Ninho");
		personalize3.setPreparationTime(0);
		personalize3.setPrice(new BigDecimal(3));

		this.personalizeRepository.save(personalize1);
		this.personalizeRepository.save(personalize2);
		this.personalizeRepository.save(personalize3);

	}

	public void prepareFlavor() {

		Flavor flavor1 = new Flavor();
		flavor1.setDescription("Morango");
		flavor1.setPreparationTime(0);

		Flavor flavor2 = new Flavor();
		flavor2.setDescription("Banana");
		flavor2.setPreparationTime(0);

		Flavor flavor3 = new Flavor();
		flavor3.setDescription("Kiwi");
		flavor3.setPreparationTime(5);

		this.flavorRepository.save(flavor1);
		this.flavorRepository.save(flavor2);
		this.flavorRepository.save(flavor3);

	}

}
