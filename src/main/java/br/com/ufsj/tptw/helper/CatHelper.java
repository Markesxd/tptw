package br.com.ufsj.tptw.helper;

import br.com.ufsj.tptw.dto.CatDataOutput;
import br.com.ufsj.tptw.model.Cat;

import java.util.HashSet;
import java.util.Set;

public class CatHelper {
  public static Set<CatDataOutput> mapFromCats(Set<Cat> cats) {
    Set<CatDataOutput> serializedCats = new HashSet<>();
    cats.forEach(cat -> {
      serializedCats.add(new CatDataOutput(cat));
    });
    return serializedCats;
  }

  public static Set<Cat> mapToCats(Set<CatDataOutput> cats) {
    Set<Cat> serializedCats = new HashSet<>();
    cats.forEach(cat -> {
      serializedCats.add(new Cat(cat));
    });
    return serializedCats;
  }

}
