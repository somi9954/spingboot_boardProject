package boardProject.commons.configs;

import boardProject.entities.Configs;
import boardProject.repositories.ConfigsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigSaveService {

    private final ConfigsRepository repository;

    public <T> void save(String code, T t) {

        Configs configs = repository.findById(code).orElseGet(Configs::new);

        ObjectMapper om = new ObjectMapper();
        String value = null;
        try {
            value = om.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        configs.setCode(code);
        configs.setValue(value);

        repository.saveAndFlush(configs);
    }
}