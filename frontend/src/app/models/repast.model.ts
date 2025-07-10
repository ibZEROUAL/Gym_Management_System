export enum RepastType {
  Breakfast = 'Breakfast',
  Lunch = 'Lunch',
  Dinner = 'Dinner',
  Snack = 'Snack'
}

export enum RepastObjective {
  WeightLoss = 'WeightLoss',
  MuscleGain = 'MuscleGain',
  Maintenance = 'Maintenance',
  Performance = 'Performance',
  Recovery = 'Recovery'
}

export interface Aliment {
  id?: number;
  name: string;
  calories: number;
  proteins: number;
  carbs: number;
  fats: number;
  quantity: number;
  unit: string;
}

export interface Repast {
  id?: number;
  name: string;
  description: string;
  type: RepastType;
  objective: RepastObjective;
  totalCalories: number;
  preparationTime: number;
  creationDate: Date;
  imageUrl?: string;
  aliments?: Aliment[];
}
