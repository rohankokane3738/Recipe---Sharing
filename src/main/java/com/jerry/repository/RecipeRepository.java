package com.jerry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jerry.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
