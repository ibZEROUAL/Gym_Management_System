export enum ProgramLevel {
  Beginner = 'Beginner',
  Intermediate = 'Intermediate',
  Advanced = 'Advanced',
  Expert = 'Expert'
}

export enum ProgramObjective {
  WeightLoss = 'WeightLoss',
  MuscleGain = 'MuscleGain',
  Endurance = 'Endurance',
  Flexibility = 'Flexibility',
  Strength = 'Strength',
  Balance = 'Balance'
}

export interface Program {
  id?: number;
  name: string;
  description: string;
  level: ProgramLevel;
  objective: ProgramObjective;
  durationInWeeks: number;
  isVisible: boolean;
  creationDate?: Date;
  exercises?: number[]; // IDs of exercises
  repasts?: number[]; // IDs of repasts
  coachId?: number;
}
