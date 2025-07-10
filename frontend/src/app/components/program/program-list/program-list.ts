import {Component, OnInit} from '@angular/core';
import {ProgramService} from '../../../services/program.service';
import {Program} from '../../../models/program.model'
import {CommonModule, NgClass} from '@angular/common';
import {ProgramDetail} from '../program-detail/program-detail';



@Component({
  selector: 'app-program-list',
  imports: [
    NgClass,
    CommonModule,
    ProgramDetail
  ],
  templateUrl: './program-list.html',
  styleUrl: './program-list.scss'
})
export class ProgramList implements OnInit {

  constructor(private programService: ProgramService) { }

  programsList : Program[] = [];

  ngOnInit() {
    this.programService.getAllPrograms().subscribe((programs: Program[]) => {
      this.programsList = programs
    })
  }

}
