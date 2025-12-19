package sorts;

import java.util.HashMap;

public interface LivreDeSorts {
    HashMap<String, Sort> SORTS = new HashMap<>() {{
        put("boule de feu", new Sort("boule de feu", 5, 10, 1, new EffetDegats()));
        put("eclair de givre", new Sort("eclair de givre", 10, 20, 3, new EffetDegats()));
        put("eclair de foudre", new Sort("eclair de foudre", 15, 30, 5, new EffetDegats()));
        put("vague de lave", new Sort("vague de lave", 20, 40, 7, new EffetDegats()));
        put("permafrost", new Sort("permafrost", 25, 50, 9, new EffetDegats()));
        put("orage", new Sort("orage", 30, 60, 11, new EffetDegats()));
        put("soins", new Sort("soins", 10, 20, 1, new EffetSoins()));
        put("soins superieur", new Sort("soins superieur", 25, 50, 10, new EffetSoins()));
    }};
}
