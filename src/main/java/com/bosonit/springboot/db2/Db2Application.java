package com.bosonit.springboot.db2;

import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.persona.infraestructure.repository.port.PersonaPortRep;
import com.bosonit.springboot.db2.content.student.domain.Student;
import com.bosonit.springboot.db2.content.student.infraestructure.repository.port.StudentPortRep;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@SpringBootApplication
public class Db2Application {

	public static void main(String[] args) {
		SpringApplication.run(Db2Application.class, args);
	}

	@Bean
	public CommandLineRunner run(PersonaPortRep personaRepository, StudentPortRep studentRepository){
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
			Student student = new Student(persona1,20,"PEREKEPEN", "FULLSTACK");
			personaRepository.save(persona1);
			personaRepository.save(persona2);
			studentRepository.save(student);
			System.out.println("-- Cargadas personas de prueba: "+persona1.getUsuario()+" y "+persona2.getUsuario());
			System.out.println("-- Cargado estudiante de prueba: "+student.getId_student());
		};
	}

}
