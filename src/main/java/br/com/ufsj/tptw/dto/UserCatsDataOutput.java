package br.com.ufsj.tptw.dto;

import br.com.ufsj.tptw.helper.CatHelper;
import br.com.ufsj.tptw.model.User;

import java.util.Set;

public record UserCatsDataOutput(
  Set<CatDataOutput> cats
) {
	public UserCatsDataOutput(User user) {
		this(CatHelper.mapFromCats(user.getCats()));
	}
}
