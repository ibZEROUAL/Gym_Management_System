export interface Coach {
  id?: number;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  speciality: string;
  experience: number;
  hireDate: Date;
  certifications?: string[];
  bio?: string;
}
