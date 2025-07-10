import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { AdherentList } from './components/adherent/adherent-list/adherent-list';
import { AdherentDetail } from './components/adherent/adherent-detail/adherent-detail';
import { CoachList } from './components/coach/coach-list/coach-list';
import { CoachDetail } from './components/coach/coach-detail/coach-detail';
import { ExerciseList } from './components/exercise/exercise-list/exercise-list';
import { ExerciseDetail } from './components/exercise/exercise-detail/exercise-detail';
import { ProgramList } from './components/program/program-list/program-list';
import { ProgramDetail } from './components/program/program-detail/program-detail';
import { RegistrationList } from './components/registration/registration-list/registration-list';
import { RegistrationDetail } from './components/registration/registration-detail/registration-detail';
import { RepastList } from './components/repast/repast-list/repast-list';
import { RepastDetail } from './components/repast/repast-detail/repast-detail';
import {Register} from './components/register/register';
import {AdherentDashboard} from './components/adherent/adherent-dashboard/adherent-dashboard';
import {Authentication} from './components/authentication/authentication';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'adherents', component: AdherentList },
  { path: 'adherents/:id', component: AdherentDetail },
  { path: 'coaches', component: CoachList },
  { path: 'coaches/:id', component: CoachDetail },
  { path: 'exercises', component: ExerciseList },
  { path: 'exercises/:id', component: ExerciseDetail },
  { path: 'programs', component: ProgramList },
  { path: 'registrations', component: RegistrationList },
  { path: 'registrations/:id', component: RegistrationDetail },
  { path: 'repasts', component: RepastList },
  { path: 'repasts/:id', component: RepastDetail },
  { path: 'register', component: Register },
  { path: 'authentication', component: Authentication },

  { path: 'adherentDashboard', component: AdherentDashboard },
  { path: '**', redirectTo: '' }

];
