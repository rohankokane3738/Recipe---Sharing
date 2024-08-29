import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatRadioModule} from '@angular/material/radio';
import { RecipeService } from '../../services/Recipe/recipe-service.service';

@Component({
  selector: 'app-create-recipe-form',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule,MatButtonModule,MatRadioModule],
  templateUrl: './create-recipe-form.component.html',
  styleUrls: ['./create-recipe-form.component.css'] // Fixed the typo here
})
export class CreateRecipeFormComponent {
  recipeItem: any = {
    title: "",
    description: "",
    foodType: "",
    image: ""
  };

  constructor(private recipeService : RecipeService){}

  onSubmit() { // Corrected the method name
    console.log("values", this.recipeItem);
    this.recipeService.createRecipes(this.recipeItem).subscribe(
      {
        next:data=>console.log("created recipe",data),
        error : error=>console.log("error",error)
      }
    )
  }
}
