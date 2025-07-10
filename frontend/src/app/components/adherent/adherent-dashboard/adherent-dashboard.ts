import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { CommonModule } from '@angular/common';
import {Auth} from '../../../services/auth';
import {Router} from '@angular/router';
import {Adherent, Sex} from '../../../models/adherent.model';
import {AdherentService} from '../../../services/adherent.service';


@Component({
  selector: 'app-adherent-dashboard',
  imports: [
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './adherent-dashboard.html',
  styleUrls: ['./adherent-dashboard.scss']
})
export class AdherentDashboard implements OnInit {
  Sex = Sex;

  adherent: Adherent = {
    firstName: 'Ayoub',
    nom:'Ayouni',
    email: 'ayoub@example.com',
    phone: '',
    gender:Sex.FEMME,
    age:0,
    poids:0,

  };

  editMode = false;
  profileForm!: FormGroup;

  constructor(private fb: FormBuilder, private router: Router,
              private adherentService: AdherentService) {}

  ngOnInit(): void {
    this.profileForm = this.fb.group({
      firstName: [this.adherent.firstName, Validators.required],
      nom: [this.adherent.nom, Validators.required],
      email: [this.adherent.email, [Validators.required, Validators.email]],
      phone: [this.adherent.phone],
      genre: [this.adherent.gender],
      age: [this.adherent.age],
      poids: [this.adherent.poids],
    });
  }

  toggleEdit(): void {
    this.editMode = !this.editMode;
    if (this.editMode) {
      this.profileForm.patchValue(this.adherent);
    }
  }

  onSubmit(): void {
    if (this.profileForm.valid) {
      const updatedAdherent: Adherent = {
        ...this.adherent,
        ...this.profileForm.value,
        gender: this.profileForm.value.genre
      };

      this.adherentService.updateAdherent(updatedAdherent.id,updatedAdherent).subscribe({
        next: (res) => {
          this.adherent = res;
          this.editMode = false;
          console.log('Profil mis à jour sur le serveur :', res);
        },
        error: (err) => {
          console.error('Erreur lors de la mise à jour du profil :', err);
        }
      });
    }
  }



}

