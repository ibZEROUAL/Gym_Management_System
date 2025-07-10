import {Component, Input, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {Program} from '../../../models/program.model'


@Component({
  standalone: true,
  selector: 'app-program-detail',
  imports: [
    CommonModule
  ],
  templateUrl: './program-detail.html',
  styleUrls: ['./program-detail.scss']
})
export class ProgramDetail implements OnInit {

  constructor() {}

  @Input() list : Program[] = [];

  ngOnInit(): void {

  }
}
