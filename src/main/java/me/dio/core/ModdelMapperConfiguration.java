package me.dio.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.dio.dtos.AutorDto;
import me.dio.dtos.LivroDto;
import me.dio.entity.Autor;
import me.dio.entity.Livro;

@Configuration
public class ModdelMapperConfiguration {
	@Bean
	public ModelMapper modelMapper() {
		
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(Autor.class, AutorDto.class)
	    .addMappings(mapper -> {
	        mapper.map(Autor::getNome, AutorDto::setNome);
	        mapper.map(Autor::getDataDeNascimento, AutorDto::setDataDeNascimento);
	        mapper.map(Autor::getNacionalidade, AutorDto::setNacionalidade);
	    });


		modelMapper.createTypeMap(Livro.class, LivroDto.class)
	    .addMappings(mapper -> {
	        mapper.map(src -> src.getTitulo(), LivroDto::setTitulo);
	        mapper.map(src -> src.getIsbn(), LivroDto::setIsbn);
	        mapper.map(src -> src.getGenero(), LivroDto::setGenero);
	        mapper.map(src -> src.getAnoPublicacao(), LivroDto::setAnoPublicacao);
	        mapper.map(src -> src.getQuantidade(), LivroDto::setQuantidade);
	        mapper.map(src -> src.getAutor(), LivroDto::setAutor);
	    });
		
		return modelMapper;
	}
}
