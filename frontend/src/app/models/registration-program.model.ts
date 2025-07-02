export interface RegistrationProgram {
  id?: number;
  registrationDate: Date;
  startDate: Date;
  endDate: Date;
  status: string;
  progress?: number;
  adherentId: number;
  programId: number;
  notes?: string;
}
