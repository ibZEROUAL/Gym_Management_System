import { Component ,OnInit } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Auth} from '../../services/auth';
import {Router, RouterLink} from '@angular/router';
import {CommonModule, NgIf} from '@angular/common';

@Component({
  selector: 'app-authentication',
  imports: [
    ReactiveFormsModule,
    CommonModule,
    RouterLink,

  ],
  templateUrl: './authentication.html',
  styleUrl: './authentication.scss'
})
export class Authentication implements OnInit {

  authForm!: FormGroup;
  errorMessage: string = '';


  constructor(private fb: FormBuilder, private authService: Auth, private router: Router) {}

  ngOnInit(): void {
    this.authForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(5)]],
    });
  }


  onSubmit(): void {
      if (this.authForm.valid) {
        this.authService.login(this.authForm.value).subscribe({
          next: (response) => {
            console.log('Login success:', response);
            localStorage.setItem('token', response.token);
            this.router.navigate(['/AdherentDashboard']); // rediriger après login
          },
          error: (err) => {
            console.error('Login failed:', err);
            this.errorMessage = 'Email ou mot de passe incorrect';
          }
        });
      }


  }

}
