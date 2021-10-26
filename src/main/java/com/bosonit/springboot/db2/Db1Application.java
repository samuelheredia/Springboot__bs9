package com.bosonit.springboot.db2;

import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.persona.infraestructure.repository.port.PersonaPortRep;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@SpringBootApplication
public class Db1Application {

	public static void main(String[] args) {
		SpringApplication.run(Db1Application.class, args);
	}

	@Bean
	public CommandLineRunner run(PersonaPortRep personaRepository){
		ZoneId defaultZoneId = ZoneId.systemDefault();

		LocalDate localDate1 = LocalDate.of(2021,10,21);
		LocalDate localDate2 = LocalDate.of(2099,10,21);

		Date date1 = Date.from(localDate1.atStartOfDay(defaultZoneId).toInstant());
		Date date2 = Date.from(localDate2.atStartOfDay(defaultZoneId).toInstant());

		return (String[] args) -> {
			Persona persona1 = new Persona(1, "ConanCim","Serpiente","Conan",
					"El barbaro","mata@serpientes.com","conan@cimmeria.com",
					"Cimmeria",true, date1, "http://conan.jpg",date2);
			Persona persona2 = new Persona(2, "AshKetchum","Pikachu","Ash",
					"Ketchum","caza@pokemon.com","ash@oak.com",
					"Pueblo paleta",true, date1, "http://conan.jpg",date2);
			personaRepository.save(persona1);
			personaRepository.save(persona2);
			System.out.println("-- Cargados registros de prueba: "+persona1.getUsuario()+" y "+persona2.getUsuario());
		};
	}

}
