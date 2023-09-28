package br.com.ufsj.tptw.model.user;

import br.com.ufsj.tptw.model.cat.Cat;
import br.com.ufsj.tptw.model.cat.CatDataOutput;
import br.com.ufsj.tptw.model.cat.CatHelper;

import java.util.Set;
import java.util.UUID;

public record UserCatsDataOutput(
  Set<CatDataOutput> cats
) {
	public UserCatsDataOutput(User user) {
		this(CatHelper.serializeCats(user.getCats()));
	}
}
