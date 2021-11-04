package com.bosonit.springboot.db2;

import com.bosonit.springboot.db2.content.asignatura.domain.Asignatura;
import com.bosonit.springboot.db2.content.asignatura.infraestructure.repository.port.AsignaturaPortRep;
import com.bosonit.springboot.db2.content.persona.domain.Persona;
import com.bosonit.springboot.db2.content.persona.infraestructure.repository.port.PersonaPortRep;
import com.bosonit.springboot.db2.content.profesor.domain.Profesor;
import com.bosonit.springboot.db2.content.profesor.infraestructure.repository.port.ProfesorPortRep;
import com.bosonit.springboot.db2.content.student.domain.Student;
import com.bosonit.springboot.db2.content.student.infraestructure.repository.port.StudentPortRep;
import org.h2.tools.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@SpringBootApplication
@EnableFeignClients("com.bosonit.springboot.db2")
public class Db2Application {

	public static void main(String[] args) {
		SpringApplication.run(Db2Application.class, args);
	}
/*
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}
*/
	@Bean
	public CommandLineRunner run(PersonaPortRep personaRepository, StudentPortRep studentRepository,
								 ProfesorPortRep profesorRepository, AsignaturaPortRep asignaturaRepository){
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate localDate = LocalDate.of(2099,10,21);
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		return (String[] args) -> {
			Persona persona1 = new Persona("Conan","Serpiente","Conan",
					"El barbaro","mata@serpientes.com","conan@cimmeria.com",
					"Cimmeria",true, "http://conan.jpg",date);
			personaRepository.save(persona1);
			Persona persona2 = new Persona("AshKetchum","Pikachu","Ash",
					"Ketchum","caza@pokemon.com","ash@oak.com",
					"Pueblo paleta",true, "http://conan.jpg",date);
			personaRepository.save(persona2);
			Persona persona3 = new Persona("Squall","Rinoa","Squall",
					"Leonhart","squall@ff8.com","squall@ff8.com",
					"Winhill",true, "http://gryphus.jpg",date);
			personaRepository.save(persona3);
			Profesor profesor = new Profesor("Comentario1","FRONTEND", persona1);
			profesor = profesorRepository.save(profesor);

			Student student = new Student(20,"PEREKEPEN", "FULLSTACK", persona2, profesor);

			Asignatura asignatura = new Asignatura("5mentarios", "Entrenamiento pokemon", date);
			asignatura.addStudent(student);
			student.addAsignatura(asignatura);
			asignatura = asignaturaRepository.save(asignatura);
			student = studentRepository.save(student);

			Asignatura asignatura2 = new Asignatura("5mentarios", "SpringBoot con Chuchi", date); // nueva
			asignatura2 = asignaturaRepository.save(asignatura2); // nueva

			System.out.println("-- Cargadas personas de prueba: "+persona1.getUsuario()+";"+persona2.getUsuario()+";"+persona3.getUsuario());
			System.out.println("-- Cargado estudiante de prueba: "+student.getId_student()+" --> "+student.getPersona().getUsuario());
			System.out.println("-- Cargado profesor de prueba: "+profesor.getId_profesor()+" --> "+profesor.getPersona().getUsuario());
			System.out.println("-- Cargada asignatura de prueba: "+asignatura.getAsignatura()+" asignada a "+student.getPersona().getUsuario());
			System.out.println("-- Cargada asignatura de prueba: "+asignatura2.getAsignatura()+" sin asignar ");
		};


	}

}