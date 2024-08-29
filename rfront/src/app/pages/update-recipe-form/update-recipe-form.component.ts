import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatRadioModule} from '@angular/material/radio';
import { RecipeService } from '../../services/Recipe/recipe-service.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Inject} from '@angular/core';

@Component({
  selector: 'app-update-recipe-form',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule,MatButtonModule,MatRadioModule],
  templateUrl: './update-recipe-form.component.html',
  styleUrl: './update-recipe-form.component.css'
})
export class UpdateRecipeFormComponent {
  recipeItem: any = {
    title: '',
    description: '',
    foodType: '',
    image: ''
  };

  constructor(@Inject(MAT_DIALOG_DATA) public recipe:any,
  private recipeService : RecipeService) {}

  onSubmit() { 
    this.recipeService.updateRecipes(this.recipeItem).subscribe({
      next:data=>console.log("update sucessful",data),
      error:error=>console.log("error", error)
    })
    console.log('values----', this.recipeItem);
   
  }

  ngOnInit() {
    this.recipeItem=this.recipe
  }
}
