import { Component } from '@angular/core';
import { RecipeCardComponent } from '../recipe-card/recipe-card.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { CreateRecipeFormComponent } from '../create-recipe-form/create-recipe-form.component';
import { AuthServiceService } from '../../services/Auth/auth-service.service';
import { RecipeService } from '../../services/Recipe/recipe-service.service';
import { state } from '@angular/animations';


@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [RecipeCardComponent, MatIconModule, MatButtonModule],
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
  providers: [RecipeService]  // <-- Provide the service here if standalone
})
export class HomePageComponent {

  recipes = [];

  constructor(public dialog: MatDialog, public authService: AuthServiceService,
             private recipeService:RecipeService ) {}

  handleOpenCreateRecipeForm(): void {
    this.dialog.open(CreateRecipeFormComponent);
  }

  ngOnInit(): void {
    this.authService.getUserProfile();
    this.recipeService.getRecipes().subscribe();
    this.recipeService.recipeSubject.subscribe(
      (state)=>{
        console.log("state",state)
        this.recipes=state.recipes
      }
      
    );
  }
}
