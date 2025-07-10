export enum ExerciseCategorie {
  CardioVasculaire = 'CardioVasculaire',
  Musculation = 'Musculation',
  Flexibility = 'Flexibility',
  Balance = 'Balance',
  Coordination = 'Coordination'
}

export interface Exercise {
  id?: number;
  name: string;
  description: string;
  creationDate: Date;
  category: ExerciseCategorie;
  imageUrl?: string;
  videoUrl?: string;
  duration?: number;
  caloriesBurned?: number;
  difficulty?: string;
}
