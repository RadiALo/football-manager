export interface PlayerRequest {
  firstName: string;
  lastName: string;
  experienceMonths: number;
  age: number;
  teamId: number;
}
  
export interface PlayerResponse {
  id: number;
  firstName: string;
  lastName: string;
  experienceMonths: number;
  age: number;
  teamId: number;
}