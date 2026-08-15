package com.roocbuilds.api.repository;

import com.roocbuilds.api.model.entity.CharacterBuild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICharacterBuildRepository  extends JpaRepository<CharacterBuild,Long> {

}
