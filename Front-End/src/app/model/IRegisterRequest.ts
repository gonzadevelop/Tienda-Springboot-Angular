export default interface IRegisterRequest {
  username: string;
  password: string;
  email: string;
  nombre: string;
  apellidos: string;
  genero: string;
  dni?: string;
  telefonoContacto?: string;
}
