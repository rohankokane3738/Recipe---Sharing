package com.jerry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.model.Recipe;
import com.jerry.model.User;
import com.jerry.service.RecipeService;
import com.jerry.service.UserService;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

	
	@Autowired
	private RecipeService recipeService;
	
	
	@Autowired
	private UserService userService;
	
	@PostMapping()
	public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) throws Exception {
	    User user = userService.findUserByJwt(jwt);
	    Recipe createdRecipe = recipeService.createRecipe(recipe, user);
	    return createdRecipe;
	}

	
	@PutMapping("/{id}")
	public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception {
		 
	
		
		Recipe updatedRecipe = recipeService.updateRecipe(recipe, id);
				return updatedRecipe;
		
	}



	@GetMapping()
	public List<Recipe> getAllRecipe() throws Exception {
		 
		List<Recipe> recipes = recipeService.findAllRecipes();
				
		return recipes;
		
	}
	
	
	
	@DeleteMapping("/{recipeId}")
	public String deleteRecipe(@PathVariable Long recipeId) throws Exception {
		 
		recipeService.deleteRecipe(recipeId);
		
		return "Recipe deleted sucessfully";
		
	}
	
	
	@PutMapping("/{id}/like")
	public Recipe likeRecipe(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
		 
	User user = userService.findUserByJwt(jwt);
		
		Recipe updatedRecipe = recipeService.likeRecipe(id, user);
				return updatedRecipe;
		
	}
	
	

}