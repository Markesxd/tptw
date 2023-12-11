package br.com.ufsj.tptw.service;

import br.com.ufsj.tptw.dto.CatDTO;
import br.com.ufsj.tptw.dto.CatDataOutput;
import br.com.ufsj.tptw.model.Cat;
import br.com.ufsj.tptw.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CatService {
  @Autowired
  private CatRepository catRepository;
  public void createCat(CatDTO cat) {
    catRepository.save(new Cat(cat));
  }

  public CatDTO findById(Long id) {
    return catRepository.findById(id).map(CatDTO::new).orElseThrow();
  }

  public Page<CatDataOutput> findAll(Pageable pageable) {
    return catRepository.findAll(pageable).map(CatDataOutput::new);
  }
}
