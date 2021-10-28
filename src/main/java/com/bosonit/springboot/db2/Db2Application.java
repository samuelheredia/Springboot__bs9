package com.bosonit.springboot.db2;

import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.persona.infraestructure.repository.port.PersonaPortRep;
import com.bosonit.springboot.db2.content.profesor.domain.Profesor;
import com.bosonit.springboot.db2.content.profesor.infraestructure.repository.port.ProfesorPortRep;
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
	public CommandLineRunner run(PersonaPortRep personaRepository, StudentPortRep studentRepository, ProfesorPortRep profesorRepository){
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate localDate = LocalDate.of(2099,10,21);
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		return (String[] args) -> {
			Persona persona1 = new Persona(1, "ConanCim","Serpiente","Conan",
					"El barbaro","mata@serpientes.com","conan@cimmeria.com",
					"Cimmeria",true, new Date(), "http://conan.jpg",date);
			Persona persona2 = new Persona(2, "AshKetchum","Pikachu","Ash",
					"Ketchum","caza@pokemon.com","ash@oak.com",
					"Pueblo paleta",true, new Date(), "http://conan.jpg",date);
			Persona persona3 = new Persona(3, "summum","Rinoa","Squall",
					"Leonhart","squall@ff8.com","squall@ff8.com",
					"Winhill",true, new Date(), "http://gryphus.jpg",date);
			Student student = new Student(20,"PEREKEPEN", "FULLSTACK", persona1);
			Profesor profesor = new Profesor("Comentario1","FRONTEND",persona2);
			personaRepository.save(persona1);
			personaRepository.save(persona2);
			personaRepository.save(persona3);
			studentRepository.save(student);
			profesorRepository.save(profesor);
			System.out.println("-- Cargadas personas de prueba: "+persona1.getUsuario()+";"+persona2.getUsuario()+";"+persona3.getUsuario());
			System.out.println("-- Cargado estudiante de prueba: "+student.getId_student());
			System.out.println("-- Cargado profesor de prueba: "+profesor.getId_profesor());
		};
	}

}
