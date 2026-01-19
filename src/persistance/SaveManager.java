package persistance;

import persistance.dto.SaveGameDto;
import persistance.dto.SaveMetaDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class SaveManager {
    private static final String META_FILE = "_meta.json";

    private final Path savesDir;
    private final SaveService saveService;

    public SaveManager(Path savesDir, SaveService saveService) {
        this.savesDir = savesDir;
        this.saveService = saveService;
    }

    // ---------- Sauvegarde/Chargement ----------

    public Path saveNew(String heroName, SaveGameDto dto) throws IOException {

        Path file = resolveHeroSavePath(heroName);

        if (Files.exists(file)) {
            throw new IOException("Une sauvegarde existe déjà pour ce nom : " + heroName);
        }
        saveService.save(file, dto);
        setLastSave(file.getFileName().toString());
        return file;
    }

    public Path saveOverwrite(String heroName, SaveGameDto dto) throws IOException {
        Path file = resolveHeroSavePath(heroName);
        saveService.save(file, dto);
        setLastSave(file.getFileName().toString());
        return file;
    }

    public SaveGameDto loadByHeroName(String heroName) throws IOException {
        Path file = resolveHeroSavePath(heroName);
        SaveGameDto dto = saveService.load(file);
        setLastSave(file.getFileName().toString());
        return dto;
    }

    public SaveGameDto loadByFileName(String fileName) throws IOException {
        Path file = savesDir.resolve(fileName);
        SaveGameDto dto = saveService.load(file);
        setLastSave(file.getFileName().toString());
        return dto;
    }

    public Optional<SaveGameDto> loadLast() throws IOException {
        Optional<String> last = getLastSave();
        if (last.isEmpty()) return Optional.empty();

        Path file = savesDir.resolve(last.get());
        if (!Files.exists(file)) return Optional.empty();

        return Optional.of(saveService.load(file));
    }

    public List<String> listSaveFiles() throws IOException {
        if (!Files.exists(savesDir)) return List.of();
        try (var stream = Files.list(savesDir)) {
            return stream
                    .filter(Files::isRegularFile)
                    .map(path -> path.getFileName().toString())
                    .filter(name -> name.toLowerCase().endsWith(".json"))
                    .filter(name -> !name.equalsIgnoreCase(META_FILE))
                    .sorted(String.CASE_INSENSITIVE_ORDER)
                    .collect(Collectors.toList());
        }
    }

    // ---------- Helpers ----------

    private Path resolveHeroSavePath(String heroName) throws IOException {
        Files.createDirectories(savesDir);
        String fileName = slugify(heroName) + ".json";
        return savesDir.resolve(fileName);
    }

    public static String slugify(String input) {
        if (input == null) return "hero";
        String s = input.trim().toLowerCase(Locale.ROOT);
        // Remplacement des espaces
        s = s.replaceAll("\\s+", "_");
        //Suppression des accents et caracteres speciaux
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}+", "");
        s = s.replaceAll("[^a-z0-9_\\-]", "");
        if (s.isBlank()) s = "hero";
        return s;
    }

    private Optional<String> getLastSave() throws IOException {
        Path metaPath = savesDir.resolve(META_FILE);
        if (!Files.exists(metaPath)) return Optional.empty();
        SaveMetaDto meta = saveService.load(metaPath, SaveMetaDto.class);
        return (meta == null || meta.lastSaveFile == null || meta.lastSaveFile.isBlank()) ? Optional.empty() : Optional.of(meta.lastSaveFile);
    }

    private void setLastSave(String fileName) throws IOException {
        Files.createDirectories(savesDir);
        Path metaPath = savesDir.resolve(META_FILE);

        SaveMetaDto metaDto = new SaveMetaDto();
        metaDto.lastSaveFile = fileName;
        saveService.save(metaPath, metaDto);
    }
}
