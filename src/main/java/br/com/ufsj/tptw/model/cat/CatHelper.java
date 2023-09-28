package br.com.ufsj.tptw.model.cat;

import java.util.HashSet;
import java.util.Set;

public class CatHelper {
  public static Set<CatDataOutput> serializeCats(Set<Cat> cats) {
    Set<CatDataOutput> serializedCats = new HashSet<>();
    cats.forEach(cat -> {
      serializedCats.add(new CatDataOutput(cat));
    });
    return serializedCats;
  }
}
