package com.bosonit.springboot.bp1;

import com.bosonit.springboot.bp1.content.persona.domain.Persona;
import com.bosonit.springboot.bp1.content.persona.infraestructure.repository.port.PersonaPortRep;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDate;
import java.util.Date;


@SpringBootApplication
public class Db1Application {

	public static void main(String[] args) {
		SpringApplication.run(Db1Application.class, args);
	}

	@Bean
	public CommandLineRunner run(PersonaPortRep personaRepository){
		LocalDate date1 = LocalDate.of(2021,10,21);
		LocalDate date2 = LocalDate.of(2099,10,21);

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
