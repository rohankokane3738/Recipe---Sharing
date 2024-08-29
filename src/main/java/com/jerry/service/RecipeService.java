package com.jerry.service;

import java.util.List;

import com.jerry.model.Recipe;
import com.jerry.model.User;

public interface RecipeService {
	
	public Recipe createRecipe(Recipe recipe, User user);
	
	public Recipe findRecipeById(Long id) throws Exception;
	
	public void deleteRecipe(Long id) throws Exception;
	
	public Recipe updateRecipe(Recipe recipe, Long Id) throws Exception;
	
	public List<Recipe> findAllRecipes();  // Correct method name
	
	public Recipe likeRecipe(Long recipeId, User user) throws Exception;

}
