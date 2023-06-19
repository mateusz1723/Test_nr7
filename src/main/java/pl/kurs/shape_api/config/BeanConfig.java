package pl.kurs.shape_api.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.kurs.shape_api.mapper.ShapeMapperDtoConverter;

import java.util.Optional;
import java.util.Set;

@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@Configuration
public class BeanConfig {


    @Bean
    public ModelMapper getModelMapper(Set<Converter> converters) {
        ModelMapper modelMapper = new ModelMapper();
        converters.forEach(modelMapper::addConverter);
        return modelMapper;
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of(((UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername());
    }
}
