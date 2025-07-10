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
  "nom": string,
  "niveau": ProgramLevel,
  "description": string,
  "dureeEnSemaines": number,
  "visibilite": boolean,
  "objective": ProgramObjective,
  "coach": number
}
