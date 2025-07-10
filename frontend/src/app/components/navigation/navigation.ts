import {Component, HostListener, OnInit} from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, NgIf],
  templateUrl: './navigation.html',
  styleUrl: './navigation.scss'
})
export class NavigationComponent implements  OnInit {
  isMenuOpen: boolean = false;
  scrolled: boolean = false;
  hasRoleAdherent?: boolean;
  private user: any;

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }


  ngOnInit() {
     if (this.user.role == 'admin') {
       this.hasRoleAdherent = true;
     }
    if (this.user.role == 'adehrent') {
      this.hasRoleAdherent = true;
    }

  }


  @HostListener('window:scroll', [])
  onWindowScroll() {
    // Change la valeur selon la hauteur de scroll souhaitée
    this.scrolled = window.scrollY > 50;
  }
}
