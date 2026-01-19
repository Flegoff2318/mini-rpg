package persistance;

import persistance.dto.SaveGameDto;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveService {
    private final ObjectMapper mapper;

    public SaveService() {
        this.mapper = new ObjectMapper();
        this.mapper.isEnabled(SerializationFeature.INDENT_OUTPUT);
    }

    public void save(Path file, SaveGameDto dto) throws IOException {
        if (file.getParent() != null) {
            Files.createDirectories(file.getParent());
        }
        mapper.writeValue(file.toFile(), dto);
    }

    public SaveGameDto load(Path file) throws IOException{
        if(!Files.exists(file)){
            throw new IOException("Fichier de sauvegarde introuvable : " + file);
        }
        return mapper.readValue(file.toFile(),SaveGameDto.class);
    }

    public <T> void save(Path file, T dto) throws IOException {
        if (file.getParent() != null) {
            Files.createDirectories(file.getParent());
        }
        mapper.writeValue(file.toFile(), dto);
    }

    public <T>T load(Path file, Class<T> tClass) throws IOException{
        if(!Files.exists(file)){
            throw new IOException("Fichier de sauvegarde introuvable : " + file);
        }
        return mapper.readValue(file.toFile(),tClass);
    }
}
