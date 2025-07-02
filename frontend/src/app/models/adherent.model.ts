export interface Adherent {
  id?: number;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  birthDate: Date;
  registrationDate: Date;
  address?: string;
  weight?: number;
  height?: number;
  goal?: string;
}
