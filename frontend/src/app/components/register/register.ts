import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Auth} from '../../services/auth';
import {Router} from '@angular/router';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-register',
  imports: [
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './register.html',
  styleUrl: './register.scss'
})
export class Register implements OnInit{

  registrationForm!: FormGroup;
  protected errorMessage: string | undefined;

  constructor(private fb: FormBuilder, private authService: Auth, private router: Router) {}

  ngOnInit(): void {
    this.registrationForm = this.fb.group({
      nom: ['', [Validators.required]],
      firstName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(5)]],
      confirmPassword: ['', [Validators.required]],
    });
  }


  onSubmit(): void {
    if (this.registrationForm.valid) {
      this.authService.addAdherent(this.registrationForm.value).subscribe({
        next: (res) => {
          if (res.status === 200) {
            this.registrationForm.reset();
            this.router.navigate(['/authentication']); // Chemin entre crochets
          } else {
            this.errorMessage = 'Erreur lors de l\'inscription. Veuillez réessayer.';
            console.error('Inscription échouée avec le statut:', res.status);
          }
        },
        error: (err) => {
          this.errorMessage = 'Une erreur est survenue. Veuillez vérifier votre connexion ou réessayer plus tard.';
          console.error('Erreur lors de l\'inscription:', err);
        }
      });
    } else {
      console.warn('Formulaire invalide');
      this.errorMessage = 'Veuillez remplir correctement tous les champs.';
      this.registrationForm.markAllAsTouched(); // Marque tous les champs comme "touchés" pour afficher les erreurs
    }
  }


  onSubmitCoach(): void {
    if (this.registrationForm.valid) {
      this.authService.addCoach(this.registrationForm.value).subscribe()
    }
  }

}
